package com.hamdan.journalapp.controller;

import com.hamdan.journalapp.entity.JournalEntries;
import com.hamdan.journalapp.service.JournalEntityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
//Special Type of component where We hit entry
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntityService journalEntityService;

    @GetMapping
    public List<JournalEntries> getAll()
    {
        return journalEntityService.getAll();
    }

    @PostMapping
    public JournalEntries createEntity(@RequestBody JournalEntries myEntry)
    {
        myEntry.setDate(LocalDateTime.now());
        journalEntityService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myId}")
    public JournalEntries getJournalEntryById(@PathVariable ObjectId myId)
    {
        return journalEntityService.findById(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myId)
    {
        journalEntityService.deleteById(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public JournalEntries updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntries newEntry)
    {
        JournalEntries oldEntry = journalEntityService.findById(id).orElse(null);

        if(oldEntry != null)
        {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle() );
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
        }

        journalEntityService.saveEntry(oldEntry);
        return oldEntry;

    }

}
