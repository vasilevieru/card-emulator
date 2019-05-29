package com.example.nfccardemulation.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shops")
public class Shop {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
