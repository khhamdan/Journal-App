package com.hamdan.journalapp.service;


import com.hamdan.journalapp.entity.JournalEntries;
import com.hamdan.journalapp.entity.User;
import com.hamdan.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntityService
{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntries journalEntry, String userName)
    {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntries saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }catch (Exception e)
        {
            throw new RuntimeException("An error occurred while saving the entry.",e);
        }

    }
    public void saveEntry(JournalEntries journalEntry)
    {
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntries> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntries> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String userName)
    {
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(id);

    }

}
