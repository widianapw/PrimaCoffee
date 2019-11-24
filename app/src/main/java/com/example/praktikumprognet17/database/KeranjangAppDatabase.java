package com.example.praktikumprognet17.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.praktikumprognet17.dao.KeranjangDAO;
import com.example.praktikumprognet17.database.entity.Keranjang;

@Database(entities = {Keranjang.class}, version = 1)
public abstract class KeranjangAppDatabase extends RoomDatabase {
    public abstract KeranjangDAO keranjangDAO();

//
//    private static volatile KeranjangAppDatabase INSTANCE;
//
//    static KeranjangAppDatabase getDatabase(final Context context) {
//        if (INSTANCE == null) {
//            synchronized (KeranjangAppDatabase.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//                            KeranjangAppDatabase.class, "rest_api")
//                            .build();
//                }
//            }
//        }
//        return INSTANCE;
//    }
}
