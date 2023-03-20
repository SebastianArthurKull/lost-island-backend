package ch.bbcag.lostislandbackend.api.controller;


import ch.bbcag.lostislandbackend.api.data.dto.UserDTO;
import ch.bbcag.lostislandbackend.api.data.dto.UserSignUpDTO;
import ch.bbcag.lostislandbackend.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(userService.getById(id));
        } catch (EntityNotFoundException element) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "sign-up", consumes = "application/json")
    public ResponseEntity<?> create(@RequestBody UserSignUpDTO userDTO) {
        try {
            return ResponseEntity.status(201).body(userService.create(userDTO));
        } catch (ConstraintViolationException element) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(path = "{id}", consumes = "application/json")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.update(userDTO, id));
        } catch (ConstraintViolationException element) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException element) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
