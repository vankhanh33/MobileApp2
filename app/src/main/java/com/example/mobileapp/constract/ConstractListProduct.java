package com.example.mobileapp.constract;

import com.example.mobileapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public interface ConstractListProduct {
    interface IView{
        void updateProductList(ArrayList<Product> mList);
        void updateProductListFailed();
    }
    interface IPresenter{
        void loadData();
    }
}
