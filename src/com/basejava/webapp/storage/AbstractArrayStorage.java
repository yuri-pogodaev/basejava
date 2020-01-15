package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int counter = 0;

    public void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    public void update(Resume resume) {
        if (resume == null) {
            System.out.println("Resume = null");
        } else {
            int index = getIndex(resume.getUuid());
            if (index == -1) {
                System.out.println("Resume " + resume.getUuid() + " not exist");
            } else {
                storage[index] = resume;
            }
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (counter >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            insertElement(resume, index);
            counter++;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            fillDeletedElement(index);
            storage[counter - 1] = null;
            counter--;
        }
    }

    public Resume[] getAll() {
        Resume[] resumes = new Resume[counter];
        System.arraycopy(storage, 0, resumes, 0, counter);
        return resumes;
    }

    public int size() {
        return counter;
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract int getIndex(String uuid);
}