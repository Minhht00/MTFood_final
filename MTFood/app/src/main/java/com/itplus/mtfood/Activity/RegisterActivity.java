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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    UserService userService;
    EditText edtUId, edtUEmail, edtUPassword, edtUName, edtUAddress;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUId = (EditText) findViewById(R.id.edtUId);
        edtUEmail = (EditText) findViewById(R.id.edtUEmail);
        edtUPassword = (EditText) findViewById(R.id.edtUPassword);
        edtUName = (EditText) findViewById(R.id.edtUName);
        edtUAddress = (EditText) findViewById(R.id.edtUAddress);
        btnRegister = (Button) findViewById(R.id.bntRegister);

        userService = APIUtils.getUserService();

        Bundle extras = getIntent().getExtras();
        final String userId = extras.getString("id");
//        String email = extras.getString("email");
//        String password = extras.getString("password");
//        String name = extras.getString("name");
//        String address = extras.getString("address");
//
//        edtUId.setText(userId);
//        edtUEmail.setText(email);
//        edtUPassword.setText(password);
//        edtUName.setText(name);
//        edtUAddress.setText(address);

        if(userId != null && userId.trim().length() > 0 ){
            edtUId.setFocusable(false);
        } else {
            edtUId.setVisibility(View.INVISIBLE);
        }


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User u = new User();
                u.setEmail(edtUEmail.getText().toString());
                u.setPassword(edtUPassword.getText().toString());
                u.setName(edtUName.getText().toString());
                u.setAddress(edtUAddress.getText().toString());
                if(userId != null && userId.trim().length() > 0){

                } else {
                    //add user
                    addUser(u);
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void addUser(User u){
        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "User created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

}