package com.basejava.webapp.storage;

import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage[index] = resume;
    }

    @Override
    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, counter));
    }

    @Override
    protected void doSave(Resume resume, Integer index) {
        if (counter == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, index);
            counter++;
        }
    }

    @Override
    protected void doDelete(Integer searchKey) {
        fillDeletedElement(searchKey);
        storage[counter - 1] = null;
        counter--;
    }


    public Resume doGet(Integer searchKey) {
        return storage[searchKey];
    }

    public int size() {
        return counter;
    }

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract Integer getSearchKey(String uuid);
}