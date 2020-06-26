package com.basejava.webapp.storage;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.model.Section;
import com.basejava.webapp.model.SectionType;
import com.basejava.webapp.sql.SqlHelper;
import com.basejava.webapp.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SqlStorage implements Storage {
    private static final String QUERY_CLEAR = "DELETE From resume";
    private static final String QUERY_UPDATE = "UPDATE resume SET full_name = ? WHERE uuid = ?";
    private static final String QUERY_SAVE = "INSERT INTO resume(uuid, full_name) VALUES (?,?)";
    private static final String QUERY_GET = "SELECT * FROM resume WHERE uuid=?";
    private static final String QUERY_GET_CONTACT = "SELECT * FROM contact WHERE resume_uuid=?";
    private static final String QUERY_GET_SECTION = "SELECT * FROM section WHERE resume_uuid=?";
    private static final String QUERY_DELETE = "DELETE FROM resume WHERE uuid=?";
    private static final String QUERY_SIZE = "SELECT count(*) FROM resume";
    private static final String QUERY_GET_ALL_SORTED = "SELECT * FROM resume ORDER BY full_name, uuid";
    private static final String QUERY_GET_ALL_SORTED_CONTACT = "SELECT * FROM contact";
    private static final String QUERY_GET_ALL_SORTED_SECTION = "SELECT * FROM section";
    private static final String QUERY_DELETE_CONTACT = "DELETE FROM contact WHERE resume_uuid=?";
    private static final String QUERY_DELETE_SECTION = "DELETE FROM section WHERE resume_uuid=?";
    private static final String QUERY_WRITE_CONTACT = ""
            + " INSERT INTO contact (resume_uuid, type, value)"
            + "      VALUES (?, ?, ?)";
    private static final String QUERY_WRITE_SECTION = "INSERT INTO section(resume_uuid, type, content) VALUES (?, ?, ?)";

    private static final Logger LOG = Logger.getLogger(SqlStorage.class.getName());
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        LOG.info("Clear storage");
        sqlHelper.execute(QUERY_CLEAR, ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        LOG.info("Update " + resume.getUuid());
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement(QUERY_UPDATE)) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
                deleteContact(resume, conn);
                deleteSection(resume, conn);
                writeContact(resume, conn);
                writeSection(resume, conn);
                return null;

        });
    }

    @Override
    public void save(Resume resume) {
        LOG.info("Save " + resume.getUuid());
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement(QUERY_SAVE)) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    writeContact(resume, conn);
                    writeSection(resume, conn);
                    return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("Get storage size");
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement(QUERY_GET)) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn.prepareStatement(QUERY_GET_CONTACT)) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    readContact(resume, rs);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(QUERY_GET_SECTION)) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    readSection(resume, rs);
                }
            }
            return resume;
        });
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
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
        LOG.info("Get storage size");
        return sqlHelper.execute(QUERY_SIZE, ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement(QUERY_GET_ALL_SORTED)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    String fullName = rs.getString("full_name");
//                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                    resumes.computeIfAbsent(uuid, k -> new Resume(uuid, fullName));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(QUERY_GET_ALL_SORTED_CONTACT)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    readContact(resumes.get(rs.getString("resume_uuid")), rs);
                }
            }
            try (PreparedStatement ps = conn.prepareStatement(QUERY_GET_ALL_SORTED_SECTION)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    readSection(resumes.get(rs.getString("resume_uuid")), rs);
                }
            }
            return new ArrayList<>(resumes.values());
        });
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

    private void writeSection(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_WRITE_SECTION)) {
            for (Map.Entry<SectionType, Section> e : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, JsonParser.write(e.getValue(), Section.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteContact(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(QUERY_DELETE_CONTACT)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void deleteSection(Resume resume, Connection conn) throws SQLException{
        try (PreparedStatement ps = conn.prepareStatement(QUERY_DELETE_SECTION)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void readContact(Resume resume, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            ContactType type = ContactType.valueOf(rs.getString("type"));
            resume.putContact(type, value);
        }
    }

    private void readSection(Resume resume, ResultSet rs) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            resume.putSection(type, JsonParser.read(content, Section.class));
        }
    }
}