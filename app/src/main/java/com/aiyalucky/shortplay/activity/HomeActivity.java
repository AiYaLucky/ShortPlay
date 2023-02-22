package com.aiyalucky.shortplay.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.fragment.HomeFragment;
import com.aiyalucky.shortplay.fragment.ShortPlayFragment;

public class HomeActivity extends AppCompatActivity {

    //声明Fragment
    private Fragment fragment1;
    private Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //初始化Fragment
        fragment1 = new HomeFragment();
        fragment2 = new ShortPlayFragment();

        //默认显示第一个Fragment
        showFragment(fragment1);
    }

    //菜单1点击事件
    public void onMenu1Clicked(View view) {
        showFragment(fragment1);
    }

    //菜单2点击事件
    public void onMenu2Clicked(View view) {
        showFragment(fragment2);
    }

    //切换Fragment
    private void showFragment(@NonNull Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        // 使用replace()方法替换当前的Fragment
        ft.replace(R.id.containerLayout, fragment);
        ft.commit();
    }
}