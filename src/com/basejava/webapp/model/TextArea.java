package com.basejava.webapp.model;

import java.util.Objects;

public class TextArea extends Section {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextArea that = (TextArea) o;
        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    private final String content;

    public TextArea(String content) {
        Objects.requireNonNull(content, "Content must not be null");
        this.content = content;
    }
}
