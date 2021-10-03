package com.example.roomdatabase.Demo01_Insert_Get_database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> listUser;

    public void setData(List<User> list){
        this.listUser = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_user_demo01, parent, false);

        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = listUser.get(position);
        if(user != null){
            holder.username.setText(user.getUsername());
            holder.address.setText(user.getAddress());
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

        TextView username;
        TextView address;

        public UserViewHolder(@NonNull View v) {
            super(v);

            username = v.findViewById(R.id.demo01_tv_username);
            address = v.findViewById(R.id.demo01_tv_address);
        }
    }
}
