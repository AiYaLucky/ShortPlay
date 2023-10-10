package com.aiyalucky.onlinevideo.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * 剧集信息表
 * @TableName video_info
 */
@Entity(tableName = "videos_info")
public class VideoInfo implements Serializable {
    /**
     * 自增id
     */
    @PrimaryKey
    private Integer id;

    /**
     * 剧集id
     */
    private Integer videoid;

    /**
     * 剧集总集数
     */
    private Integer maxnum;

    /**
     * 视频风格 1校园故事 2屌丝逆袭 3穿越故事 4江湖轶事 5搞笑喜剧 6玄幻史诗
     */
    private String videostyle;

    /**
     * 完结状态 1连载中 2完结
     */
    private Integer finish;

    /**
     * 剧集付费类型 1免费 2限免 3付费
     */
    private Integer paytype;

    /**
     * 追剧数量
     */
    private Integer catchnum;

    /**
     * 播放数量
     */
    private Integer playnum;

    /**
     * 点赞数量
     */
    private Integer likenum;

    /**
     * 更新时间
     */
    private Integer updatetime;

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
     * 剧集id
     */
    public Integer getVideoid() {
        return videoid;
    }

    /**
     * 剧集id
     */
    public void setVideoid(Integer videoid) {
        this.videoid = videoid;
    }

    /**
     * 剧集总集数
     */
    public Integer getMaxnum() {
        return maxnum;
    }

    /**
     * 剧集总集数
     */
    public void setMaxnum(Integer maxnum) {
        this.maxnum = maxnum;
    }

    /**
     * 视频风格 1校园故事 2屌丝逆袭 3穿越故事 4江湖轶事 5搞笑喜剧 6玄幻史诗
     */
    public String getVideostyle() {
        return videostyle;
    }

    /**
     * 视频风格 1校园故事 2屌丝逆袭 3穿越故事 4江湖轶事 5搞笑喜剧 6玄幻史诗
     */
    public void setVideostyle(String videostyle) {
        this.videostyle = videostyle;
    }

    /**
     * 完结状态 1连载中 2完结
     */
    public Integer getFinish() {
        return finish;
    }

    /**
     * 完结状态 1连载中 2完结
     */
    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    /**
     * 剧集付费类型 1免费 2限免 3付费
     */
    public Integer getPaytype() {
        return paytype;
    }

    /**
     * 剧集付费类型 1免费 2限免 3付费
     */
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    /**
     * 追剧数量
     */
    public Integer getCatchnum() {
        return catchnum;
    }

    /**
     * 追剧数量
     */
    public void setCatchnum(Integer catchnum) {
        this.catchnum = catchnum;
    }

    /**
     * 播放数量
     */
    public Integer getPlaynum() {
        return playnum;
    }

    /**
     * 播放数量
     */
    public void setPlaynum(Integer playnum) {
        this.playnum = playnum;
    }

    /**
     * 点赞数量
     */
    public Integer getLikenum() {
        return likenum;
    }

    /**
     * 点赞数量
     */
    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    /**
     * 更新时间
     */
    public Integer getUpdatetime() {
        return updatetime;
    }

    /**
     * 更新时间
     */
    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
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
        VideoInfo other = (VideoInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVideoid() == null ? other.getVideoid() == null : this.getVideoid().equals(other.getVideoid()))
            && (this.getMaxnum() == null ? other.getMaxnum() == null : this.getMaxnum().equals(other.getMaxnum()))
            && (this.getVideostyle() == null ? other.getVideostyle() == null : this.getVideostyle().equals(other.getVideostyle()))
            && (this.getFinish() == null ? other.getFinish() == null : this.getFinish().equals(other.getFinish()))
            && (this.getPaytype() == null ? other.getPaytype() == null : this.getPaytype().equals(other.getPaytype()))
            && (this.getCatchnum() == null ? other.getCatchnum() == null : this.getCatchnum().equals(other.getCatchnum()))
            && (this.getPlaynum() == null ? other.getPlaynum() == null : this.getPlaynum().equals(other.getPlaynum()))
            && (this.getLikenum() == null ? other.getLikenum() == null : this.getLikenum().equals(other.getLikenum()))
            && (this.getUpdatetime() == null ? other.getUpdatetime() == null : this.getUpdatetime().equals(other.getUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVideoid() == null) ? 0 : getVideoid().hashCode());
        result = prime * result + ((getMaxnum() == null) ? 0 : getMaxnum().hashCode());
        result = prime * result + ((getVideostyle() == null) ? 0 : getVideostyle().hashCode());
        result = prime * result + ((getFinish() == null) ? 0 : getFinish().hashCode());
        result = prime * result + ((getPaytype() == null) ? 0 : getPaytype().hashCode());
        result = prime * result + ((getCatchnum() == null) ? 0 : getCatchnum().hashCode());
        result = prime * result + ((getPlaynum() == null) ? 0 : getPlaynum().hashCode());
        result = prime * result + ((getLikenum() == null) ? 0 : getLikenum().hashCode());
        result = prime * result + ((getUpdatetime() == null) ? 0 : getUpdatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", videoid=").append(videoid);
        sb.append(", maxnum=").append(maxnum);
        sb.append(", videostyle=").append(videostyle);
        sb.append(", finish=").append(finish);
        sb.append(", paytype=").append(paytype);
        sb.append(", catchnum=").append(catchnum);
        sb.append(", playnum=").append(playnum);
        sb.append(", likenum=").append(likenum);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}