package com.example.forcemachine.retrofit;

import com.example.forcemachine.table.RestaurantStatusRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {
    @POST("/status/restaurant-status")
    Call<String> postRestaurantStatus(@Body RestaurantStatusRequest restaurantStatusRequest);
}