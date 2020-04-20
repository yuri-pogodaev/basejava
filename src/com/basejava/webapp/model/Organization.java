package com.basejava.webapp.model;

import com.basejava.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.basejava.webapp.util.DataUtil.NOW;
import static com.basejava.webapp.util.DataUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link link;
    private List<Position> positions = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Organization(Link link, List<Position> positions) {
        this.link = link;
        this.positions = positions;
    }

    public Link getLink() {
        return link;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;

        return link.equals(that.link) &&
                positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, positions);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link.toString() +
                ", positions=" + positions +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate finalDate;
        private String position;
        private String description;

        public Position() {
        }

        public Position(int startYear, Month startMonth, String position, String description) {
            this(of(startYear, startMonth), NOW, position, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String position, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), position, description);
        }

        public Position(LocalDate startDate, LocalDate finalDate, String position, String description) {
            Objects.requireNonNull(startDate, "parameter startDate is null");
            Objects.requireNonNull(finalDate, "parameter finalDate is null");
            Objects.requireNonNull(position, "parameter position is null");

            this.startDate = startDate;
            this.finalDate = finalDate;
            this.position = position;
            this.description = description == null ? "" : description;
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
}


