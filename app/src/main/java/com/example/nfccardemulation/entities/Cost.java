package com.example.nfccardemulation.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "costs", primaryKeys = {"shopId", "cardId"},
        foreignKeys = {
                @ForeignKey(entity = Shop.class,
                        parentColumns = "id",
                        childColumns = "shop_id"),
                @ForeignKey(entity = Card.class,
                        parentColumns = "id",
                        childColumns = "card_id")
        })
public class Cost {
    @PrimaryKey
    public int id;
    public double price;
    @ColumnInfo(name = "shop_id")
    public int shopId;
    @ColumnInfo(name = "card_id")
    public int cardId;

}
