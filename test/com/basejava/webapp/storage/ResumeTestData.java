package com.basejava.webapp.storage;

import com.basejava.webapp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.basejava.webapp.model.ContactType.*;
import static com.basejava.webapp.model.SectionType.*;

public class ResumeTestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");
        R1.putContact(MOBILE, "+7(921) 855-0482");
        R1.putContact(SKYPE, "grigory.kislin");
        R1.putContact(EMAIL, "grigory.kislin@mail.ru");
        R1.putContact(LINKEDIN, "https://www.linkedin.com/in/gkislin");
        R1.putContact(GITHUB, "https://github.com/gkislin");
        R1.putContact(STACKOVERFLOW, "https://stackoverflow.com/users/548473/grigory-kislin");
        R1.putSection(OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        R1.putSection(PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        List<String> strings = new ArrayList<>();
        strings.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        strings.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        strings.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        strings.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        strings.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        strings.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        R1.putSection(SectionType.ACHIEVEMENT, new ListSection(strings));
        List<String> strings2 = new ArrayList<>();
        strings2.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        strings2.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        strings2.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        strings2.add("MySQL, SQLite, MS SQL, HSQLDB");
        strings2.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        strings2.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        strings2.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        strings2.add("Python: Django.");
        strings2.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        strings2.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        strings2.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        strings2.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        strings2.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        strings2.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        strings2.add("Родной русский, английский \"upper intermediate\"");
        R1.putSection(QUALIFICATIONS, new ListSection(strings2));
/*        List<Organization> organizations = new ArrayList<>();
        organizations.add(new Organization("http://javaops.ru", "Java Online Projects", new Organization.Position(of(2013, Month.OCTOBER), of(2020, Month.FEBRUARY), "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.")));
        organizations.add(new Organization("https://www.wrike.com", "Wrike", new Organization.Position(of(2014, Month.OCTOBER), of(2016, Month.JANUARY), "\tСтарший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")));
        organizations.add(new Organization(null, "RIT Center", new Organization.Position(of(2012, Month.APRIL), of(2014, Month.OCTOBER), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")));
        organizations.add(new Organization("https://www.luxoft.com", "Luxoft (Deutsche Bank)", new Organization.Position(of(2012, Month.OCTOBER), of(2012, Month.APRIL), "\tВедущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")));
        organizations.add(new Organization("https://www.yota.ru", "Yota", new Organization.Position(of(2008, Month.JUNE), of(2010, Month.DECEMBER), "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")));
        organizations.add(new Organization("https://www.pega.com", "Enkata", new Organization.Position(of(2007, Month.MARCH), of(2008, Month.JUNE), "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")));
        organizations.add(new Organization("https://new.siemens.com", "Siemens AG", new Organization.Position(of(2005, Month.JANUARY), of(2007, Month.FEBRUARY), "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")));
        organizations.add(new Organization("http://www.alcatel.ru", "Alcatel", new Organization.Position(of(1997, Month.SEPTEMBER), of(2005, Month.JANUARY), "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")));
        R1.putSection(EXPERIENCE, new OrganizationSection(organizations));
        List<Organization> eduList = new ArrayList<>();
        eduList.add(new Organization("https://www.coursera.org", "Coursera", new Organization.Position(of(2013, Month.MARCH), of(2013, Month.MAY), "\"Functional Programming Principles in Scala\" by Martin Odersky", null)));
        eduList.add(new Organization("http://www.luxoft-training.ru", "Luxoft", new Organization.Position(of(2011, Month.MARCH), of(2011, Month.APRIL), "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null)));
        eduList.add(new Organization("http://www.siemens.ru", "Siemens", new Organization.Position(of(2005, Month.JANUARY), of(2005, Month.APRIL), "3 месяца обучения мобильным IN сетям (Берлин)", null)));
        eduList.add(new Organization("http://www.alcatel.ru", "Alcatel", new Organization.Position(of(1997, Month.SEPTEMBER), of(1998, Month.MARCH), "\t6 месяцев обучения цифровым телефонным сетям (Москва)", null)));
        eduList.add(new Organization("http://www.ifmo.ru", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", new Organization.Position(of(1993, Month.SEPTEMBER), of(1996, Month.JULY), "Аспирантура (программист С, С++)", null),
                new Organization.Position(of(1987, Month.SEPTEMBER), of(1993, Month.JULY), "Аспирантура (программист С, С++)", null)));
        eduList.add(new Organization("http://www.school.mipt.ru", "Заочная физико-техническая школа при МФТИ", new Organization.Position(of(1984, Month.SEPTEMBER), of(1987, Month.JUNE), "Закончил с отличием", null)));
        R1.putSection(EDUCATION, new OrganizationSection(eduList));*/

    /*    R1.putContact(ContactType.EMAIL, "mail1@ya.ru");
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
    }*/
    }
}


