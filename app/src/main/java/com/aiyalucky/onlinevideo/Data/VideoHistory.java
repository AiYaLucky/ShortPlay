package com.aiyalucky.onlinevideo.Data;

import java.io.Serializable;

/**
 * 任务数据表
 * @TableName task
 */
public class VideoHistory implements Serializable {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 用户唯一id
     */
    private String uid;

    /**
     * 任务完成情况json字符串
     */
    private String historyInfo;

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
     * 用户唯一id
     */
    public String getUid() {
        return uid;
    }

    /**
     * 用户唯一id
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * 任务完成情况json字符串
     */
    public String getHistoryInfo() {
        return historyInfo;
    }

    /**
     * 任务完成情况json字符串
     */
    public void setHistoryInfo(String historyInfo) {
        this.historyInfo = historyInfo;
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
        VideoHistory other = (VideoHistory) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getHistoryInfo() == null ? other.getHistoryInfo() == null : this.getHistoryInfo().equals(other.getHistoryInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getHistoryInfo() == null) ? 0 : getHistoryInfo().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", taskinfo=").append(historyInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}