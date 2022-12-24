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

import com.itplus.mtfood.Activity.HisDetailActivity;
import com.itplus.mtfood.Activity.ShowDetailActivity;
import com.itplus.mtfood.Model.Cart;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    private List<Cart> cartList;
    private Context context;

    public HistoryAdapter(@NonNull Context context, @NonNull List<Cart> cart) {
        this.context = context;
        this.cartList = cart;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_history, parent, false);
        return new HistoryAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cart cart = cartList.get(position);
        if (cart.getStatus() == 1) {
        holder.txtId.setText("Mã đơn hàng: " + String.valueOf(cart.getOrderId()));
        holder.txtAddress.setText("Địa chỉ giao hàng: " + cart.getAddress());
        holder.txtAmount.setText("Tổng: " + String.valueOf(cart.getAmount()));
        holder.txtStatus.setText("Thành công");
        }


        holder.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), HisDetailActivity.class);
                intent.putExtra("orderId", String.valueOf(cart.getOrderId()));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtAddress, txtAmount, txtStatus;
        TextView btnShow;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnShow = itemView.findViewById(R.id.btnShow);
        }
    }
}
