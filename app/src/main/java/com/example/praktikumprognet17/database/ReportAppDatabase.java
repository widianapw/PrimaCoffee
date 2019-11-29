package com.example.praktikumprognet17.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.praktikumprognet17.dao.ReportDAO;
import com.example.praktikumprognet17.database.entity.Report;

@Database(entities = {Report.class}, version = 1, exportSchema = false)
//@TypeConverters({Converters.class})
public abstract class ReportAppDatabase extends RoomDatabase {
    public abstract ReportDAO reportDAO();
    private static volatile ReportAppDatabase INSTANCE;

    public static ReportAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReportAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ReportAppDatabase.class, "tes2")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
