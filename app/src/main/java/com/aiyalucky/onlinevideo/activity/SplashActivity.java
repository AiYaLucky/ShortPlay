package com.aiyalucky.onlinevideo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.User;
import com.aiyalucky.onlinevideo.MainActivity;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.utils.RetrofitClient;
import com.aiyalucky.onlinevideo.utils.SharedPreferencesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author xu xiao wei
 * @ClassName SplashActivity
 * @Package com.aiyalucky.onlinevideo
 * @Date 2023/7/1 21:57
 * @Version 1.0
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * 延时时间
     */
    private static final int SPLASH_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        //全屏的启动画面，隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //检测本地是否有登录信息
        SharedPreferencesUtil instance = SharedPreferencesUtil.getInstance(this);
        String account = instance.getString("account", "");
        String password = instance.getString("password", "");

        //账号和密码其中一个是空的，说明不能直接登录
        User user = new User();
        if (!"".equals(account) && !"".equals(password)) {
            user.setAccount(account);
            user.setPassword(password);
            RetrofitClient.getInstance().userLogin(user, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    if (user != null) {
                        AllData.USER = user;
                        instance.saveString("account", user.getAccount());
                        instance.saveString("password", user.getPassword());
                        Toast.makeText(SplashActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(SplashActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            RetrofitClient.getInstance().userRegister(user, new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    if (user != null) {
                        AllData.USER = user;
                        instance.saveString("account", user.getAccount());
                        instance.saveString("password", user.getPassword());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("xxxxxxxxxxxxxxx: ",t.getMessage());
                }
            });
        }

        // 延迟启动下一个Activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, SPLASH_DELAY);
    }
}
