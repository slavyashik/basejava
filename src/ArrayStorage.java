import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int resumesCount;

    void clear() {
        if (resumesCount > 0) {
            Arrays.fill(storage, 0, resumesCount, null);
            resumesCount = 0;
        }
    }

    void save(Resume r) {
        if (r != null) {
            storage[resumesCount++] = r;
        }
    }

    Resume get(String uuid) {
        if (resumesCount > 0) {
            for (int i = 0; i < resumesCount; i++) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        if (resumesCount == 0) {
            return;
        }

        for (int i = 0; i < resumesCount; i++) {
            if (storage[i].toString().equals(uuid)) {
                System.arraycopy(storage, i + 1, storage, i, resumesCount - i - 1);
                storage[--resumesCount] = null;
                break;
            }
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