package com.hamdan.journalapp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class User
{
    @Id
    private ObjectId id;
    @Indexed(unique = true) //filter out the field
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef // Due to this annotation
    private List<JournalEntries> journalEntries = new ArrayList<>();
    private List<String> roles;


    public List<JournalEntries> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntries> journalEntries) {
        this.journalEntries = journalEntries;
    }
}
