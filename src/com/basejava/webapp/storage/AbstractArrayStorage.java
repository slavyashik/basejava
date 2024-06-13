package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    private static final String ERROR_RESUME_NOT_FOUND = "Ошибка: в базе нет резюме с UUID: ";
    private static final String ERROR_OUT_OF_SIZE = "Ошибка: база резюме заполнена.";
    private static final String ERROR_RESUME_EXISTS = "Ошибка: в базе уже есть резюме с UUID: ";
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());

        if (index == -1) {
            System.out.printf("%s%s%n", ERROR_RESUME_NOT_FOUND, r);
        } else {
            storage[index] = r;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    final public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.printf("%s%s%n", ERROR_RESUME_EXISTS, r);
        } else if (size >= STORAGE_LIMIT) {
            System.out.printf("%s%n", ERROR_OUT_OF_SIZE);
        } else {
            insertResume(r, index);
            size++;
        }
    }

    final public void delete(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            System.out.printf("%s%s%n", ERROR_RESUME_NOT_FOUND, uuid);
            return;
        }

        moveItems(index);
        storage[size--] = null;
    }

    final public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index == -1) {
            System.out.printf("%s%s%n", ERROR_RESUME_NOT_FOUND, uuid);
            return null;
        }

        return storage[index];
    }

    protected abstract void insertResume(Resume r, int index);

    protected abstract void moveItems(int index);

    protected abstract int getIndex(String uuid);
}