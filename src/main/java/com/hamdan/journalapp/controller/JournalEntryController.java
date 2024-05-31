package com.hamdan.journalapp.controller;

import com.hamdan.journalapp.entity.JournalEntries;
import com.hamdan.journalapp.entity.User;
import com.hamdan.journalapp.service.JournalEntityService;
import com.hamdan.journalapp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
//Special Type of component where We hit entry
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntityService journalEntityService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName)
    {
        User user = userService.findByUserName(userName);
        List<JournalEntries> all =  user.getJournalEntries();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntries> createEntity(@RequestBody JournalEntries myEntry, @PathVariable String userName)
    {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalEntityService.saveEntry(myEntry,userName);
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

//    @PutMapping("/id/{id}")
//    public ResponseEntity<JournalEntries> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntries newEntry)
//    {
//        JournalEntries oldEntry = journalEntityService.findById(id).orElse(null);
//
//        if(oldEntry != null)
//        {
//            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle() );
//            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
//            journalEntityService.saveEntry(oldEntry);
//            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
