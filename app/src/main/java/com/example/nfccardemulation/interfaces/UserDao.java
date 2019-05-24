package com.example.nfccardemulation.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.nfccardemulation.entities.User;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User... card);

    @Query("select * from users where email like :email")
    User selectUser(String email);
}
