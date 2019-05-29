package com.example.nfccardemulation.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.nfccardemulation.converters.Converters;
import com.example.nfccardemulation.entities.Card;
import com.example.nfccardemulation.entities.Cost;
import com.example.nfccardemulation.entities.Shop;
import com.example.nfccardemulation.entities.User;
import com.example.nfccardemulation.interfaces.CardDao;
import com.example.nfccardemulation.interfaces.CostDao;
import com.example.nfccardemulation.interfaces.ShopDao;
import com.example.nfccardemulation.interfaces.UserDao;

@Database(entities = {User.class, Shop.class, Card.class, Cost.class}, version = 5)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract CardDao cardDao();
    public abstract UserDao userDao();
    public abstract CostDao costDao();
    public abstract ShopDao shopDao();

    public static AppDatabase getAppDatabse(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "CardEmulation")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
