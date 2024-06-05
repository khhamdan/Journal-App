package com.hamdan.journalapp.controller;


import com.hamdan.journalapp.entity.User;
import com.hamdan.journalapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll()
    {
        return userService.getAll();
    }
    @PostMapping
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName)
    {
        User userInDb= userService.findByUserName(userName);
        if(userInDb != null)
        {
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }










//    @DeleteMapping
//    public ResponseEntity<?> deleteUser(@RequestBody User user)
//    {
//        userService.deleteById();
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }





}
