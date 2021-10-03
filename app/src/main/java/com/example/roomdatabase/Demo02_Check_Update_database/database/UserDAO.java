package com.example.roomdatabase.Demo02_Check_Update_database.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.Demo02_Check_Update_database.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();

    @Query("SELECT * FROM user WHERE username = :username")
    List<User> checkUser(String username);

    @Update
    void updateUser(User user);
}
