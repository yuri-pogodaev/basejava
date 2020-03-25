package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListArea extends Section {
    private final List<String> items;

    public ListArea(List<String> items) {
        Objects.requireNonNull(items, "Items must not be null");
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListArea that = (ListArea) o;
        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
