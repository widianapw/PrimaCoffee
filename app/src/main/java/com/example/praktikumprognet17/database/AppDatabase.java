package com.example.praktikumprognet17.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.praktikumprognet17.dao.ReportDAO;
import com.example.praktikumprognet17.database.entity.Report;

@Database(entities = {Report.class}, version = 1, exportSchema = false)
//@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReportDAO reportDAO();
    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "tes2").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
