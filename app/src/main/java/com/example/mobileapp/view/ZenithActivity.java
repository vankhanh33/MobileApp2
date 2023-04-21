package com.example.mobileapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.R;
import com.example.mobileapp.fragment.SmartphoneFragment;
import com.example.mobileapp.fragment.HomeFragment;
import com.example.mobileapp.fragment.LaptopFragment;
import com.example.mobileapp.fragment.AccountFragment;
import com.example.mobileapp.model.Account;
import com.example.mobileapp.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ZenithActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    public static final int FRAGMENT_HOME = 0;
    public static final int FRAGMENT_SMART = 1;
    public static final int FRAGMENT_LAPTOP = 2;
    public static final int FRAGMENT_ACCOUNT = 3;
    public int mCurrentFragment = FRAGMENT_HOME;
    NavigationView mNavigationView;
    BottomNavigationView bottomNavigationView;
    TextView tv_num_cart, tvUsername;
    LinearLayout lnCart;
    LinearLayout layoutLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zenith);
        initGUI();


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });

        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        mNavigationView.setNavigationItemSelectedListener(this);
        eventClickBottomNav();
    }

    private void initGUI() {

        mNavigationView = findViewById(R.id.nav_header);
        mDrawerLayout = findViewById(R.id.header_draw);
        mToolbar = findViewById(R.id.header_toolbar);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setBackground(null);
        Menu menu = mNavigationView.getMenu();
        MenuItem itemToRemove = menu.findItem(R.id.nav_cart);
        menu.removeItem(itemToRemove.getItemId());
        lnCart = findViewById(R.id.linear_cart);
        tv_num_cart = findViewById(R.id.num_cart);
        View view = mNavigationView.getHeaderView(0);
        tvUsername = view.findViewById(R.id.text_user);
        layoutLogout = findViewById(R.id.layout_logout);



    }

    public void eventClickBottomNav() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        openHome();
                        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;
                    case R.id.nav_smart:
                        openSmartphone();
                        mNavigationView.getMenu().findItem(R.id.nav_smart).setChecked(true);
                        break;
                    case R.id.nav_lap:
                        openLaptop();
                        mNavigationView.getMenu().findItem(R.id.nav_lap).setChecked(true);
                        break;
                    case R.id.nav_account:
                        openAccount();
                        mNavigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                openHome();
                bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                break;
            case R.id.nav_smart:
                openSmartphone();
                bottomNavigationView.getMenu().findItem(R.id.nav_smart).setChecked(true);
                break;
            case R.id.nav_lap:
                openLaptop();
                bottomNavigationView.getMenu().findItem(R.id.nav_lap).setChecked(true);
                break;
            case R.id.nav_account:
                openAccount();
                bottomNavigationView.getMenu().findItem(R.id.nav_account).setChecked(true);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openHome() {
        if (mCurrentFragment != FRAGMENT_HOME) {
            replaceFragment(new HomeFragment());
            mCurrentFragment = FRAGMENT_HOME;
        }
    }

    private void openSmartphone() {
        if (mCurrentFragment != FRAGMENT_SMART) {
            replaceFragment(new SmartphoneFragment());
            mCurrentFragment = FRAGMENT_SMART;
        }
    }

    private void openLaptop() {
        if (mCurrentFragment != FRAGMENT_LAPTOP) {
            replaceFragment(new LaptopFragment());
            mCurrentFragment = FRAGMENT_LAPTOP;
        }
    }

    private void openAccount() {
        if (mCurrentFragment != FRAGMENT_ACCOUNT) {
            replaceFragment(new AccountFragment());
            mCurrentFragment = FRAGMENT_ACCOUNT;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}