package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.List;

public interface Storage {

    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    int size();

    List<Resume> getAllSorted();
}