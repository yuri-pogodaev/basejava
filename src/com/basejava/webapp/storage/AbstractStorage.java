package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int anyKey = getAnyKey(resume.getUuid());
        if (!isExist(anyKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume, anyKey);
        }
    }

    public void save(Resume resume) {
        int anyKey = getAnyKey(resume.getUuid());
        if (isExist(anyKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, anyKey);
        }
    }

    public Resume get(String uuid) {
        int anyKey = getAnyKey(uuid);
        if (!isExist(anyKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(anyKey);
        }

    }

    public void delete(String uuid) {
        int anyKey = getAnyKey(uuid);
        if (!isExist(anyKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(anyKey);
        }
    }

    protected abstract int getAnyKey(String uuid);

    protected abstract boolean isExist(int anyKey);

    protected abstract void doUpdate(Resume resume, int anyKey);

    protected abstract void doSave(Resume resume, int anyKey);

    protected abstract void doDelete(int anyKey);

    protected abstract Resume doGet(int anyKey);
}
