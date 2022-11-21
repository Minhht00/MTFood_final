package com.itplus.mtfood.Remote;

public class APIUtils {
    private APIUtils(){
    };

    public static final String API_URL = "http://192.168.1.108:8080/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(API_URL).create(UserService.class);
    }

    public static CategoryService getCategoryService(){
        return RetrofitClient.getClient(API_URL).create(CategoryService.class);
    }

    public static ProductService getFoodService(){
        return RetrofitClient.getClient(API_URL).create(ProductService.class);
    }

    public static CartService getCartService(){
        return RetrofitClient.getClient(API_URL).create(CartService.class);
    }
}
