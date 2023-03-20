package ch.bbcag.lostislandbackend.api.data.entity;

import javax.persistence.*;

@Entity
public class PerlinMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String perlinMapList;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPerlinMapList() {
        return perlinMapList;
    }

    public void setPerlinMapList(String perlinMapList) {
        this.perlinMapList = perlinMapList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
