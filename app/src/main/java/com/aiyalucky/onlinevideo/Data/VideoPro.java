package com.aiyalucky.onlinevideo.Data;

import java.io.Serializable;

/**
 * 视频观看进度
 * @TableName task
 */
public class VideoPro implements Serializable {
    /**
     * 剧集id
     */
    private Integer videoId;

    /**
     * 当前videoid的观看第几集
     */
    private Integer num;

    /**
     * 视频展示图片url
     */
    private String imgurl;

    /**
     * 视频url
     */
    private String videourl;

    /**
     * 剧集说明
     */
    private String desc;

    /**
     * 剧集名称
     */
    private String name;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}