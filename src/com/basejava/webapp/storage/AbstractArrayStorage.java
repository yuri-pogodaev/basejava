package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        storage[index] = resume;
    }

    @Override
    protected void doSave(Resume resume, int index) {
        if (counter == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, index);
            counter++;
        }
    }

    @Override
    protected void doDelete(int index) {
        fillDeletedElement(index);
        storage[counter - 1] = null;
        counter--;
    }

    @Override
    protected Resume doGet(int index) {
        return storage[index];
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[counter];
        System.arraycopy(storage, 0, resumes, 0, counter);
        return resumes;
    }

    public int size() {
        return counter;
    }

    @Override
    protected boolean isExist(int index) {
        return index >= 0;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract int getAnyKey(String uuid);
}