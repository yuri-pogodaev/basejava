package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    MOBILE("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
    GITHUB("Профиль GitHub"),
    MAIL("Почта");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    private String getTitle() {
        return title;
    }
}