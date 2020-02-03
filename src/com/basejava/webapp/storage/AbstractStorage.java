package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int searchKey = Integer.parseInt(getSearchKey(resume.getUuid()));
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume, searchKey);
        }
    }

    public void save(Resume resume) {
        int searchKey = Integer.parseInt(getSearchKey(resume.getUuid()));
        if (isExist(searchKey)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, searchKey);
        }
    }

    public Resume get(String uuid) {
        int anyKey = Integer.parseInt(getSearchKey(uuid));
        if (!isExist(anyKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(anyKey);
        }

    }

    public void delete(String uuid) {
        int searchKey = Integer.parseInt(getSearchKey(uuid));
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(searchKey);
        }
    }

    protected abstract String getSearchKey(String uuid);

    protected abstract boolean isExist(int searchKey);

    protected abstract void doUpdate(Resume resume, int searchKey);

    protected abstract void doSave(Resume resume, int searchKey);

    protected abstract void doDelete(int searchKey);

    protected abstract Resume doGet(int searchKey);
}
