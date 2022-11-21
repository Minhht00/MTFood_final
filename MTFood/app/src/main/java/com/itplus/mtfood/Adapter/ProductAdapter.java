package com.itplus.mtfood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.mtfood.Activity.ShowDetailActivity;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Product;
import com.itplus.mtfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<CartDetail> productList;
    private Context context;

    public ProductAdapter(@NonNull Context context, @NonNull List<CartDetail> product) {
        this.context = context;
        this.productList = product;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ProductAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartDetail product = productList.get(position);
//        holder.id.setText(category.getId());
//        holder.img.setText(category.getImg());
        holder.name.setText(product.getName());
        holder.fee.setText(String.valueOf(product.getUnitPrice()));
        String picUrl = context.getResources().getString(R.string.link_img)+product.getPic();
        Picasso.get().load(picUrl).into(holder.pic);
//        Picasso.get().load(product.getPic()).into(holder.pic);
//        System.out.println(holder.pic);
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
//        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);



        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", productList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, fee;
        ImageView pic;
        TextView btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            btnAdd = itemView.findViewById(R.id.btnAdd);
        }
    }


}
