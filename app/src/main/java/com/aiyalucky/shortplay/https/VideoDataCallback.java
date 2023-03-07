package com.aiyalucky.shortplay.https;

import com.aiyalucky.shortplay.pojo.VideoData;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/7 18:29
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public interface VideoDataCallback {
    void onVideoDataReceived(List<VideoData> videoDataList);
}
