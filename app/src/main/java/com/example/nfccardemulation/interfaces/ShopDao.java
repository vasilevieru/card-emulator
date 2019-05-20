package com.example.nfccardemulation.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import com.example.nfccardemulation.entities.Shop;

@Dao
public interface ShopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShops(Shop... shops);
}
