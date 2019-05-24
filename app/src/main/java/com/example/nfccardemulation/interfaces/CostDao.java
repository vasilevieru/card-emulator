package com.example.nfccardemulation.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import com.example.nfccardemulation.entities.Cost;

@Dao
public interface CostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserCosts(Cost... costs);
}
