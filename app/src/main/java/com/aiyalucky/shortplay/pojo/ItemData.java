package com.aiyalucky.shortplay.pojo;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/1 22:54
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class ItemData {
    private String imageRes;
    private String text;

    private Integer videoId;

    public ItemData() {
    }

    public ItemData(String imageRes, String text) {
        this.imageRes = imageRes;
        this.text = text;
    }

    public String getImageRes() {
        return imageRes;
    }

    public void setImageRes(String imageRes) {
        this.imageRes = imageRes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}