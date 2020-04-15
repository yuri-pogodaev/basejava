package com.basejava.webapp.storage;

import com.basejava.webapp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.basejava.webapp.util.DataUtil.NOW;
import static java.time.LocalDate.of;

public class ResumeTestData {

    public static void main(String[] args) {
        printResume(generateResume("uuid1", "Григорий Кислин"));
    }

    public static Resume generateResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        //Контакты
        resume.putContact(ContactType.PHONE, "+7(921) 855-0482");
        resume.putContact(ContactType.EMAIL, "skype:grigory.kislin");
        resume.putContact(ContactType.SKYPE, "gkislin@yandex.ru");
        resume.putContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.putContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.putContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.putContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        // Раздел "Позиция"
        TextSection position = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.putSection(SectionType.OBJECTIVE, position);

        // Раздел "Личные качества"
        TextSection personalDesc = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.putSection(SectionType.PERSONAL, personalDesc);

        // Раздел "Достижения"
        List<String> listAchievements = new ArrayList<>();
        listAchievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        listAchievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        listAchievements.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");

        ListSection achievements = new ListSection(listAchievements);
        resume.putSection(SectionType.ACHIEVEMENT, achievements);

        // Раздел "Квалификация"
        List<String> listQualification = new ArrayList<>();
        listQualification.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2 ");
        listQualification.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce, ");
        listQualification.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");

        ListSection qualification = new ListSection(listQualification);
        resume.putSection(SectionType.QUALIFICATIONS, qualification);

        // Раздел "Опыт работы"
        List<Organization> organizationExpList = new ArrayList<>();
        // Первая работа
        Organization.Position javaops = new Organization.Position(of(2013, 10, 2), NOW, "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Link javaopsLink = new Link("JavaOps", "http://javaops.ru/");
        List<Organization.Position> firstJob = new ArrayList<>();
        firstJob.add(javaops);
        Organization firstOrganization = new Organization(javaopsLink, firstJob);
        organizationExpList.add(firstOrganization);
        // Вторая работа
        Organization.Position wrike = new Organization.Position(of(2014, 10, 2), of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Link wrikeLink = new Link("Wrike", "https://www.wrike.com/");
        List<Organization.Position> secondJob = new ArrayList<>();
        secondJob.add(wrike);
        Organization secondOrganization = new Organization(wrikeLink, secondJob);
        organizationExpList.add(secondOrganization);

        OrganizationSection experienceSection = new OrganizationSection(organizationExpList);

        resume.putSection(SectionType.EXPERIENCE, experienceSection);

        // Раздел "Образование
        List<Organization> educationExpList = new ArrayList<>();
        // Первое место
        Organization.Position Coursera = new Organization.Position(of(2013, 3, 1), of(2013, 5, 1), "\"Functional Programming Principles in Scala\" by Martin Odersky", null);
        Link CourseraLink = new Link("Coursera", "https://www.coursera.org/course/progfun");
        List<Organization.Position> firstEdu = new ArrayList<>();
        firstEdu.add(Coursera);
        Organization firstEducation = new Organization(CourseraLink, firstEdu);

        educationExpList.add(firstEducation);
        // Второе место
        Organization.Position itmo1 = new Organization.Position(of(1993, 9, 1), of(1996, 7, 1), "Аспирантура (программист С, С++)", null);
        Organization.Position itmo2 = new Organization.Position(of(1987, 9, 1), of(1993, 7, 1), "Инженер (программист Fortran, C)", null);
        Link itmoLink = new Link("IFMO", "http://www.ifmo.ru/");
        List<Organization.Position> secondEdu = new ArrayList<>();
        secondEdu.add(itmo1);
        secondEdu.add(itmo2);
        Organization secondEducation = new Organization(itmoLink, secondEdu);

        educationExpList.add(secondEducation);

        OrganizationSection education = new OrganizationSection(educationExpList);

        resume.putSection(SectionType.EDUCATION, education);

        return resume;
    }

    private static void printResume(Resume resume) {
        Map<ContactType, String> contacts = resume.getContacts();
        Map<SectionType, Section> sections = resume.getSections();

        //Выводим результат
        System.out.println(resume.getFullName());

        for (ContactType type : ContactType.values()) {
            System.out.println(type.getTitle() + " : " + contacts.get(type));
        }

        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle() + " : " + sections.get(type).toString());
        }
    }
}

