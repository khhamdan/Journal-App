package com.hamdan.journalapp.repository;

import com.hamdan.journalapp.entity.JournalEntries;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntries, ObjectId> {
}
