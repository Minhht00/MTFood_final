package com.itplus.mtfood.Remote;

import com.itplus.mtfood.Model.CartDetail;
import com.itplus.mtfood.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
//    @GET("product/get-all")
//    Call<List<Product>> getAllFood1();

    @GET("product/get-all")
    Call<List<CartDetail>> getAllFood();

    @GET("product/get-all/{categoryId}")
    Call<List<CartDetail>> getAllProductById(@Path("categoryId") int categoryId);

}
