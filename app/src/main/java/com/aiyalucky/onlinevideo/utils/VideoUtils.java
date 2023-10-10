package com.aiyalucky.onlinevideo.utils;

import android.content.Context;
import android.content.Intent;

import com.aiyalucky.onlinevideo.activity.VideosPlayerActivity;

/**
 * 视频播放工具
 * @ClassName VideoUtils
 * @Description
 * @Author xu xiao wei
 * @Date 2023/6/19 17:10
 * @Version 1.0
 */
public class VideoUtils {

    /**
     * 视频播放类
     */
    private static Intent INSTANCE;

    /**
     * 获取视频播放类
     * @param context 上下文
     * @return  返回单例的视频播放类
     */
    public static Intent getVideoPlayer(Context context) {
        if (INSTANCE == null) {
            synchronized (VideoUtils.class) {
                INSTANCE = new Intent(context, VideosPlayerActivity.class);
            }
        }
        return INSTANCE;
    }
}
