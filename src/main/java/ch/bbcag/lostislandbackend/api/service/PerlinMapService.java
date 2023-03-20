package ch.bbcag.lostislandbackend.api.service;


import ch.bbcag.lostislandbackend.api.data.dto.PerlinMapDto;
import ch.bbcag.lostislandbackend.api.data.dto.PerlinMapMapper;
import ch.bbcag.lostislandbackend.api.data.dto.PerlinMapWithDataDto;
import ch.bbcag.lostislandbackend.api.data.entity.PerlinMap;
import ch.bbcag.lostislandbackend.api.data.repository.PerlinMapRepository;
import ch.bbcag.lostislandbackend.perlin.PerlinNoiseGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerlinMapService {

    private final PerlinMapRepository perlinMapRepository;


    @Autowired
    public PerlinMapService(PerlinMapRepository perlinMapRepository) {
        this.perlinMapRepository = perlinMapRepository;
    }

    public Iterable<PerlinMapDto> getAll() {

        List<PerlinMapDto> perlinMapDtoList = new ArrayList<>();
        List<PerlinMap> perlinMapList;
        perlinMapList = perlinMapRepository.findAll();
        for (PerlinMap perlinMap: perlinMapList) {
            perlinMapDtoList.add(PerlinMapMapper.toDto(perlinMap));

        }
        return perlinMapDtoList;
    }

    public PerlinMapWithDataDto getById(int id) {

        return PerlinMapMapper.toDtoWithData(perlinMapRepository.getReferenceById(id));
    }

    public PerlinMapWithDataDto createMap(String name, Integer size, Integer randomSeed, Integer cellSize) {

        PerlinMap perlinMap = new PerlinMap();

        String map = PerlinNoiseGenerator.generateNoiseMatrix(size, size, randomSeed, cellSize, 1);

        perlinMap.setPerlinMapList(map);
        perlinMap.setName(name);
        System.out.println(perlinMap);

        return PerlinMapMapper.toDtoWithData(perlinMapRepository.save(perlinMap));
    }
}