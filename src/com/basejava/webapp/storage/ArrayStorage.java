package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;
import java.util.Arrays;

public class ArrayStorage {
    private static final String ERROR_RESUME_NOT_FOUND = "Ошибка: в базе нет резюме с UUID: ";
    private static final String ERROR_EMPTY_RESUME_DELIVERED = "Ошибка: передано пустое резюме.";
    private static final String ERROR_OUT_OF_SIZE = "Ошибка: база резюме заполнена.";
    private static final String ERROR_RESUME_EXISTS = "Ошибка: в базе уже есть резюме с UUID: ";
    private final Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        if (size > 0) {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.uuid);

        if (index == -1) {
            System.out.println(ERROR_RESUME_NOT_FOUND + r);
            return;
        }

        storage[index] = r;
    }

    public void save(Resume r) {
        if (r == null) {
            System.out.println(ERROR_EMPTY_RESUME_DELIVERED);
            return;
        }

        if (size == storage.length) {
            System.out.println(ERROR_OUT_OF_SIZE);
            return;
        }

        if (findIndex(r.uuid) != -1) {
            System.out.println(ERROR_RESUME_EXISTS + r);
            return;
        }

        storage[size++] = r;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);

        if (index == -1) {
            System.out.println(ERROR_RESUME_NOT_FOUND + uuid);
            return null;
        }

        return storage[index];
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);

        if (index == -1) {
            System.out.println(ERROR_RESUME_NOT_FOUND + uuid);
            return;
        }

        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        storage[--size] = null;
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