package com.hamdan.journalapp.service;


import com.hamdan.journalapp.entity.JournalEntries;
import com.hamdan.journalapp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntityService
{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntries journalEntity)
    {
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntries> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntries> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId myId)
    {
        journalEntryRepository.deleteById(myId);
    }

}
