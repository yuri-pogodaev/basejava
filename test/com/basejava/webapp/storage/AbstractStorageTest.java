package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.model.ContactType;
import com.basejava.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.basejava.webapp.storage.ResumeTestData.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\Users\\bozia\\Desktop\\basejava\\storage");

    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R3);
        storage.save(R1);
        storage.save(R2);
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
        Resume newResume = new Resume(UUID_1, "New Name");
        newResume.putContact(ContactType.EMAIL, "mail1@google.com");
        newResume.putContact(ContactType.SKYPE, "NewSkype");
        newResume.putContact(ContactType.MOBILE, "+7 921 223 54 35");
        storage.update(newResume);
        Assert.assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        Assert.assertEquals(3, list.size());
        Assert.assertEquals(list, Arrays.asList(R1, R2, R3));
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(R1, storage.get(UUID_1));
        Assert.assertEquals(R2, storage.get(UUID_2));
        Assert.assertEquals(R3, storage.get(UUID_3));
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

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}