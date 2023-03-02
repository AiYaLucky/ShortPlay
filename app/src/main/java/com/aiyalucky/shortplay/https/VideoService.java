package com.aiyalucky.shortplay.https;

import com.aiyalucky.shortplay.pojo.ItemData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 用于请求视频相关数据的接口
 */
public interface VideoService {

    @POST("video/getList")
    // 随机请求条目数据
    Call<List<ItemData>> getList(@Body Map<String, String> params);

}