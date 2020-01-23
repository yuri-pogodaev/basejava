package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        int numberMove = counter - index - 1;
        if (numberMove > 0) {
            System.arraycopy(storage, index + 1, storage, index, numberMove);
        }
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int insertIndex = -index - 1;
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, counter - insertIndex);
        storage[insertIndex] = resume;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, counter, searchKey);
    }
}
