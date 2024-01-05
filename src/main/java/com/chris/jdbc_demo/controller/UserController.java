package com.chris.jdbc_demo.controller;

import com.chris.jdbc_demo.service.JDBCClientUserServiceImpl;
import com.chris.jdbc_demo.service.JDBCTemplateUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.chris.jdbc_demo.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    JDBCClientUserServiceImpl userService;
    @GetMapping
    ResponseEntity<Collection<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping("/{id}")
    void update(@RequestBody User user, @PathVariable String id) {
        userService.update(user, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable String id) {
        userService.delete(id);
    }



}
