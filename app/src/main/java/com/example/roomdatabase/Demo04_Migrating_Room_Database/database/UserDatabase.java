package com.example.roomdatabase.Demo04_Migrating_Room_Database.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdatabase.Demo04_Migrating_Room_Database.User;
import com.example.roomdatabase.Demo04_Migrating_Room_Database.database.UserDAO;

@Database(entities = {User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {

    static Migration migration_from_1_to_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user ADD COLUMN year TEXT");
        }
    };

    private static final String DATABASE_NAME = "user.db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserDatabase.class,
                    DATABASE_NAME
                )
                .allowMainThreadQueries() // cho phép setting Query trên Main Thread
                .addMigrations(migration_from_1_to_2)
                .build();
        }

        return instance;
    }

    // DAO
    public abstract UserDAO userDAO();

}
