package com.aiyalucky.shortplay.pojo;

import java.io.Serializable;

/**
 * 用户视频数据表
 * @TableName video_list
 */
public class VideoData implements Serializable,Comparable<VideoData> {
    /**
     * 自增id
     */
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
     * 视频分类文本
     */
    private String type;

    private static final long serialVersionUID = 1L;

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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        VideoData other = (VideoData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getImgurl() == null ? other.getImgurl() == null : this.getImgurl().equals(other.getImgurl()))
            && (this.getVideourl() == null ? other.getVideourl() == null : this.getVideourl().equals(other.getVideourl()))
            && (this.getDesc() == null ? other.getDesc() == null : this.getDesc().equals(other.getDesc()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getEpnum() == null ? other.getEpnum() == null : this.getEpnum().equals(other.getEpnum()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getImgurl() == null) ? 0 : getImgurl().hashCode());
        result = prime * result + ((getVideourl() == null) ? 0 : getVideourl().hashCode());
        result = prime * result + ((getDesc() == null) ? 0 : getDesc().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getVideoid() == null) ? 0 : getVideoid().hashCode());
        result = prime * result + ((getEpnum() == null) ? 0 : getEpnum().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imgurl=").append(imgurl);
        sb.append(", videourl=").append(videourl);
        sb.append(", desc=").append(desc);
        sb.append(", name=").append(name);
        sb.append(", videoid=").append(videoid);
        sb.append(", epnum=").append(epnum);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int compareTo(VideoData o) {
        return Integer.compare(this.videoid,o.getVideoid());
    }
}