package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itplus.mtfood.Adapter.CartListAdapter;
import com.itplus.mtfood.Model.Cart;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Product;
import com.itplus.mtfood.Model.User;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.APIUtils;
import com.itplus.mtfood.Remote.ChangeNumberItemsListener;
import com.itplus.mtfood.Remote.ManagementCart;
import com.itplus.mtfood.Remote.CartService;
import com.itplus.mtfood.Remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, cusID;
    TextView totalEachItem;
    TextView totalShipTxt;
    TextView totalSaleTxt;
    TextView totalTxt;
    TextView emptyTxt;
    TextView txtId, numberItemTxt;
    TextView btnPay;
    private ScrollView scrollView;
    EditText edtAdress, edtID;
    CartService cartService;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        userService = APIUtils.getUserService();
        cartService = APIUtils.getCartService();
        cusID = findViewById(R.id.cusID);
        edtAdress = findViewById(R.id.edtAdress);
//        Bundle extras = getIntent().getExtras();
//        final String cartId = extras.getString("order_id");
//        String cusId = extras.getString("customer_id");
//        String amount = extras.getString("amount");
//        String name = extras.getString("name");
//        String address = extras.getString("address");

//        edtAdress.setText(address);

        managementCart = new ManagementCart(this);
        initView();
        initList();
        CalculateCart();

//        if(cartId != null && cartId.length() > 0 ){
//            edtID.setFocusable(false);
//        } else {
//            edtID.setVisibility(View.INVISIBLE);
//        }

        Call<List<User>> call = userService.getAllUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                cusID.setText(Integer.toString(response.body().get(0).getId()));
                edtAdress.setText(response.body().get(0).getAddress());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart c = new Cart();
                CartDetail cd = new CartDetail();
                double total = Math.round(managementCart.getTotalFee());


                c.setCustomerId(Integer.parseInt(cusID.getText().toString()));
                c.setAmount(total);
                c.setAddress(edtAdress.getText().toString());

                List<CartDetail> cartDetails = managementCart.getListCart();

//                cd.setOrderId(c.getOrderId());
//                cd.setUnitPrice(total);
                if (edtAdress == null) {
                    Toast.makeText(CartListActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                } else {
                    Call<List<Cart>> call2 = cartService.getAllCart();
                    call2.enqueue(new Callback<List<Cart>>() {
                        @Override
                        public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                            List<Cart> cartList = response.body();
                            int cartId = 1;
                            cartId = cartId +  cartList.get(cartList.size()-1).getOrderId();
                            c.setOrderId(cartId);
                            addCart(c);
                            for (CartDetail cd1:cartDetails
                            ) {
//                        cd1.setNumberInCart(Integer.parseInt(numberItemTxt.getText().toString()));
//                        cd1.setUnitPrice(Double.parseDouble(totalEachItem.getText().toString()));
                                cd1.setOrderId(cartId);
                                addCartDetail(cd1);
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Cart>> call, Throwable t) {

                        }
                    });



                    Intent intent = new Intent(CartListActivity.this, CartListActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addCart(Cart c){
        Call<Cart> call = cartService.addCart(c);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if(response.isSuccessful()){
                        Toast.makeText(CartListActivity.this, "created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    public void addCartDetail(CartDetail cd){
        Call<CartDetail> call = cartService.addCartDetail(cd);
        call.enqueue(new Callback<CartDetail>() {
            @Override
            public void onResponse(Call<CartDetail> call, Response<CartDetail> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CartListActivity.this, "created successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartDetail> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recycleViewCart);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        totalShipTxt = findViewById(R.id.totalShipTxt);
        totalSaleTxt = findViewById(R.id.totalSaleTxt);
        totalTxt = findViewById(R.id.totalTxt);
        scrollView = findViewById(R.id.scrollView3);
        emptyTxt = findViewById(R.id.emptyTxt);
        btnPay = findViewById(R.id.btnPay);
        totalEachItem = findViewById(R.id.totalEachItem);
        numberItemTxt = findViewById(R.id.numberItemTxt);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double total = Math.round(managementCart.getTotalFee());
        double itemTotal = Math.round(managementCart.getTotalFee());

        totalTxt.setText(total + " VNĐ");
        totalFeeTxt.setText(itemTotal + " VNĐ");
    }
}