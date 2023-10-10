package com.aiyalucky.onlinevideo.utils;


import com.aiyalucky.onlinevideo.Data.TaskData;
import com.aiyalucky.onlinevideo.Data.User;
import com.aiyalucky.onlinevideo.Data.VideoCatch;
import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.Data.VideoHistory;
import com.aiyalucky.onlinevideo.Data.VideoInfo;
import com.aiyalucky.onlinevideo.RequestParams.UserAnd2IntRequestParams;
import com.aiyalucky.onlinevideo.RequestParams.UserAndIntRequestParams;
import com.aiyalucky.onlinevideo.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/28 18:18
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class RetrofitClient {

    public static final String BASE_URL = "http://xxw.x3322.net:8887";
    public static final String BASE_RESOURCE_URL = "http://xxw.x3322.net:8889";

    private static RetrofitClient instance;
    private final ApiService apiService;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    /**
     * 获取服务器传回来的数据
     *
     * @param param1
     * @param callback
     */
    public void getVideoDataList(Integer param1, Callback<List<VideoData>> callback) {
        Call<List<VideoData>> call = apiService.getVideoDataList(param1);
        call.enqueue(callback);
    }

    /**
     * 获取服务器传回来的数据
     *
     * @param param1
     * @param callback
     */
    public void getVideoInfoList(Integer param1, Callback<List<VideoInfo>> callback) {
        Call<List<VideoInfo>> call = apiService.getVideoInfoList(param1);
        call.enqueue(callback);
    }

    /**
     * 获取服务器传回来的数据
     *
     * @param param
     * @param callback
     */
    public void getTaskInfoList(UserAndIntRequestParams param, Callback<TaskData> callback) {
        Call<TaskData> call = apiService.getTaskInfoList(param);
        call.enqueue(callback);
    }

    /**
     * 获取追剧信息
     *
     * @param param
     * @param callback
     */
    public void getVideoCatch(UserAndIntRequestParams param, Callback<VideoCatch> callback) {
        Call<VideoCatch> call = apiService.getVideoCatch(param);
        call.enqueue(callback);
    }

    /**
     * 添加追剧信息
     *
     * @param params
     * @param callback
     */
    public void addVideoCatch(UserAndIntRequestParams params, Callback<VideoCatch> callback) {
        Call<VideoCatch> call = apiService.addVideoCatch(params);
        call.enqueue(callback);
    }

    /**
     * 获取历史观看记录
     *
     * @param param
     * @param callback
     */
    public void getVideoHistory(UserAndIntRequestParams param, Callback<VideoHistory> callback) {
        Call<VideoHistory> call = apiService.getVideoHistory(param);
        call.enqueue(callback);
    }

    /**
     * 添加历史观看记录
     *
     * @param param
     * @param callback
     */
    public void addVideoHistory(UserAnd2IntRequestParams param, Callback<VideoHistory> callback) {
        Call<VideoHistory> call = apiService.addVideoHistory(param);
        call.enqueue(callback);
    }

    /**
     * 用户登录
     */
    public void userLogin(User user, Callback<User> callback) {
        Call<User> call = apiService.userLogin(user);
        call.enqueue(callback);
    }

    /**
     * 用户注册
     */
    public void userRegister(User user, Callback<User> callback) {
        Call<User> call = apiService.userRegister(user);
        call.enqueue(callback);
    }
}
