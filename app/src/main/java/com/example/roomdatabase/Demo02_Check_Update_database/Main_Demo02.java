package com.example.roomdatabase.Demo02_Check_Update_database;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.Demo02_Check_Update_database.User;
import com.example.roomdatabase.Demo02_Check_Update_database.UserAdapter;
import com.example.roomdatabase.Demo02_Check_Update_database.database.UserDatabase;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

// 15:20
public class Main_Demo02 extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private EditText edtUsername, edtAddress;
    private Button btnAddUser;
    private RecyclerView recyclerView;
    UserAdapter adapter;

    List<User> mListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo02);

        edtUsername = findViewById(R.id.demo02_edt_username);
        edtAddress = findViewById(R.id.demo02_edt_address);
        recyclerView = findViewById(R.id.demo02_recyclerView);
        btnAddUser = findViewById(R.id.demo02_btn_add_user);

        mListUser = new ArrayList<>();

        // HANDLE EVENT ONCLICK FROM CLASS UserAdapter
        adapter = new UserAdapter(new UserAdapter.IClickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }
        });
        adapter.setData(mListUser);
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
        if (isUserExist(user)){
            Toast.makeText(this, "User is exist", Toast.LENGTH_SHORT).show();
            return;
        }
        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this, "Add user successful", Toast.LENGTH_SHORT).show();

        edtUsername.setText("");
        edtAddress.setText("");
        hideSoftKeyboard();

        // Lấy dữ liệu và hiện ra RecyclerView
        loadData();
    }

    private void loadData(){
        mListUser = UserDatabase.getInstance(this).userDAO().getListUser();
        adapter.setData(mListUser);
    }

    public void hideSoftKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }


    private boolean isUserExist(User user){
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }

    private void clickUpdateUser(User user){
        Intent intent = new Intent(this, Update_Activity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        intent.putExtras(bundle);
        startActivityForResult(intent, MY_REQUEST_CODE);
    }

    // nhận dữ liệu từ Update_Activity.java gửi lại
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }
}