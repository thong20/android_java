package com.example.roomdatabase.Demo03_Delete_Search_database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    // Khởi tạo interface để lắng nghe sự kiện
    // onClick trên button "Update"
    private IClickItemUser iClickItemUser;
    public interface IClickItemUser{
        void updateUser(User user);
        void deleteUser(User user);
    }

    List<com.example.roomdatabase.Demo03_Delete_Search_database.User> listUser;

    public void setData(List<com.example.roomdatabase.Demo03_Delete_Search_database.User> list){
        this.listUser = list;
        notifyDataSetChanged();
    }

    public UserAdapter(IClickItemUser iClickItemUser) {
        this.iClickItemUser = iClickItemUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_user_demo03, parent, false);

        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = listUser.get(position);
        if(user != null){
            holder.username.setText(user.getUsername());
            holder.address.setText(user.getAddress());

            // EVENT UPDATE
            holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickItemUser.updateUser(user);
                }
            });

            // EVENT DELETE
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickItemUser.deleteUser(user);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(listUser != null){
            return listUser.size();
        }
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{

        TextView username, address;
        Button btnUpdate, btnDelete;

        public UserViewHolder(@NonNull View v) {
            super(v);

            username = v.findViewById(R.id.demo03_tv_username);
            address = v.findViewById(R.id.demo03_tv_address);

            btnUpdate = v.findViewById(R.id.demo03_btn_update);
            btnDelete = v.findViewById(R.id.demo03_btn_delete);

        }
    }
}
