package com.example.nfccardemulation.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.nfccardemulation.entities.Cost;
import com.example.nfccardemulation.entities.CostsOfShop;

import java.util.List;

@Dao
public interface CostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserCosts(Cost... costs);

    @Query("select * from costs where card_id = :cardId group by date")
    List<Cost> getAllCostsOfCard(int cardId);

    @Query("select * from costs group by date")
    List<Cost> getAllCosts();

    @Query("select price, s.name from costs join shops as s on shop_id == s.id group by shop_id")
    List<CostsOfShop> getCostsOfShop();



}
