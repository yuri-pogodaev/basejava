package com.basejava.webapp.storage;

import com.basejava.webapp.model.*;

import java.time.Month;

public class ResumeTestData {
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";
    public static final String UUID_4 = "uuid4";

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.putContact(ContactType.EMAIL, "mail1@ya.ru");
        R1.putContact(ContactType.PHONE, "11111");
        R1.putSection(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.putSection(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.putSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.putSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.putSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.putSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R2.putContact(ContactType.SKYPE, "skype2");
        R2.putContact(ContactType.PHONE, "22222");
        R1.putSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }
}


