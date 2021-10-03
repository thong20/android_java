package com.example.roomdatabase.Demo01_Insert_Get_database.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.Demo01_Insert_Get_database.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

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
                .build();
        }

        return instance;
    }

    // DAO
    public abstract UserDAO userDAO();

}
