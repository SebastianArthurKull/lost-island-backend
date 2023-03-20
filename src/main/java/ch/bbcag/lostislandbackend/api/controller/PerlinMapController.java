package ch.bbcag.lostislandbackend.api.controller;

import ch.bbcag.lostislandbackend.api.data.dto.PerlinMapWithDataDto;
import ch.bbcag.lostislandbackend.api.service.PerlinMapService;
import ch.bbcag.lostislandbackend.socket.WebSocketModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/map")
public class PerlinMapController {

    private final PerlinMapService perlinMapService;
    private final WebSocketModule webSocketModule;

    @Autowired
    public PerlinMapController(PerlinMapService perlinMapService, WebSocketModule webSocketModule) {
        this.perlinMapService = perlinMapService;
        this.webSocketModule = webSocketModule;
    }

    @GetMapping(path = "")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(perlinMapService.getAll());
        } catch (EntityNotFoundException element) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            PerlinMapWithDataDto perlinMapWithDataDto = perlinMapService.getById(id);
            if (!webSocketModule.isEventListenerForMapAdded(id)) {
                webSocketModule.AddEventListener(id);
            }
            return ResponseEntity.ok(perlinMapWithDataDto);
        } catch (EntityNotFoundException element) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/create/{name}/{size}/{randomSeed}/{cellSize}")
    public ResponseEntity<?> createMap(@PathVariable String name, @PathVariable Integer size, @PathVariable Integer randomSeed, @PathVariable Integer cellSize) {
        try {

            PerlinMapWithDataDto perlinMapWithDataDto;
            perlinMapWithDataDto = perlinMapService.createMap(name, size, randomSeed, cellSize);
            webSocketModule.AddEventListener(perlinMapWithDataDto.getId());
            return ResponseEntity.ok(perlinMapWithDataDto);
        } catch (EntityNotFoundException element) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
