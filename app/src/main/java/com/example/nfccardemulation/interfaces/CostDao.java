package com.example.nfccardemulation.interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import com.example.nfccardemulation.entities.Cost;

@Dao
public interface CostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void inserCosts(Cost... costs);
}
