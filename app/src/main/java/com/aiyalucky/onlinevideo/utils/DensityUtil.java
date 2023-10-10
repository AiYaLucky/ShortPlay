package com.aiyalucky.onlinevideo.utils;

import android.content.Context;

import com.aiyalucky.onlinevideo.R;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/24 11:31
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取手机屏幕的宽度
     *
     * @param context 上下文
     * @return 高度数值
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取手机屏幕的高度
     *
     * @param context 上下文
     * @return 宽度数值
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    //获取数值配置文件中的数值
    public static int getDimens(Context context, int id) {
        return (int) context.getResources().getDimension(id);
    }

    /**
     * 专门用来计算单个视频的宽度
     *
     * @param context 上下文
     * @param columnsNums 一列要几个
     * @return 宽度数值
     */
    public static int getVideoWidth(Context context,Integer columnsNums) {
        int screenWidth = getScreenWidth(context);

        //获取热门视频的留空宽度总和
        int start = DensityUtil.getDimens(context, R.dimen.good_video_start_dp);
        int end = DensityUtil.getDimens(context, R.dimen.good_video_end_dp);
        int horizontalSpacing = DensityUtil.getDimens(context, R.dimen.good_video_horizontal_spacing_dp);
        int spaceDP = start + end + horizontalSpacing * 2;

        //根据一行,计算出每个视频的宽度
        return (screenWidth - spaceDP) / columnsNums;
    }

    /**
     * 专门用于计算热门推荐里面视频的宽度
     *
     * @param context 上下文
     * @return 宽度数值
     */
    public static int getHotVideoWidth(Context context) {
        //屏幕宽度
        int screenWidth = getScreenWidth(context);

        //获取的热门视频的高度
        int hotVideoHeight = DensityUtil.getDimens(context, R.dimen.hot_video_layout_height);

        //根据宽高比为1.5计算出宽度
        double hotVideoHWidth = Math.ceil((double) (hotVideoHeight * 1.5));

        //计算

        //获取热门视频的留空宽度总和
        int start = DensityUtil.getDimens(context, R.dimen.hot_video_marginStart);
        int end = DensityUtil.getDimens(context, R.dimen.hot_video_marginEnd);
        //热门视频水平间隔，跟好剧看视频的间隔一样
        int horizontalSpacing = DensityUtil.getDimens(context, R.dimen.good_video_horizontal_spacing_dp);
        //统计好空白的宽度
        int spaceDP = start + end + horizontalSpacing * 2;

        //根据一行三个的三列排序，计算出每个视频的宽度
        return (screenWidth - spaceDP) / 3;
    }
}
