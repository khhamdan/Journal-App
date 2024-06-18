package com.hamdan.journalapp.controller;

import com.hamdan.journalapp.entity.User;
import com.hamdan.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String healthCheck()
    {
        return "Ok";
    }
    @GetMapping
    public List<User> getAll()
    {
        return userService.getAll();
    }
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }

}
