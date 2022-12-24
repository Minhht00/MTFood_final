package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itplus.mtfood.Model.User;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.APIUtils;
import com.itplus.mtfood.Remote.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {
    private UserService userService;
    private EditText edtUserName, edtUserAddress, edtPass;
    private Button btnEdit;
    List<User> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userService = APIUtils.getUserService();
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_edit_user);
        edtUserName = findViewById(R.id.edtUserName);
        edtUserAddress = findViewById(R.id.edtUserAddress);
        edtPass = findViewById(R.id.edtPass);
        btnEdit = findViewById(R.id.btnEdit);

        edtUserName.setText(extras.getString("name"));
        edtUserAddress.setText(extras.getString("address"));

        int id = Integer.parseInt(extras.getString("id"));

        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Call<List<User>> call = userService.getUserById(id);
                call.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                        listUser = response.body();
                        if (edtPass.getText() != null && edtPass.getText().length() > 0) {
                            User user = new User();
                            user.setId(listUser.get(0).getId());
                            user.setName(edtUserName.getText().toString());
                            user.setAddress(edtUserAddress.getText().toString());
                            user.setEmail(listUser.get(0).getEmail());
                            user.setPassword(edtPass.getText().toString());
                            updateUser(user);
                            Intent intent = new Intent(EditUserActivity.this, UserActivity.class);
                            intent.putExtra("id", String.valueOf(listUser.get(0).getId()));
                            startActivity(intent);
                        } else {
                            User user = new User();
                            user.setId(listUser.get(0).getId());
                            user.setName(edtUserName.getText().toString());
                            user.setAddress(edtUserAddress.getText().toString());
                            user.setEmail(listUser.get(0).getEmail());
                            user.setPassword(listUser.get(0).getPassword());
                            updateUser(user);
                            Intent intent = new Intent(EditUserActivity.this, UserActivity.class);
                            intent.putExtra("id", String.valueOf(listUser.get(0).getId()));
                            startActivity(intent);
                        }

                    }
                    @Override
                    public void onFailure(Call<List<User>> call, Throwable t) {
                        Toast.makeText(EditUserActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
                    }
                });



            }
        });

    }

    private void updateUser(User user) {
        Call<User> call = userService.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(EditUserActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}