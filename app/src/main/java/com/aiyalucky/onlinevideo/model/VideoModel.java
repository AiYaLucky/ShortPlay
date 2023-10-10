package com.aiyalucky.onlinevideo.model;


/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 17:33
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */

public class VideoModel {
    private String videoImage;
    private String videoName;
    private String videoDesc;
    private String videoUrl;

    public VideoModel(String imageResId, String name, String url, String desc) {
        videoImage = imageResId;
        videoName = name;
        videoUrl = url;
        videoDesc = desc;
    }

    public VideoModel() {
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
