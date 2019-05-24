package com.example.nfccardemulation.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.example.nfccardemulation.entities.Shop;

@Dao
public interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShops(Shop... shops);
}
