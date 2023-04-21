package com.example.mobileapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileapp.R;
import com.example.mobileapp.adapter.BannerChildAdapter;
import com.example.mobileapp.adapter.ProductAdapter;
import com.example.mobileapp.constants.Constants;
import com.example.mobileapp.constract.ConstractListProduct;
import com.example.mobileapp.model.Slider;
import com.example.mobileapp.model.Product;
import com.example.mobileapp.presenter.PresenterProduct;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class SmartphoneFragment extends Fragment implements ConstractListProduct.IView {
    AutoCompleteTextView inputSearch;
    TextInputLayout inputLayout;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    RecyclerView rvSmartphone;
    ProductAdapter smartAdapter;
    BannerChildAdapter bannerAdapter;
    View view;
    LinearLayout llHighFilter, llLowFilter, llPercentFilter;
    List<String> suggestSearchList = new ArrayList<>();
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product, container, false);
        initGUI();
        progressBar.setVisibility(View.VISIBLE);

        ConstractListProduct.IPresenter mPresenter = new PresenterProduct(this);
        mPresenter.loadData();
        return view;
    }

    void initGUI() {
        inputSearch = view.findViewById(R.id.autoCompleteText_search);
        inputLayout = view.findViewById(R.id.textInputLayout);
        viewPager = view.findViewById(R.id.slider_smartphone);
        circleIndicator = view.findViewById(R.id.circleIndicator_sm);
        rvSmartphone = view.findViewById(R.id.recycleView_smartphone);
        llHighFilter = view.findViewById(R.id.box_caothap);
        llLowFilter = view.findViewById(R.id.box_thapcao);
        llPercentFilter = view.findViewById(R.id.box_percen);
        progressBar = view.findViewById(R.id.load_product);

    }




    private void autoSlideImages() {
        // Tạo Handler và Runnable  thực hiện slide tự động
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int currentItem = viewPager.getCurrentItem();
                int count = viewPager.getAdapter().getCount();
                viewPager.setCurrentItem((currentItem + 1) % count);

                // Lặp lại chạy lại task sau 5 giây
                handler.postDelayed(this, 6000);
            }
        };
        // Bắt đầu thực hiện slide tự động khi Activity hoặc Fragment được khởi tạo
        handler.postDelayed(runnable, 6000);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    viewPager.removeCallbacks(runnable);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    viewPager.postDelayed(runnable, 6000);
                return false;
            }
        });

    }

    @Override
    public void updateProductList(ArrayList<Product> mList) {
        rvSmartphone.setHasFixedSize(true);
        rvSmartphone.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvSmartphone.setNestedScrollingEnabled(false);
        smartAdapter = new ProductAdapter(mList,getContext());
        rvSmartphone.setAdapter(smartAdapter);
        smartAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateProductListFailed() {

    }
}
