package com.basejava.webapp.model;

import java.util.List;
import java.util.Objects;

public class OrganizationArea extends Section {
    private final List<Organization> organizations;

    public OrganizationArea(List<Organization> organizations) {
        Objects.requireNonNull(organizations, "Organizations must not be null");
        this.organizations = organizations;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationArea that = (OrganizationArea) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
