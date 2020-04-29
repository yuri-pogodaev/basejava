package com.basejava.webapp.sql;

import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.Storage;

import java.sql.*;
import java.util.List;

public class SqlStorage implements Storage {
    public final ConnectionFactory connectionFactory;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("DELETE From resumes.public.resume");
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void save(Resume resume) {
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT into resumes.public.resume(uuid, full_name) VALUES (?,?)");
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

        @Override
        public Resume get (String uuid){
            try (Connection conn = connectionFactory.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT * From resumes.public.resume r WHERE r.uuid =?");
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                Resume resume = new Resume(uuid, rs.getString("full_name"));
                return resume;
            } catch (SQLException e) {
                throw new StorageException(e);
            }
        }

        @Override
        public void delete (String uuid){

        }

        @Override
        public int size () {
            return 0;
        }

        @Override
        public List<Resume> getAllSorted () {
            return null;
        }
    }
