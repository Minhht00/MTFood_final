package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.itplus.mtfood.Adapter.CategoryAdapter;
import com.itplus.mtfood.Adapter.ProductAdapter;
import com.itplus.mtfood.Adapter.ProductByIdAdapter;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Category;
import com.itplus.mtfood.Model.User;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.APIUtils;
import com.itplus.mtfood.Remote.CategoryService;
import com.itplus.mtfood.Remote.ProductService;
import com.itplus.mtfood.Remote.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerViewProductList;
    private ProductService productService;
    private UserService userService;
    List<CartDetail> listProduct;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        productService = APIUtils.getFoodService();
        userService = APIUtils.getUserService();
        btnBack = findViewById(R.id.btnBack);
        recyclerViewProduct();
        Call<List<User>> call = userService.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                btnBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(CategoryActivity.this, MainActivity.class);
                        intent.putExtra("email", response.body().get(0).getName());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


    }

    private void recyclerViewProduct() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewProductList = findViewById(R.id.recyclerViewProduct);
        recyclerViewProductList.setLayoutManager(linearLayoutManager);

        Bundle extras = getIntent().getExtras();

        Call<List<CartDetail>> call = productService.getAllProductById(Integer.parseInt(extras.getString("category_id")));
        call.enqueue(new Callback<List<CartDetail>>() {
            @Override
            public void onResponse(Call<List<CartDetail>> call, Response<List<CartDetail>> response) {
                listProduct = response.body();
                ProductByIdAdapter productAdapter = new ProductByIdAdapter(CategoryActivity.this, listProduct);
                recyclerViewProductList.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CartDetail>> call, Throwable t) {
                Toast.makeText(CategoryActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
            }
        });
    }
}