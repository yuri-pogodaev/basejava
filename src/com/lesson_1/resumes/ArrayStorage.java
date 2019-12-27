package com.lesson_1.resumes;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int counter = 0;

    void clear() {
        Arrays.fill(storage, 0, counter, null);
        counter = 0;
    }

    void save(Resume r) {
        storage[counter] = r;
        counter++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < counter; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int k = 0;
        for (int i = 0; i < counter; i++) {
            if (storage[i].uuid.equals(uuid)) {
                k = i;
                break;
            }
        }
        if (k != 0) {
            if (counter - k >= 0) System.arraycopy(storage, k + 1, storage, k, counter - k - 1);
            counter--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[counter];
        System.arraycopy(storage, 0, resumes, 0, counter);
        return resumes;
    }

    int size() {
        return counter;
    }
}