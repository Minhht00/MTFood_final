package com.itplus.mtfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.mtfood.Activity.MainActivity;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.User;
import com.itplus.mtfood.R;

import java.util.List;

public class UserAdapter {
//    private List<User> userList;
//    private Context context;
//    public UserAdapter(@NonNull Context context, @NonNull List<User> user) {
//        this.context = context;
//        this.userList = user;
//    }
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_productcate, parent, false);
//        return new UserAdapter.ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        User user = userList.get(position);
//        holder.
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView name;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//        }
//    }
//
//    MainActivity context;
//    int layout;
//    List<User> list;
//
//    public UserAdapter(MainActivity context, int layout, List<User> list) {
//        this.context = context;
//        this.layout = layout;
//        this.list = list;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        view = layoutInflater.inflate(layout, null);
//        TextView txtName = view.findViewById(R.id.txtName);
//        ImageView img = view.findViewById(R.id.imageView);
//
//        User user = list.get(i);
//
//        txtName.setText(user.getName());
//
//        return view;
//    }
}
