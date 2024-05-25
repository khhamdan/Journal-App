package com.hamdan.journalapp.controller;

import com.hamdan.journalapp.entity.JournalEntries;
import com.hamdan.journalapp.service.JournalEntityService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
//Special Type of component where We hit entry
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntityService journalEntityService;

    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<JournalEntries> all =  journalEntityService.getAll();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntries> createEntity(@RequestBody JournalEntries myEntry)
    {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntityService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED) ;
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntries> getJournalEntryById(@PathVariable ObjectId myId)
    {
        Optional<JournalEntries> journalEntry = journalEntityService.findById(myId);
        if(journalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId)
    {
        journalEntityService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntries> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntries newEntry)
    {
        JournalEntries oldEntry = journalEntityService.findById(id).orElse(null);

        if(oldEntry != null)
        {
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle() );
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalEntityService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
