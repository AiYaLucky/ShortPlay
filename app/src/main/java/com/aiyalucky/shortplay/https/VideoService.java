package com.aiyalucky.shortplay.https;

import com.aiyalucky.shortplay.pojo.VideoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 用于请求视频相关数据的接口
 */
public interface VideoService {

    @POST("video/getList")
    // 随机请求条目数据
    Call<List<VideoData>> getList(@Body Integer params);

}