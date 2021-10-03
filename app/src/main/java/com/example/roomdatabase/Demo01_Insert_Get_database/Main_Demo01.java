package com.example.roomdatabase.Demo01_Insert_Get_database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.Demo01_Insert_Get_database.database.UserDatabase;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

public class Main_Demo01 extends AppCompatActivity {

    private EditText edtUsername, edtAddress;
    private Button btnAddUser;
    private RecyclerView recyclerView;
    UserAdapter adapter;

    List<User> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo01);

        edtUsername = findViewById(R.id.demo01_edt_username);
        edtAddress = findViewById(R.id.demo01_edt_address);
        recyclerView = findViewById(R.id.demo01_recyclerView);
        btnAddUser = findViewById(R.id.demo01_btn_add_user);



        arrayList = new ArrayList<>();

        adapter = new UserAdapter();
        adapter.setData(arrayList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData();

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });
    }

    private void addUser() {
        String strUsername = edtUsername.getText().toString().trim();
        String strAddress = edtAddress.getText().toString().trim();

        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return;
        }

        User user = new User(strUsername, strAddress);
        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this, "Add user successful", Toast.LENGTH_SHORT).show();

        edtUsername.setText("");
        edtAddress.setText("");
        hideSoftKeyboard();

        // Lấy dữ liệu và hiện ra RecyclerView
        loadData();
    }

    private void loadData(){
        arrayList = UserDatabase.getInstance(this).userDAO().getListUser();
        adapter.setData(arrayList);
    }

    public void hideSoftKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }


}