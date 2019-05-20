package com.example.nfccardemulation.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "cards", foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"))
public class Card {
    @PrimaryKey
    public int id;
    public String number;
    public int cvv;
    public Date expiration;
    @ColumnInfo(name="user_id")
    public int userId;
}
