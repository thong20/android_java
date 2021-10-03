package com.example.roomdatabase.Demo04_Migrating_Room_Database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.Demo04_Migrating_Room_Database.database.UserDatabase;
import com.example.roomdatabase.R;

import java.util.ArrayList;
import java.util.List;

public class Main_Demo04 extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    private EditText edtUsername, edtAddress, edtSearch, edtYear;
    private Button btnAddUser;
    private TextView tvDeleteAll;
    private RecyclerView recyclerView;
    UserAdapter adapter;

    List<User> mListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_demo04);

        edtSearch = findViewById(R.id.demo04_edt_search);
        edtUsername = findViewById(R.id.demo04_edt_username);
        edtAddress = findViewById(R.id.demo04_edt_address);
        edtYear = findViewById(R.id.demo04_edt_year);
        recyclerView = findViewById(R.id.demo04_recyclerView);
        btnAddUser = findViewById(R.id.demo04_btn_add_user);
        tvDeleteAll = findViewById(R.id.demo04_tv_delete_all);

        mListUser = new ArrayList<>();

        // HANDLE EVENT ONCLICK FROM CLASS UserAdapter
        adapter = new UserAdapter(new UserAdapter.IClickItemUser() {
            @Override
            public void updateUser(User user) {
                clickUpdateUser(user);
            }

            @Override
            public void deleteUser(User user) {
                clickDeleteUser(user);
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

        tvDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDeleteAllUser();
            }
        });

        edtSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    handleSearchUser();
                }

                return false;
            }
        });
    }

    // nhận dữ liệu từ Update_Activity.java gửi lại
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            loadData();
        }
    }


    private void addUser() {
        String strUsername = edtUsername.getText().toString().trim();
        String strAddress = edtAddress.getText().toString().trim();
        String strYear = edtYear.getText().toString().trim();

        if(TextUtils.isEmpty(strUsername) || TextUtils.isEmpty(strAddress)){
            return;
        }

        User user = new User(strUsername, strAddress, strYear);
        if (isUserExist(user)){
            Toast.makeText(this, "User is exist", Toast.LENGTH_SHORT).show();
            return;
        }
        UserDatabase.getInstance(this).userDAO().insertUser(user);
        Toast.makeText(this, "Add user successful", Toast.LENGTH_SHORT).show();

        edtUsername.setText("");
        edtAddress.setText("");
        edtYear.setText("");
        hideSoftKeyboard();

        // Lấy dữ liệu và hiện ra RecyclerView
        loadData();
    }

    public void hideSoftKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

    }


    private boolean isUserExist(@NonNull User user){
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

    private void clickDeleteUser(User user) {
        // SHOW DIALOG
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // DELETE USER
                        UserDatabase.getInstance(Main_Demo04.this).userDAO().deleteUser(user);
                        Toast.makeText(Main_Demo04.this, "Delete successful", Toast.LENGTH_SHORT).show();

                        // Refresh RecyclerView
                        loadData();
                    }
                })
                .setNegativeButton("NO", null)
                .show();
    }

    private void clickDeleteAllUser(){
        // SHOW DIALOG
        new AlertDialog.Builder(this)
            .setTitle("Confirm Delete All User")
            .setMessage("Are You Sure?")
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Delete all
                    UserDatabase.getInstance(Main_Demo04.this).userDAO().deleteAllUser();
                    Toast.makeText(Main_Demo04.this, "Deleted successful", Toast.LENGTH_SHORT).show();

                    // Refresh RecyclerView
                    loadData();
                }
            })
            .setNegativeButton("No", null)
            .show();
    }

    private void handleSearchUser(){
        String strKeyword = edtSearch.getText().toString().trim();
        mListUser = new ArrayList<>();
        mListUser = UserDatabase.getInstance(this).userDAO().searchUser(strKeyword);

        adapter.setData(mListUser);
        hideSoftKeyboard();

    }

    private void loadData(){
        mListUser = UserDatabase.getInstance(this).userDAO().getListUser();
        adapter.setData(mListUser);
    }
}