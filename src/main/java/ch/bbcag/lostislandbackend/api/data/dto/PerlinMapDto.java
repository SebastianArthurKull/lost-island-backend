package ch.bbcag.lostislandbackend.api.data.dto;

import java.io.Serializable;
import java.util.Objects;

public class PerlinMapDto implements Serializable {
    private Integer id;

    private String name;

    public PerlinMapDto() {
    }

    public PerlinMapDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PerlinMapDto entity = (PerlinMapDto) o;
        return Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                ")";
    }
}