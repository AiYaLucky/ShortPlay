package com.aiyalucky.shortplay.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.https.Response.ServerResponse;
import com.aiyalucky.shortplay.https.UserService;
import com.aiyalucky.shortplay.pojo.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Retrofit retrofit;
    private CheckBox rememberPassword;

    private SharedPreferences userSP;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_login);
        userSP = getSharedPreferences("user", MODE_PRIVATE);
        // 初始化 Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.165:8080/")
//                .baseUrl("http://192.168.3.218:8080/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        accountEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberPassword = findViewById(R.id.remember_password);
        loginButton = findViewById(R.id.loginButton);

        //记住密码的话，设置一下账号密码
        if (rememberPassword.isChecked()) {
            accountEditText.setText(userSP.getString("account", ""));
            passwordEditText.setText(userSP.getString("password", ""));
        }

        //设置登录按钮监听
        loginButton.setOnClickListener(v -> {
            String username = accountEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                return;
            }
            login(username, password);
        });
    }

    private void login(String account, String password) {
        if (this.rememberPassword.isChecked()) {
            SharedPreferences.Editor userEditor = getSharedPreferences("user", MODE_PRIVATE).edit();
            userEditor.putString("account", account);
            userEditor.putString("password", password);
            userEditor.apply();
        }
        // 创建登录服务实例
        UserService loginService = retrofit.create(UserService.class);
        // 发送登录请求
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        Call<ServerResponse> call = loginService.login(user);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.isSuccessful()) {
                    // 服务器成功通信返回
                    ServerResponse loginResponse = response.body();
                    if (loginResponse == null) {
                        // 登录失败，显示错误信息
                        Toast.makeText(LoginActivity.this, "通讯失败，请联系客服或者稍后尝试", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //是否正常登录成功
                    if (loginResponse.isSuccessLogin()) {
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        saveLoginInfo(loginResponse);
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // 登录失败，显示错误信息
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage() + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                // 网络错误
                Toast.makeText(LoginActivity.this, "网络错误：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        // 不进行任何操作，禁止返回
    }

    private void saveLoginInfo(ServerResponse loginResponse) {
        // TODO: 保存登录信息到 SharedPreferences 或数据库中
        Object user = loginResponse.getData().get("user");

        Gson gson = new Gson();
        User user1 = gson.fromJson(gson.toJson(user), User.class);
        SharedPreferences.Editor edit = userSP.edit();
        //todo 这里做所有的数据本地化
    }
}

