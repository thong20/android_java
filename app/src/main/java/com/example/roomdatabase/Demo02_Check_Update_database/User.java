package com.example.roomdatabase.Demo02_Check_Update_database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

// Khai báo tên của table trong CSDL
// ví dụ: table "user"
@Entity(tableName = "user")
public class User implements Serializable {

    /** Khai báo khóa chính trong CSDL **/
    @PrimaryKey(autoGenerate = true)
    int id;

    /**
    * Giả sử ta có 1 CSDL có 1 table User, và trong table này<br />
    * có 1 column tên là "user_name", nhưng trong .java ta không thix tên<br />
    * biến cho cột này là "user_name" mà là "username"<br />
    * thì ta sử dụng cú pháp @ColumnInfo(name="user_name")<br />
    * sau đó khai báo tên biến cho cột này là "username"<br />
    **/
//    @ColumnInfo(name = "user_name")
    String username;
    String address;

    public User(String username, String address) {
        this.username = username;
        this.address = address;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
