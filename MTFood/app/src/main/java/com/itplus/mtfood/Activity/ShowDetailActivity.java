package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Product;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.ManagementCart;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView btnAddCart;
    private TextView txtTitle, txtFee, txtDescription, txtNumberOrder;
    private ImageView btnPlus, btnMinus, picFood;
    private CartDetail object;
    int numberOrder = 1;
    List<CartDetail> menuList;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBunble();
    }

    private void getBunble() {
        object =  (CartDetail) getIntent().getSerializableExtra("object");

        String picUrl = "http://192.168.1.108:8080/product/files/"+object.getPic();
        Picasso.get().load(picUrl).into(picFood);
        txtTitle.setText(object.getName());
        txtFee.setText(object.getUnitPrice() + " VNĐ");
        txtDescription.setText(object.getDescription());
        txtNumberOrder.setText(String.valueOf(numberOrder));


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberOrder = numberOrder + 1;
                txtNumberOrder.setText(String.valueOf(numberOrder));
                txtFee.setText(object.getUnitPrice()*numberOrder + " VNĐ");

            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numberOrder > 1) {
                    numberOrder = numberOrder - 1;
                }
                txtNumberOrder.setText(String.valueOf(numberOrder));
                txtFee.setText(object.getUnitPrice()*numberOrder + " VNĐ");
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }

        });
    }

    private void initView() {
        btnAddCart = findViewById(R.id.btnAddCart);
        txtTitle = findViewById(R.id.txtTitle);
        txtFee = findViewById(R.id.txtFee);
        txtDescription = findViewById(R.id.txtDescription);
        txtNumberOrder = findViewById(R.id.txtNumberOder);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        picFood = findViewById(R.id.picFood);
    }
}