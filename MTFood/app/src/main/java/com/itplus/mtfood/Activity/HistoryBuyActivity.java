package com.itplus.mtfood.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.itplus.mtfood.Adapter.HistoryAdapter;
import com.itplus.mtfood.Adapter.ProductAdapter;
import com.itplus.mtfood.Model.Cart;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.R;
import com.itplus.mtfood.Remote.APIUtils;
import com.itplus.mtfood.Remote.CartService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryBuyActivity extends AppCompatActivity {
    private CartService cartService;
    private RecyclerView recyclerViewHistory;
    private List<Cart> listCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_buy);
        cartService = APIUtils.getCartService();
        recyclerViewHistory();
    }
    private void recyclerViewHistory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewHistory = findViewById(R.id.recyclerviewHistory);
        recyclerViewHistory.setLayoutManager(linearLayoutManager);
        Bundle extras = getIntent().getExtras();

        Call<List<Cart>> call = cartService.getAllCartById(Integer.parseInt(extras.getString("id")));
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                listCart = response.body();
                HistoryAdapter hisAdapter = new HistoryAdapter(HistoryBuyActivity.this, listCart);
                recyclerViewHistory.setAdapter(hisAdapter);
                hisAdapter.notifyDataSetChanged();

            }
            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(HistoryBuyActivity.this, "data does not fetch", Toast.LENGTH_SHORT);
            }
        });
    }
}