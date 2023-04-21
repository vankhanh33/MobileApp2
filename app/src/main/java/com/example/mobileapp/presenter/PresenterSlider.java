package com.example.mobileapp.presenter;

import com.example.mobileapp.constants.Constants;
import com.example.mobileapp.constract.ApiService;
import com.example.mobileapp.constract.ConstractSlider;
import com.example.mobileapp.model.Slider;
import com.example.mobileapp.remote.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterSlider implements ConstractSlider.IPresenter {
    ConstractSlider.IView mView;
    ApiService api;
    public PresenterSlider(ConstractSlider.IView mView){
        this.mView = mView;
        api = RetrofitClient.getClient(Constants.API_URL_BANNER).create(ApiService.class);
    }
    @Override
    public void loadDataSlider() {
        api.getImgSlider().enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {
                List<Slider> sliderList = response.body();
                mView.updateSliderList(sliderList);
            }

            @Override
            public void onFailure(Call<List<Slider>> call, Throwable t) {

            }
        });
    }
}
