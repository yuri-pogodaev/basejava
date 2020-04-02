package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection implements Section {
    private final List<String> part;

    public ListSection(List<String> part) {
        Objects.requireNonNull(part, "part must not be null");
        this.part = part;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "part=" + part +
                '}';
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
        return Objects.hash(part);
    }
}
