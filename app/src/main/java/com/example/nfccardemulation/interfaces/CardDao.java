package com.example.nfccardemulation.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.nfccardemulation.entities.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCars(Card... card);

    @Query("select * from cards")
    List<Card> getAllCards();

}
