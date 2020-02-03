package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(int searchKey) {
        return false;
    }

    @Override
    protected void doUpdate(Resume resume, int searchKey) {

    }

    @Override
    protected void doSave(Resume resume, int searchKey) {

    }

    @Override
    protected void doDelete(int searchKey) {

    }

    @Override
    protected Resume doGet(int searchKey) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
