package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itplus.mtfood.Adapter.CategoryAdapter;
import com.itplus.mtfood.Adapter.UserAdapter;
import com.itplus.mtfood.Model.Category;
import com.itplus.mtfood.Model.User;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.APIUtils;
import com.itplus.mtfood.Remote.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    TextView txtName, txtNameUser, txtAddressUser;
    private UserService userService;
    List<User> listUser;
    private RecyclerView recyclerViewUserList;
    Button editBtn, hisBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        userService = APIUtils.getUserService();
        txtName = findViewById(R.id.txtName);

        txtNameUser = findViewById(R.id.txtNameUser);
        txtAddressUser = findViewById(R.id.txtAddressUser);
        bottomNavigation();
        editBtn = findViewById(R.id.editBtn);
        hisBtn = findViewById(R.id.hisBtn);

        recyclerViewUser();

    }
    private void bottomNavigation() {
        Bundle extras = getIntent().getExtras();
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout userBtn = findViewById(R.id.profileBtn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, CartListActivity.class);
                startActivity(intent);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this, MainActivity.class));
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
                        Intent intent = new Intent(UserActivity.this, UserActivity.class);
                        intent.putExtra("id", String.valueOf(listUser.get(0).getId()));
                        intent.putExtra("email", String.valueOf(listUser.get(0).getName()));
                        startActivity(intent);
                    }
                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(UserActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
                    }
                });

            }
        });
    }

    private void recyclerViewUser() {
        Bundle extras = getIntent().getExtras();

        Call<List<User>> call = userService.getUserById(Integer.parseInt(extras.getString("id")));
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                listUser = response.body();
                txtNameUser.setText(listUser.get(0).getName());
                txtAddressUser.setText(listUser.get(0).getAddress());
                txtName.setText("XIN CHÀO " + listUser.get(0).getName());
                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserActivity.this, EditUserActivity.class);
                        intent.putExtra("id", String.valueOf(listUser.get(0).getId()));
                        intent.putExtra("name", String.valueOf(listUser.get(0).getName()));
                        intent.putExtra("address", String.valueOf(listUser.get(0).getAddress()));
                        startActivity(intent);
                    }
                });
                hisBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(UserActivity.this, HistoryBuyActivity.class);
                        intent.putExtra("id", String.valueOf(listUser.get(0).getId()));
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(UserActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
            }
        });

    }

}