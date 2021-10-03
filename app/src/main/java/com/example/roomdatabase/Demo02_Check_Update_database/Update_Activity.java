package com.example.roomdatabase.Demo02_Check_Update_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.Demo02_Check_Update_database.database.UserDatabase;
import com.example.roomdatabase.R;

import java.util.List;

public class Update_Activity extends AppCompatActivity {

    private EditText edtUsername, edtAddress;
    private Button btnUpdateUser;

    private User mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_demo02);

        edtUsername = findViewById(R.id.demo02_edt_username);
        edtAddress = findViewById(R.id.demo02_edt_address);
        btnUpdateUser = findViewById(R.id.demo02_btn_update_user);

        mUser = (User) getIntent().getExtras().get("object_user");
        if(mUser != null){
            edtUsername.setText(mUser.getUsername());
            edtAddress.setText(mUser.getAddress());
        }

        btnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

    }

    private void updateUser() {
        String strUsername = edtUsername.getText().toString().trim();
        String strAddress = edtAddress.getText().toString().trim();

        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return;
        }

        // UPDATE USER
        mUser.setUsername(strUsername);
        mUser.setAddress(strAddress);

        UserDatabase.getInstance(this).userDAO().updateUser(mUser);
        Toast.makeText(this, "Update user successful", Toast.LENGTH_SHORT).show();

        // Truyền dữ liệu ngược lại MainActivity
        Intent intent = new Intent();
        setResult(Activity.RESULT_OK, intent);
        finish();

    }
}