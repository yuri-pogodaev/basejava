package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected String getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return String.valueOf(i);
            }
        }
        return String.valueOf(-1);
    }

    @Override
    protected boolean isExist(int index) {
        return index != 0;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        list.set(index, resume);

    }

    @Override
    protected void doSave(Resume resume, int index) {
        list.add(resume);
    }

    @Override
    protected void doDelete(int index) {
        list.remove(index);
    }

    @Override
    protected Resume doGet(int index) {
        return list.get(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return list.size();
    }
}
