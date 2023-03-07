package com.aiyalucky.shortplay.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.https.DataUtils;
import com.aiyalucky.shortplay.https.HttpUtils;
import com.aiyalucky.shortplay.https.Response.ServerResponse;
import com.aiyalucky.shortplay.https.UserService;
import com.aiyalucky.shortplay.pojo.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText accountEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private CheckBox rememberPassword;

    private SharedPreferences userSP;

    private SharedPreferences.Editor userSpEditor;
    private SharedPreferences videoSP;
    private SharedPreferences.Editor videoSpEditor;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setContentView(R.layout.activity_login);
        userSP = getSharedPreferences("user", MODE_PRIVATE);
        userSpEditor = getSharedPreferences("user", MODE_PRIVATE).edit();
        videoSP = getSharedPreferences("video", MODE_PRIVATE);
        videoSpEditor = getSharedPreferences("video", MODE_PRIVATE).edit();

        accountEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberPassword = findViewById(R.id.remember_password);
        loginButton = findViewById(R.id.loginButton);

        //记住密码的话，设置一下账号密码
        if (userSP.getBoolean("rememberPassword", false)) {
            accountEditText.setText(userSP.getString("account", ""));
            passwordEditText.setText(userSP.getString("password", ""));
            rememberPassword.setChecked(true);
        }

        //设置登录按钮监听
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = accountEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                login(username, password);
            }
        });

        //设置记住密码的勾选点击监听
        rememberPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消勾选的时候，清空密码
                if (!rememberPassword.isChecked()) {
                    passwordEditText.setText("");
                    userSpEditor.putBoolean("rememberPassword", false);
                }
            }
        });
    }

    /**
     * 登录具体逻辑
     *
     * @param account
     * @param password
     */
    private void login(String account, String password) {
        if (rememberPassword.isChecked()) {
            userSpEditor.putString("account", account);
            userSpEditor.putString("password", password);
            userSpEditor.putBoolean("rememberPassword", true);
            userSpEditor.apply();
        }

        // 创建登录服务实例
        UserService loginService = HttpUtils.getInstance().getRetrofit().create(UserService.class);

        // 发送登录请求
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        Call<ServerResponse> call = loginService.login(user);
        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
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
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                // 网络错误
                Toast.makeText(LoginActivity.this, "网络错误：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        DataUtils.videoDataRefresh(12,null);
        DataUtils.initHomeVideo(3);
    }

    @Override
    public void onBackPressed() {
        // 不进行任何操作，禁止返回
    }

    private void saveLoginInfo(ServerResponse loginResponse) {
        // TODO: 保存登录信息到 SharedPreferences 或数据库中
        Object userObj = loginResponse.getData().get("user");

        //本地化用户信息
        Gson gson = new Gson();
        User user = gson.fromJson(gson.toJson(userObj), User.class);
        user.userSaveSp(userSpEditor);


        //本地化推荐视频资源，初始默认加载3条url到本地

        //本地化主页展示的信息

    }


}

