package com.example.mobileapp.constract;

import com.example.mobileapp.model.Slider;

import java.util.List;

public interface ConstractSlider {
    interface IView{
        void updateSliderList(List<Slider> mList);
    }
    interface IPresenter{
        void loadDataSlider();
    }
}
