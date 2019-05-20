package com.example.nfccardemulation.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "shops")
public class Shop {
    @PrimaryKey
    public int id;
    public String name;
    public int category;
}
