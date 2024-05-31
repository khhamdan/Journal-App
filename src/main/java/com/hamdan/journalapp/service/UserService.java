package com.hamdan.journalapp.service;

import com.hamdan.journalapp.entity.User;
import com.hamdan.journalapp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user)
    {
        userRepository.save(user);
    }

    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id)
    {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId myId)
    {
        userRepository.deleteById(myId);
    }

    public User findByUserName(String userName)
    {
        return userRepository.findByUserName(userName);
    }

}