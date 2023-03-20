package ch.bbcag.lostislandbackend.api.data.dto;

public class PerlinMapWithDataDto extends PerlinMapDto{
    private String perlinMapList;

    public String getPerlinMapList() {
        return perlinMapList;
    }
    public void setPerlinMapList(String perlinMapList) {
        this.perlinMapList = perlinMapList;
    }
}