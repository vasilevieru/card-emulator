//package com.example.nfccardemulation.database;
//
//import android.content.Context;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import com.example.nfccardemulation.entities.Card;
//import com.example.nfccardemulation.entities.Cost;
//import com.example.nfccardemulation.entities.Shop;
//import com.example.nfccardemulation.entities.User;
//import com.example.nfccardemulation.interfaces.CardDao;
//import com.example.nfccardemulation.interfaces.UserDao;
//
//@Database(entities = {User.class, Shop.class, Card.class, Cost.class}, version = 1)
//public abstract class AppDatabase extends RoomDatabase {
//
//    private static AppDatabase INSTANCE;
//
//    public abstract CardDao cardDao();
//    public abstract UserDao userDao();
//
//    public static AppDatabase getAppDatabse(Context context) {
//        if (INSTANCE == null) {
//            INSTANCE =
//                    Room.databaseBuilder(context.getApplicationContext(),
//                            AppDatabase.class, "CardEmulation")
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return INSTANCE;
//    }
//
//    public static void destroyInstance() {
//        INSTANCE = null;
//    }
//}
