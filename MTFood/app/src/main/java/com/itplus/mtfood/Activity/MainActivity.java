package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itplus.mtfood.Adapter.CategoryAdapter;
import com.itplus.mtfood.Adapter.ProductAdapter;
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

public class MainActivity extends AppCompatActivity {
    TextView txtName;
    ImageView categoryPic;
    User user;
    UserService userService;
    CategoryService categoryService;
    ProductService productService;
    List<User> listUser;
    List<Category> listCate;
    List<CartDetail> listProduct;
    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userService = APIUtils.getUserService();
        categoryService = APIUtils.getCategoryService();
        productService = APIUtils.getFoodService();
        Intent data = getIntent();
        String message = data.getStringExtra("email");
        txtName = findViewById(R.id.txtName);
        txtName.setText("XIN CHÀO " + message);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();


    }

    private void bottomNavigation() {
        Bundle extras = getIntent().getExtras();
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout userBtn = findViewById(R.id.profileBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CartListActivity.class);
                startActivity(intent);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<User>> call = userService.getUserById(Integer.parseInt(extras.getString("id")));
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        listUser = response.body();
                        Intent intent = new Intent(MainActivity.this, UserActivity.class);
                        intent.putExtra("id", String.valueOf(listUser.get(0).getId()));
                        intent.putExtra("email", String.valueOf(listUser.get(0).getName()));
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
                    }
                });

            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerViewCategory);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        Call<List<Category>> call = categoryService.getAllCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                listCate = response.body();
                CategoryAdapter categoryAdapter = new CategoryAdapter(MainActivity.this, listCate);
                recyclerViewCategoryList.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
            }
        });


//        ArrayList<Category> category = new ArrayList<>();
//        category.add(new Category("Pizza","cat_1"));
//        category.add(new Category("Burger","cat_2"));
//        category.add(new Category("Hotdog","cat_3"));
//        category.add(new Category("Drink","cat_4"));
//        category.add(new Category("Donut","cat_5"));
//        category.add(new Category("Donut","cat_5"));
//        adapter = new CategoryAdapter(category);
//        recyclerViewCategoryList.setAdapter(adapter);
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerViewPopular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        Call<List<CartDetail>> call = productService.getAllFood();
        call.enqueue(new Callback<List<CartDetail>>() {
            @Override
            public void onResponse(Call<List<CartDetail>> call, Response<List<CartDetail>> response) {
                listProduct = response.body();
                ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, listProduct);
                recyclerViewPopularList.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<List<CartDetail>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
            }
        });
    }
}