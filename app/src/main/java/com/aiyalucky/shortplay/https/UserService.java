package com.aiyalucky.shortplay.https;

import com.aiyalucky.shortplay.https.Response.ServerResponse;
import com.aiyalucky.shortplay.pojo.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    // 定义请求的路径
    @POST("user/login")
    // 定义登录请求的方法，请求参数为 User 对象，返回值为 Call<ResponseBody>
    Call<ServerResponse> login(@Body User user);
}