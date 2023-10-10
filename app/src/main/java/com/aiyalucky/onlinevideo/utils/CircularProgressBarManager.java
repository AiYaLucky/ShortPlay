package com.aiyalucky.onlinevideo.utils;

import android.os.Handler;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

/**
 * @Author: xu xiao wei
 * @Date: 2023/6/24 0:31
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class CircularProgressBarManager {
    private static CircularProgressBarManager instance;
    private CircularProgressBar progressBar;

    private boolean isProgressRunning;
    private int maxProgress; // 最大进度值
    private double currentProgress; // 当前进度值
    private long totalTime; // 总时长
    private long startTime; // 开始时间
    private boolean isPaused; // 是否处于暂停状态
    private long pauseTime; // 记录暂停时间点
    private long resumeTime; // 记录恢复时间点
    private Handler handler;
    private Runnable progressRunnable;
    private Interpolator interpolator;


    private CircularProgressBarManager() {
        handler = new Handler();
        progressRunnable = new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                if (elapsedTime >= totalTime) {
                    stopProgress();
                    return;
                }
                double progressPercentage = (double) elapsedTime / totalTime;
                double smoothProgress = interpolator.getInterpolation((float) progressPercentage) * maxProgress;
                int progress = (int) Math.round(smoothProgress);
                progressBar.setProgress(progress);
                handler.postDelayed(this, 16); // 每隔16毫秒更新一次进度，约等于60帧每秒
            }
        };
//        interpolator = new DecelerateInterpolator(); // 使用减速插值器，前面进度增长快，后面慢。
        interpolator = new LinearInterpolator(); // 使用线性插值器，全程都是均匀的增长
    }


    public static synchronized CircularProgressBarManager getInstance() {
        if (instance == null) {
            instance = new CircularProgressBarManager();
        }
        return instance;
    }

    /**
     * 设置环形进度条
     * @param progressBar 环形进度条
     */
    public void setProgressBar(CircularProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public boolean getIsPaused(){
        return isPaused;
    }
    /**
     * 获取环形进度条
     */
    public CircularProgressBar getProgressBar() {
       return this.progressBar;
    }

    /**
     * 设置最大进度值
     * @param maxProgress 最大进度值
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    /**
     * 设置总时长
     * @param totalTime 总时长，单位毫秒
     */
    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * 开始进度增长
     */
    public void startProgress() {
        if (isProgressRunning) {
            return;
        }
        isProgressRunning = true;
        currentProgress = 0;
        startTime = System.currentTimeMillis();
        handler.post(progressRunnable);
    }

    /**
     * 停止进度增长
     */
    public void stopProgress() {
        isProgressRunning = false;
        handler.removeCallbacks(progressRunnable);
    }
    /**
     * 设置插值器
     * @param interpolator 插值器
     */
    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    /**
     * 暂停进度增长
     */
    public void pauseProgress() {
        if (!isProgressRunning || isPaused) {
            return;
        }
        isPaused = true;
        //记录暂停开始时间
        pauseTime = System.currentTimeMillis();
        handler.removeCallbacks(progressRunnable);
    }

    /**
     * 恢复进度增长
     */
    public void resumeProgress() {
        if (!isProgressRunning || !isPaused) {
            return;
        }
        isPaused = false;

        // 计算已经过去的时间，即从开始时间到现在的时间间隔
        long elapsedTime = pauseTime - startTime;
        startTime = System.currentTimeMillis() - elapsedTime;
        handler.post(progressRunnable);
    }



    /**
     * 切换暂停/恢复状态
     */
    public void togglePauseResume() {
        if (isPaused) {
            resumeProgress();
        } else {
            pauseProgress();
        }
    }

}


