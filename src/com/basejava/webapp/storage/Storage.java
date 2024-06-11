package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

public interface Storage {

    int size();

    void clear();

    void update(Resume r);

    Resume[] getAll();

    void save(Resume r);

    void delete(String uuid);

    Resume get(String uuid);
}