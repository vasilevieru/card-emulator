package com.example.nfccardemulation.entities;


import androidx.room.*;

import java.util.Date;

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
    public float price;
    @ColumnInfo(name = "shop_id")
    public int shopId;

    public Date date;

    @ColumnInfo(name = "card_id")
    public int cardId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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
