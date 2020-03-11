package com.basejava.webapp;


import com.basejava.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_1 = "uuid1";
    private static final Resume R1 = new Resume(UUID_1, "Name1");
    private static final String UUID_2 = "uuid2";
    private static final Resume R2 = new Resume(UUID_2, "Name2");
    private static final String UUID_3 = "uuid3";
    private static final Resume R3 = new Resume(UUID_3, "Name3");
    private static final String UUID_4 = "uuid4";
    private static final Resume R4 = new Resume(UUID_4, "Name4");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(R1);
        collection.add(R2);
        collection.add(R3);


        for (Resume resume : collection) {
            System.out.println(resume);
            if (Objects.equals(resume.getUuid(), UUID_1)) {
//                    collection.remove(resume);
            }
        }
        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume resume = iterator.next();
            System.out.println(resume);

            if (Objects.equals(resume.getUuid(), UUID_1)) {
                iterator.remove();
            }

        }
        System.out.println(collection.toString());
        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1,R1);
        map.put(UUID_2,R2);
        map.put(UUID_3,R3);

        for (String uuid: map.keySet()) {
            System.out.println(map.get(uuid));
        }
        for(Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
        List<Resume> resumes = Arrays.asList(R1, R2, R3);
        resumes.remove(1);
        System.out.println(resumes);
    }

}
