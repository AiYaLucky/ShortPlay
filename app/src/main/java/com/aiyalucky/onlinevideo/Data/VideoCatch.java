package com.aiyalucky.onlinevideo.Data;

import java.io.Serializable;

/**
 * 任务数据表
 * @TableName task
 */
public class VideoCatch implements Serializable {
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
    private String catchInfo;

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
    public String getCatchInfo() {
        return catchInfo;
    }

    /**
     * 任务完成情况json字符串
     */
    public void setCatchInfo(String catchInfo) {
        this.catchInfo = catchInfo;
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
        VideoCatch other = (VideoCatch) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getCatchInfo() == null ? other.getCatchInfo() == null : this.getCatchInfo().equals(other.getCatchInfo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getCatchInfo() == null) ? 0 : getCatchInfo().hashCode());
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
        sb.append(", taskinfo=").append(catchInfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}