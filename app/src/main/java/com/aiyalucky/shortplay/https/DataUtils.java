package com.aiyalucky.shortplay.https;

import com.aiyalucky.shortplay.fragment.HomeFragment;
import com.aiyalucky.shortplay.fragment.ShortPlayFragment;
import com.aiyalucky.shortplay.pojo.VideoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/6 16:30
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class DataUtils {

    /**
     * 从服务器获取视频数据
     * @param num 获取的条数
     */
    public static void videoDataRefresh(int num,final VideoDataCallback callback){
        // 创建获取视频数据实例
        VideoService videoService = HttpUtils.getInstance().getRetrofit().create(VideoService.class);
        Call<List<VideoData>> list = videoService.getList(num);

        list.enqueue(new Callback<List<VideoData>>() {
            @Override
            public void onResponse(Call<List<VideoData>> call, Response<List<VideoData>> response) {
                if (response.isSuccessful()) {
                    // 服务器成功通信返回,初始化主页视频数据
                    List<VideoData> videoDataList = response.body();
                    if (callback != null){
                        callback.onVideoDataReceived(videoDataList);
                    }else{
                        new ShortPlayFragment().initVideoData(videoDataList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoData>> call, Throwable t) {
            }
        });
    }

    public static void initHomeVideo(int num){
        // 创建获取视频数据实例
        VideoService videoService = HttpUtils.getInstance().getRetrofit().create(VideoService.class);
        Call<List<VideoData>> list = videoService.getList(num);
        list.enqueue(new Callback<List<VideoData>>() {
            @Override
            public void onResponse(Call<List<VideoData>> call, Response<List<VideoData>> response) {
                if (response.isSuccessful()) {
                    // 服务器成功通信返回,初始化主页视频数据
                    List<VideoData> videoDataList = response.body();
                    HomeFragment.initHomeVideo(videoDataList);
                }
            }

            @Override
            public void onFailure(Call<List<VideoData>> call, Throwable t) {
            }
        });
    }
}
