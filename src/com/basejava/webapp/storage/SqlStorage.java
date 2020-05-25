package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    public static final String QUERY_CLEAR = "DELETE From resume";
    public static final String QUERY_UPDATE = "UPDATE resume SET full_name = ? WHERE uuid = ?";
    public static final String QUERY_SAVE = "INSERT INTO resume(uuid, full_name) VALUES (?,?)";
    public static final String QUERY_GET = "SELECT * From resume r " +
            " LEFT JOIN contact c " +
            "   ON r.uuid = c.resume_uuid " +
            " WHERE r.uuid =?";
    public static final String QUERY_DELETE = "DELETE FROM resume WHERE uuid=?";
    public static final String QUERY_SIZE = "SELECT count(*) FROM resume";
    public static final String QUERY_GET_ALL_SORTED = "SELECT * From resume r " +
            "LEFT JOIN contact c " +
            "ON r.uuid = c.resume_uuid " +
            "ORDER BY full_name,uuid";
    public static final String QUERY_DELETE_CONTACT = "DELETE FROM contact WHERE resume_uuid=?";
    public static final String QUERY_WRITE_CONTACT = ""
            + " INSERT INTO contact (resume_uuid, type, value)"
            + "      VALUES (?, ?, ?)";

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute(QUERY_CLEAR, ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement(QUERY_UPDATE)) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
                deleteContact(resume, conn);
                writeContact(resume, conn);
                return null;
            }
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement(QUERY_SAVE)) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    writeContact(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute(QUERY_GET,
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume resume = new Resume(uuid, rs.getString("full_name"));
                    do {
                        readContact(rs, resume);
                    } while (rs.next());
                    return resume;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute(QUERY_DELETE, ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute(QUERY_SIZE, ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute(QUERY_GET_ALL_SORTED,
                ps -> {
                    ResultSet rs = ps.executeQuery();

                    Map<String, Resume> map = new LinkedHashMap<>();
                    while (rs.next()) {
                        String uuid = rs.getString("uuid");
                        Resume resume = map.get(uuid);
                        if (resume == null) {
                            String full_name = rs.getString("full_name");
                            resume = new Resume(uuid, full_name);
                        }
                        readContact(rs, resume);
                        map.put(uuid, resume);
                    }
                    return new ArrayList<>(map.values());
                });
    }

    private void readContact(ResultSet rs, Resume resume) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            resume.putContact(type, value);
        }
    }

    private void deleteContact(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_DELETE_CONTACT)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void writeContact(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_WRITE_CONTACT)) {
            for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
