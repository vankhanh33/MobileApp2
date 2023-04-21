package com.example.mobileapp.presenter;

import com.example.mobileapp.constants.Constants;
import com.example.mobileapp.constract.ApiService;
import com.example.mobileapp.constract.ConstractListProduct;
import com.example.mobileapp.model.Product;
import com.example.mobileapp.remote.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterProduct implements ConstractListProduct.IPresenter {
    ConstractListProduct.IView mView;
    private ApiService api;

    public PresenterProduct(ConstractListProduct.IView mVIew){
        this.mView = mVIew;
        api = RetrofitClient.getClient(Constants.API_URL_SMARTPHONE).create(ApiService.class);
    }
    @Override
    public void loadData() {
        api.getAllProduct().enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> mList = response.body();
                mView.updateProductList(mList);
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                mView.updateProductListFailed();
            }
        });
    }
}
