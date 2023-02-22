package com.aiyalucky.shortplay.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aiyalucky.shortplay.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 定义启动 LoginActivity 的 Intent
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        // 设置淡入淡出动画效果
        ActivityOptions options = ActivityOptions.makeCustomAnimation(MainActivity.this, android.R.anim.fade_in, android.R.anim.fade_out);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // 启动 LoginActivity
//                startActivity(intent, options.toBundle());
//                // 结束当前 Activity
//                finish();
//            }
//        },3000);

        startActivity(intent, options.toBundle());


    }
}