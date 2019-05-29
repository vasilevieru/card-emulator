package com.example.nfccardemulation.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.nfccardemulation.entities.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCars(Card... card);

    @Query("select * from cards where user_id = :id")
    List<Card> getAllCards(int id);

}
