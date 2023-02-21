package com.aiyalucky.shortplay.https;

import com.aiyalucky.shortplay.pojo.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    // 定义请求的路径
    Call<ResponseBody> login(@Body User user);  // 定义登录请求的方法，请求参数为 User 对象，返回值为 Call<Response
}