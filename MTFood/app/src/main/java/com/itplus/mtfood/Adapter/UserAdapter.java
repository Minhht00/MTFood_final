package com.itplus.mtfood.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itplus.mtfood.Activity.MainActivity;
import com.itplus.mtfood.Model.User;
import com.itplus.mtfood.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    MainActivity context;
    int layout;
    List<User> list;

    public UserAdapter(MainActivity context, int layout, List<User> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);
        TextView txtName = view.findViewById(R.id.txtName);
        ImageView img = view.findViewById(R.id.imageView);

        User user = list.get(i);

        txtName.setText(user.getName());

        return view;
    }
}
