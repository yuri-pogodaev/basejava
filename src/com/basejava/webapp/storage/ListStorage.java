package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> list = new ArrayList<>();

    @Override
    protected int getAnyKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean isExist(int anyKey) {
        return anyKey != 0;
    }

    @Override
    protected void doUpdate(Resume resume, int anyKey) {
        list.set(anyKey, resume);

    }

    @Override
    protected void doSave(Resume resume, int anyKey) {
        list.add(resume);
    }

    @Override
    protected void doDelete(int anyKey) {
        list.remove(anyKey);
    }

    @Override
    protected Resume doGet(int anyKey) {
        return list.get(anyKey);
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
