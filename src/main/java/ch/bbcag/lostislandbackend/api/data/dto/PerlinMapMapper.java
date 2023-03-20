package ch.bbcag.lostislandbackend.api.data.dto;

import ch.bbcag.lostislandbackend.api.data.entity.PerlinMap;


public class PerlinMapMapper {

    public static PerlinMapDto toDto(PerlinMap perlinMap) {
        PerlinMapDto perlinMapDto = new PerlinMapDto();

        perlinMapDto.setId(perlinMap.getId());
        perlinMapDto.setName(perlinMap.getName());

        return perlinMapDto;
    }

    public static PerlinMapWithDataDto toDtoWithData(PerlinMap perlinMap) {
        PerlinMapWithDataDto perlinMapWithDataDto = new PerlinMapWithDataDto();

        perlinMapWithDataDto.setId(perlinMap.getId());
        perlinMapWithDataDto.setPerlinMapList(perlinMap.getPerlinMapList());
        perlinMapWithDataDto.setName(perlinMap.getName());

        return perlinMapWithDataDto;
    }
}
