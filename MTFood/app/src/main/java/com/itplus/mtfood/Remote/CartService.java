package com.itplus.mtfood.Remote;

import com.itplus.mtfood.Model.Cart;
import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartService {
    @GET("cart/get-all/")
    Call<List<Cart>> getAllCart();

    @GET("cart/get-cart/{id}")
    Call<List<Cart>> getAllCartById(@Path("id") int id);

    @POST("cart/insert/")
    Call<Cart> addCart(@Body Cart cart);

    @POST("cart_detail/insert/")
    Call<CartDetail> addCartDetail(@Body CartDetail cartDetail);
}
