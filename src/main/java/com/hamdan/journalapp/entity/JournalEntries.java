package com.hamdan.journalapp.entity;


import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document("journal_entries")
@Setter
@Getter
public class JournalEntries
{
    @Id
    private ObjectId id;
    private String title;
    private String content;
    private LocalDateTime date;

}
