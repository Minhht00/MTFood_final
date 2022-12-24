package com.itplus.mtfood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itplus.mtfood.Activity.ShowDetailActivity;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Product;
import com.itplus.mtfood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {
    private List<CartDetail> productList;
    private List<CartDetail> productListOld;
    private Context context;

    public ProductAdapter(@NonNull Context context, @NonNull List<CartDetail> product) {
        this.context = context;
        this.productList = product;
        this.productListOld = product;
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String keyword = charSequence.toString();
                if (keyword.isEmpty()) {
                    productList = productListOld;
                } else {
                    List<CartDetail> list = new ArrayList<>();
                    for (CartDetail cd : productListOld) {
                        if (cd.getName().toLowerCase().contains(keyword.toLowerCase())) {
                            list.add(cd);
                        }
                    }
                    productList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = productList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                productList = (List<CartDetail>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
