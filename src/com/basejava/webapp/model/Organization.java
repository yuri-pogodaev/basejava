package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Organization {
    private final String companyName;
    private final String url;
    private final LocalDate startDate;
    private final LocalDate finalDate;
    private final String position;
    private final String positionDescription;

    public Organization(String companyName, String url, LocalDate startDate, LocalDate finalDate, String position, String positionDescription) {
        Objects.requireNonNull(companyName, "companyName must be not empty");
        Objects.requireNonNull(startDate, "startDate must be not empty");
        Objects.requireNonNull(finalDate, "endDate must be not empty");
        Objects.requireNonNull(position, "position must be not empty");

        this.companyName = companyName;
        this.url = url;
        this.startDate = startDate;
        this.finalDate = finalDate;
        this.position = position;
        this.positionDescription = positionDescription;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getUrl() {
        return url;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public String getPosition() {
        return position;
    }

    public String getPositionDescription() {
        return positionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (!companyName.equals(that.companyName)) return false;
        if (!Objects.equals(url, that.url)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!finalDate.equals(that.finalDate)) return false;
        if (!position.equals(that.position)) return false;
        return positionDescription.equals(that.positionDescription);
    }

    @Override
    public int hashCode() {
        int result = companyName.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + finalDate.hashCode();
        result = 31 * result + position.hashCode();
        result = 31 * result + positionDescription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "companyName='" + companyName + '\'' +
                ", url='" + url + '\'' +
                ", startDate=" + startDate +
                ", finalDate=" + finalDate +
                ", position='" + position + '\'' +
                ", positionDescription='" + positionDescription + '\'' +
                '}';
    }
}

