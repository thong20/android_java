package com.example.roomdatabase.Demo02_Check_Update_database;

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
    }

    List<User> listUser;

    public void setData(List<User> list){
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
        View v = layoutInflater.inflate(R.layout.item_user_demo02, parent, false);

        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = listUser.get(position);
        if(user != null){
            holder.username.setText(user.getUsername());
            holder.address.setText(user.getAddress());

            holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iClickItemUser.updateUser(user);
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

        TextView username;
        TextView address;
        Button btnUpdate;

        public UserViewHolder(@NonNull View v) {
            super(v);

            username = v.findViewById(R.id.demo02_tv_username);
            address = v.findViewById(R.id.demo02_tv_address);

            btnUpdate = v.findViewById(R.id.demo02_btn_update);

        }
    }
}
