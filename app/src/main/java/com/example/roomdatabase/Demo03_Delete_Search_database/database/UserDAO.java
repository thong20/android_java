package com.example.roomdatabase.Demo03_Delete_Search_database.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.Demo03_Delete_Search_database.User;

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

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAllUser();

    // _______________________________________________ || là toán tử nối chuỗi
    @Query("SELECT * FROM user WHERE username LIKE '%' || :name || '%'")
    List<User> searchUser(String name);
}
