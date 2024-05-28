package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    private static final String ERROR_RESUME_NOT_FOUND = "Ошибка: в базе нет резюме с UUID: ";
    private static final String ERROR_OUT_OF_SIZE = "Ошибка: база резюме заполнена.";
    private static final String ERROR_RESUME_EXISTS = "Ошибка: в базе уже есть резюме с UUID: ";
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = findIndex(r.uuid);

        if (index == -1) {
            System.out.printf("%s%s%n", ERROR_RESUME_NOT_FOUND, r);
            return;
        }

        storage[index] = r;
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.printf("%s%n", ERROR_OUT_OF_SIZE);
        } else if (findIndex(r.uuid) != -1) {
            System.out.printf("%s%s%n", ERROR_RESUME_EXISTS, r);
        } else {
            storage[size++] = r;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);

        if (index == -1) {
            System.out.printf("%s%s%n", ERROR_RESUME_NOT_FOUND, uuid);
            return null;
        }

        return storage[index];
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);

        if (index == -1) {
            System.out.printf("%s%s%n", ERROR_RESUME_NOT_FOUND, uuid);
            return;
        }

        storage[index] = storage[--size];
        storage[size] = null;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }

        return -1;
    }
}