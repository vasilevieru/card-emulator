package com.example.nfccardemulation.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "costs",
        foreignKeys = {
                @ForeignKey(entity = Shop.class,
                        parentColumns = "id",
                        childColumns = "shop_id"),
                @ForeignKey(entity = Card.class,
                        parentColumns = "id",
                        childColumns = "card_id")
        })
public class Cost {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public double price;
    @ColumnInfo(name = "shop_id")
    public int shopId;
    @ColumnInfo(name = "card_id")
    public int cardId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
