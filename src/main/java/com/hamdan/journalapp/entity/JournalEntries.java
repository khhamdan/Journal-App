package com.hamdan.journalapp.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collation = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntries
{
    @Id
    private ObjectId id;
    @NonNull // means can't be null
    private String title;
    private String content;
    private LocalDateTime date;

}
