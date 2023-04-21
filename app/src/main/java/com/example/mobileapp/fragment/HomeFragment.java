package com.example.mobileapp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobileapp.constants.Constants;
import com.example.mobileapp.R;
import com.example.mobileapp.adapter.BannerAdapter;
import com.example.mobileapp.adapter.CategoryAdapter;
import com.example.mobileapp.adapter.ProductAdapter;
import com.example.mobileapp.constract.ConstractSlider;
import com.example.mobileapp.model.Slider;
import com.example.mobileapp.model.Category;
import com.example.mobileapp.constract.OnItemClickListener;
import com.example.mobileapp.presenter.PresenterSlider;
import com.example.mobileapp.view.ZenithActivity;
import com.example.mobileapp.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import me.relex.circleindicator.CircleIndicator;


public class HomeFragment extends Fragment implements ConstractSlider.IView {
    //
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;

    List<Slider> bannerList;
    Timer timer;
    TextView txtCart;
    ArrayList<Category> cateList;
    CategoryAdapter cateAdapter;
    ProductAdapter smartAdapter, laptopAdapter;
    BannerAdapter bannerAdapter;
    CategoryAdapter categoryAdapter;
    LinearLayoutManager layoutProduct, layoutLaptop;
    ProgressBar loading_cate;
    AutoCompleteTextView inputSearch;
    TextInputLayout textInputLayout;
    TextView tvLaptop;
    RecyclerView rvSmart, rvCate, rvLaptop, rvFilter_Smartphone, rvFilter_Laptop;
    public int clickPosition;
    NavigationView mNavigationView;
    BottomNavigationView bottomNavigationView;

    List<String> suggestList = new ArrayList<>();
    ZenithActivity zenithActivity;
    ArrayAdapter suggestAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        zenithActivity = (ZenithActivity) getActivity();

        initGUI();
        loading_cate.setVisibility(View.VISIBLE);
        setLayoutRv();

        loadDataRvCategory();
        search();
        ConstractSlider.IPresenter mPresenter = new PresenterSlider(this);
        mPresenter.loadDataSlider();
        return view;

    }

    private void initGUI() {
        mNavigationView = getActivity().findViewById(R.id.nav_header);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_nav);
        viewPager = view.findViewById(R.id.viewPager_home);
        circleIndicator = view.findViewById(R.id.circleIndicator_home);
        rvSmart = view.findViewById(R.id.recyclerView_productHome);

        loading_cate = view.findViewById(R.id.load_categoryHome);
        inputSearch = view.findViewById(R.id.autoCompleteText_search);
        textInputLayout = view.findViewById(R.id.textInputLayout);
        tvLaptop = view.findViewById(R.id.textView_laptopHome);
        rvCate = view.findViewById(R.id.recyclerView_categoryHome);
        rvLaptop = view.findViewById(R.id.recyclerView_laptopHome);
        rvFilter_Smartphone = view.findViewById(R.id.rv_filter_smartphoneHome);
        rvFilter_Laptop = view.findViewById(R.id.rv_filter_laptopHome);
    }

    void search() {
        suggestAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, Utils.suggestSearchList);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textInputLayout.setHintEnabled(false);
                inputSearch.setAdapter(suggestAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setLayoutRv() {
        rvCate.setHasFixedSize(true);
        rvLaptop.setHasFixedSize(true);
        rvSmart.setHasFixedSize(true);
        rvFilter_Laptop.setHasFixedSize(true);
        rvFilter_Smartphone.setHasFixedSize(true);

        LinearLayoutManager layoutFilSm = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutSmart = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutFillap = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutLap = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager layoutCategory = new GridLayoutManager(this.getContext(), 5);

        rvFilter_Smartphone.setLayoutManager(layoutFilSm);
        rvFilter_Laptop.setLayoutManager(layoutFillap);
        rvLaptop.setLayoutManager(layoutLap);
        rvSmart.setLayoutManager(layoutSmart);
        rvCate.setLayoutManager(layoutCategory);
    }

    public void loadDataRvCategory() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.API_URL_CATEGORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ArrayList<Category> categoryList = new ArrayList<>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject arr = jsonArray.getJSONObject(i);
                        categoryList.add(new Category(arr.getInt("id"), arr.getString("tensp"), arr.optString("anh")));
                    }
                    categoryAdapter = new CategoryAdapter(categoryList, getContext());
                    rvCate.setAdapter(categoryAdapter);
                    loading_cate.setVisibility(View.INVISIBLE);
                    eventClickRvCate();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(view.getContext(), "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(view.getContext()).add(stringRequest);
    }



    private void eventClickRvCate() {
        categoryAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0:
                        replaceFragment(new SmartphoneFragment());
                        mNavigationView.getMenu().findItem(R.id.nav_smart).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.nav_smart).setChecked(true);
                        zenithActivity.mCurrentFragment = zenithActivity.FRAGMENT_SMART;
                        break;
                    case 1:
                        replaceFragment(new LaptopFragment());
                        mNavigationView.getMenu().findItem(R.id.nav_lap).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.nav_lap).setChecked(true);
                        zenithActivity.mCurrentFragment = zenithActivity.FRAGMENT_LAPTOP;
                        break;
                    case 3:
                        replaceFragment(new SmartphoneFragment());
                        mNavigationView.getMenu().findItem(R.id.nav_smart).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.nav_smart).setChecked(true);
                        zenithActivity.mCurrentFragment = zenithActivity.FRAGMENT_SMART;
                        break;

                }
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }


    @Override
    public void updateSliderList(List<Slider> mList) {
        bannerAdapter = new BannerAdapter(mList);
        viewPager.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager);
        bannerAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        com.example.mobileapp.slider.Slider.autoSlideImages(viewPager);
        bannerAdapter.notifyDataSetChanged();
        if(mList != null)  Toast.makeText(zenithActivity, mList.get(0).getImg(), Toast.LENGTH_SHORT).show();
    }
}
