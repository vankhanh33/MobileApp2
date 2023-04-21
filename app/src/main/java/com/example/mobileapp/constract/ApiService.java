package com.example.mobileapp.constract;

import com.example.mobileapp.model.Product;
import com.example.mobileapp.model.Slider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("list_smartphone.php")
    Call<ArrayList<Product>> getAllProduct();
    @GET("slider__home.php")
    Call<List<Slider>> getImgSlider();
}
