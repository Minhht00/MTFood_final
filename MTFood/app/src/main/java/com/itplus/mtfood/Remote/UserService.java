package com.itplus.mtfood.Remote;

import com.itplus.mtfood.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    @GET("user/checkLogin/{email}/{password}")
    Call<List<User>> login(@Path("email") String email, @Path("password") String password);

    @GET("user/get-user/{id}")
    Call<List<User>> getUserById(@Path("id") int id);

    @GET("user/get-all/")
    Call<List<User>> getAllUsers();

    @POST("user/insert/")
    Call<User> addUser(@Body User user);

    @PUT("user/update/")
    Call<User> updateUser(@Body User user);

    @DELETE("user/delete/{id}")
    Call<User> deleteUser(@Path("id") int id);
}
