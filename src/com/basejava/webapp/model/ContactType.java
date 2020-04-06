package com.basejava.webapp.model;

public enum ContactType {
    PHONE("Телефон"),
    MOBILE("Мобильный телефон"),
    HOME_PHONE("Домашний телефон"),
    GITHUB("Профиль GitHub"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    STACKOVERFLOW("Профиль StackOverFlow"),
    HOMEPAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }
}