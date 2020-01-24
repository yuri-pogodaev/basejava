package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public abstract class AbstractArrayStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final Resume R1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume R2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume R3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume R4 = new Resume(UUID_4);
    private Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAll() throws Exception {
        Resume[] actualResume = storage.getAll();
        Assert.assertEquals(3, actualResume.length);
        Assert.assertEquals(R1, actualResume[0]);
        Assert.assertEquals(R2, actualResume[1]);
        Assert.assertEquals(R3, actualResume[2]);
    }

    @Test
    public void save() throws Exception {
        storage.save(R4);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(R1, storage.get(UUID_1));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(R1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(R1, storage.get(UUID_1));
        Assert.assertEquals(R2, storage.get(UUID_2));
        Assert.assertEquals(R3, storage.get(UUID_3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

}