package com.aiyalucky.shortplay.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.https.LoginService;
import com.aiyalucky.shortplay.pojo.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Retrofit retrofit;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_login);
        // 初始化 Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.165:8080/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        usernameEditText.setText("123");
        passwordEditText.setText("123");
        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                return;
            }
            login(username, password);
        });
    }

    private void login(String username, String password) {
        // 创建登录服务实例
        LoginService loginService = retrofit.create(LoginService.class);
        // 发送登录请求
        User user = new User();
        user.setAccount(username);
        user.setPassword(password);
        Call<ResponseBody> call = loginService.login(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // 登录成功，保存登录信息并跳转到主界面
                    ResponseBody loginResponse = response.body();
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    saveLoginInfo(loginResponse);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 登录失败，显示错误信息
                    Toast.makeText(LoginActivity.this, "登录失败：" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // 网络错误
                Toast.makeText(LoginActivity.this, "网络错误：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveLoginInfo(ResponseBody loginResponse) {
        // TODO: 保存登录信息到 SharedPreferences 或数据库中
    }
}

