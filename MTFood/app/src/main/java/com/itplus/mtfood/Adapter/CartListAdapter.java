package com.itplus.mtfood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Product;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.ChangeNumberItemsListener;
import com.itplus.mtfood.Remote.ManagementCart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.CartViewHolder>{
    private List<CartDetail> productList;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(List<CartDetail> productList, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.productList = productList;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartListAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new CartViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(productList.get(position).getName());
        holder.feeEachItem.setText(String.valueOf(productList.get(position).getUnitPrice()));
        holder.totalEachItem.setText(String.valueOf(Math.round((productList.get(position).getNumberInCart()* productList.get(position).getUnitPrice()))));
        holder.num.setText(String.valueOf(productList.get(position).getNumberInCart()));

        String picUrl = "http://192.168.1.108:8080/product/files/"+productList.get(position).getPic();
        Picasso.get().load(picUrl).into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(productList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(productList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCart);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
