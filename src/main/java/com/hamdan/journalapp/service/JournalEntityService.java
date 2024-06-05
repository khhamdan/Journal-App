package com.hamdan.journalapp.service;


import com.hamdan.journalapp.entity.JournalEntries;
import com.hamdan.journalapp.entity.User;
import com.hamdan.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public void saveEntry(JournalEntries journalEntry, String userName)
    {
        journalEntry.setDate(LocalDateTime.now());
        JournalEntries saved = journalEntryRepository.save(journalEntry);
        User user = userService.findByUserName(userName);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
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
