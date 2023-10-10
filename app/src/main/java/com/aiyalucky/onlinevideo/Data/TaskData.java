package com.aiyalucky.onlinevideo.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * 用户视频数据表,映射到SQLite数据库
 */
@Entity(tableName = "task_data")
public class TaskData implements Serializable {
    /**
     * 自增id
     */
    @PrimaryKey
    private Integer id;

    /**
     * 用户唯一id
     */
    private String uid;

    /**
     * 任务完成情况json字符串
     */
    private String taskinfo;

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
    public String getTaskinfo() {
        return taskinfo;
    }

    /**
     * 任务完成情况json字符串
     */
    public void setTaskinfo(String taskinfo) {
        this.taskinfo = taskinfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getTaskinfo() == null) ? 0 : getTaskinfo().hashCode());
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
        sb.append(", taskinfo=").append(taskinfo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}