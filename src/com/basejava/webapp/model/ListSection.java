package com.basejava.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<String> part;

    public ListSection() {
    }

    public ListSection(String... part) {
        this(Arrays.asList(part));
    }

    public ListSection(List<String> part) {
        Objects.requireNonNull(part, "part must not be null");
        this.part = part;
    }

    public List<String> getPart() {
        return part;
    }

    @Override
    public String toString() {
        return part.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return part.equals(that.part);
    }

    @Override
    public int hashCode() {
        return part.hashCode();
    }
}
