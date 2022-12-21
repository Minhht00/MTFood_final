package com.itplus.mtfood.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.mtfood.Activity.CategoryActivity;
import com.itplus.mtfood.Model.Category;
import com.itplus.mtfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> categoryList;
    private Context context;

    public CategoryAdapter(@NonNull Context context, @NonNull List<Category> category) {
        this.context = context;
        this.categoryList = category;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new CategoryAdapter.CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);

//        holder.id.setText(category.getId());
//        holder.img.setText(category.getImg());
        holder.name.setText(category.getName());
        String picUrl = context.getResources().getString(R.string.link_img)+category.getImg();
        Picasso.get().load(picUrl).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("category_id",String.valueOf(category.getId()));
                context.startActivity(intent);
            }
        });
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.img);
    }

//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        Category category = categoryList.get(position);
//        if (category == null) {
//            return;
//        }
//        holder.name.setText(category.getName());
////        String picUrl = "";
////        switch (position) {
////            case 0: {
////                picUrl = "cat_1";
////                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background1));
////                break;
////            }
////            case 1: {
////                picUrl = "cat_2";
////                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background2));
////                break;
////            }
////            case 2: {
////                picUrl = "cat_3";
////                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background3));
////                break;
////            }
////            case 3: {
////                picUrl = "cat_4";
////                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background4));
////                break;
////            }
////            case 4: {
////                picUrl = "cat_5";
////                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.cat_background5));
////                break;
////            }
////        }
////        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
////
////        Glide.with(holder.itemView.getContext())
////                .load(drawableResourceId)
////                .into(holder.categoryPic);
//    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, catePic;
        ImageView img;
        ImageView categoryPic;
//        ConstraintLayout mainLayout;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            img = itemView.findViewById(R.id.categoryPic);
            name = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
//            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
