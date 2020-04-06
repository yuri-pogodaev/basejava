package com.basejava.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Position {
    private final LocalDate startDate;
    private final LocalDate finalDate;
    private final String position;
    private final String description;

    public Position(LocalDate startDate, LocalDate finalDate, String position, String description) {
        Objects.requireNonNull(startDate, "parameter startDate is null");
        Objects.requireNonNull(finalDate, "parameter finalDate is null");
        Objects.requireNonNull(position, "parameter position is null");

        this.startDate = startDate;
        this.finalDate = finalDate;
        this.position = position;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position that = (Position) o;

        return startDate.equals(that.startDate) &&
                finalDate.equals(that.finalDate) &&
                position.equals(that.position) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, finalDate, position, description);
    }

    @Override
    public String toString() {
        return "Position{" +
                "startDate=" + startDate +
                ", finalDate=" + finalDate +
                ", position='" + position + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

