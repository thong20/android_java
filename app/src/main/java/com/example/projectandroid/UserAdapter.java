package com.example.projectandroid;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {
    Context context;
    ArrayList<User> arrayList;
    ArrayList<User> arrayListOld;

    public UserAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayListOld = arrayList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.item_recyclerview, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.ViewHolder holder, int position) {
        User user = arrayList.get(position);
        holder.tv_name.setText(user.getName());
        holder.tv_age.setText(String.valueOf(user.getAge()));
    }

    @Override
    public int getItemCount() {
        if(arrayList != null){
            return arrayList.size();
        }

        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                // get string from user type
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    arrayList = arrayListOld;
                }else{
                    List<User> list = new ArrayList<>();
                    for(User user : arrayListOld){
                        if(user.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(user);
                        }
                    }
                    arrayList = (ArrayList<User>) list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = arrayList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arrayList = (ArrayList<User>) filterResults.values;

                // refresh adapter
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name;
        TextView tv_age;

        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_age = itemView.findViewById(R.id.tv_age);
        }
    }

}
