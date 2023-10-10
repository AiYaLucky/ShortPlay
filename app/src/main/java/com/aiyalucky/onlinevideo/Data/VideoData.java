package com.aiyalucky.onlinevideo.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 用户视频数据表,映射到SQLite数据库
 */
@Entity(tableName = "videos")
public class VideoData {
    /**
     * 自增id
     */
    @PrimaryKey
    private Integer id;

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

    /**
     * 剧集标识
     */
    private Integer videoid;

    /**
     * 剧集编号
     */
    private Integer epnum;

    /**
     * 是否热门视频，1为热门，0为非热门
     */
    private Integer hot;

    /**
     * 视频优先级，数字越高优先级越高
     */
    private Integer level;

    /**
     * 是否显示到banner上 0否 1是
     */
    private Integer banner;


    /**
     * 视频分类文本
     */
    private String type;

    /**
     * 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 视频展示图片url
     */
    public String getImgurl() {
        return imgurl;
    }

    /**
     * 视频展示图片url
     */
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    /**
     * 视频url
     */
    public String getVideourl() {
        return videourl;
    }

    /**
     * 视频url
     */
    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    /**
     * 剧集说明
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 剧集说明
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 剧集名称
     */
    public String getName() {
        return name;
    }

    /**
     * 剧集名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 剧集标识
     */
    public Integer getVideoid() {
        return videoid;
    }

    /**
     * 剧集标识
     */
    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    /**
     * 剧集编号
     */
    public Integer getEpnum() {
        return epnum;
    }

    /**
     * 剧集编号
     */
    public void setEpnum(Integer epnum) {
        this.epnum = epnum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getBanner() {
        return banner;
    }

    public void setBanner(Integer banner) {
        this.banner = banner;
    }
}