import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int resumesCount;

    void clear() {
        Arrays.fill(storage, 0, resumesCount, null);
        resumesCount = 0;
    }

    void save(Resume r) {
        storage[resumesCount++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < resumesCount; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int index = -1;

        for (int i = 0; i < resumesCount; i++) {
            if (storage[i].toString().equals(uuid)) {
                index = i;
            }
        }

        if (index != -1) {
            System.arraycopy(storage, index + 1, storage, index, resumesCount - index - 1);
            storage[--resumesCount] = null;
        }
    }

    /*
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, resumesCount);
    }

    int size() {
        return resumesCount;
    }
}
