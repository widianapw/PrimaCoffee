package com.example.praktikumprognet17;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import android.os.Bundle;

import com.example.praktikumprognet17.features.home.HomeFragment;
import com.example.praktikumprognet17.features.kasir.KasirFragment;
import com.example.praktikumprognet17.features.setting.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    final Fragment fragment1 = new KasirFragment();
    final Fragment fragment2 = new SettingFragment();
    final Fragment fragment3 = new HomeFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.getString("objek") != null) {
                fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
                fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
                fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
                active = fragment2;
            } else {
                fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
                fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
                fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
                active = fragment1;
            }

        } else {
            fm.beginTransaction().add(R.id.main_container, fragment1, "1").commit();
            fm.beginTransaction().add(R.id.main_container, fragment2, "2").hide(fragment2).commit();
            fm.beginTransaction().add(R.id.main_container, fragment3, "3").hide(fragment3).commit();
            active = fragment1;
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_kasir:
                fm.beginTransaction().hide(active).show(fragment1).commit();
                active = fragment1;
                return true;

            case R.id.navigation_setting:
                fm.beginTransaction().hide(active).show(fragment2).commit();
                active = fragment2;
                return true;

            case R.id.navigation_home:
                fm.beginTransaction().hide(active).show(fragment3).commit();
                active = fragment3;
                return true;

        }
        return false;
    };

}
