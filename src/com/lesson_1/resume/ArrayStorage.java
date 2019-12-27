package com.lesson_1.resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int counter = 0;

    void clear() {
        Arrays.fill(storage, null);
        counter = 0;
    }

    void save(Resume r) {
        storage[counter] = r;
        counter++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size(); i++) {
            Resume r = storage[i];
            if (r.uuid.equals(uuid)) {
                return r;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int k = 0;
        for (int i = 0; i < size(); i++) {
            if (storage[i].uuid.equals(uuid)) {
                k = i;
                break;
            }
        }
        if (k != 0) {
            if (size() - 1 >= 0) System.arraycopy(storage, 1, storage, 0, size() - 1);
            counter--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] result = new Resume[size()];
        System.arraycopy(storage, 0, result, 0, size());
        return result;
    }

    int size() {
        return counter;
    }
}