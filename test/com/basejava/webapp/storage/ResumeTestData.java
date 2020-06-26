package com.basejava.webapp.storage;

import com.basejava.webapp.model.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.basejava.webapp.model.ContactType.*;
import static com.basejava.webapp.model.SectionType.*;
import static com.basejava.webapp.util.DateUtil.of;

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
        R1.putSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Java Online Projects", "http://javaops.ru/",
                                new Organization.Position(2013, Month.OCTOBER, "Автор проекта.\n", "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                        new Organization("Wrike", "https://www.wrike.com/",
                                new Organization.Position(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)\n", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.")),
                        new Organization("RIT Center", null,
                                new Organization.Position(2012, Month.APRIL, 2014, Month.OCTOBER, "Java архитектор\n", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python")),
                        new Organization("Luxoft (Deutsche Bank)", "https://www.luxoft.com/",
                                new Organization.Position(2010, Month.DECEMBER, 2012, Month.APRIL, "Ведущий программист\n", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.")),
                        new Organization("Yota", "https://www.yota.ru/",
                                new Organization.Position(2008, Month.JUNE, 2010, Month.DECEMBER, "Ведущий специалист\n", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)")),
                        new Organization("Enkata", "https://www.pega.com/products/pega-platform/robotic-automation",
                                new Organization.Position(2007, Month.MARCH, 2008, Month.JUNE, "Разработчик ПО\n", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).")),
                        new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html",
                                new Organization.Position(2005, Month.JANUARY, 2007, Month.FEBRUARY, "Разработчик ПО\n", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).")),
                        new Organization("Alcatel", "http://www.alcatel.ru/",
                                new Organization.Position(1997, Month.SEPTEMBER, 2005, Month.JANUARY, "Инженер по аппаратному и программному тестированию\n", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))));
        R1.putSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Coursera", "https://www.coursera.org/learn/progfun1",
                                new Organization.Position(2013, Month.MARCH, 2013, Month.MAY, "Слушатель", "\"Functional Programming Principles in Scala\" by Martin Odersky")),
                        new Organization("Luxoft", "https://www.luxoft-training.ru/kurs/obektno-orientirovannyy_analiz_i_proektirovanie_na_uml.html",
                                new Organization.Position(2011, Month.MARCH, 2011, Month.APRIL, "Слушатель", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"")),
                        new Organization("Siemens AG", "https://new.siemens.com/ru/ru.html",
                                new Organization.Position(2005, Month.JANUARY, 2005, Month.APRIL, "Студент", "3 месяца обучения мобильным IN сетям (Берлин)")),
                        new Organization("Alcatel", "http://www.alcatel.ru/",
                                new Organization.Position(1997, Month.SEPTEMBER, 1998, Month.MARCH, "Студент", "6 месяцев обучения цифровым телефонным сетям (Москва)")),
                        new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "https://itmo.ru/ru/",
                                new Organization.Position(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирант", "Аспирантура (программист С, С++)"),
                                new Organization.Position(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер", "Инженер (программист Fortran, C)")),
                        new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/",
                                new Organization.Position(1984, Month.SEPTEMBER, 1987, Month.JUNE, "Ученик", "Закончил с отличием"))
                ));
    }
}


