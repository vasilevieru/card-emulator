package com.example.nfccardemulation.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public int id;
    public String name;
    public String email;
    public String password;
}