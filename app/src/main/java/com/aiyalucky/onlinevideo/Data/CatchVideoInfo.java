package com.aiyalucky.onlinevideo.Data;

/**
 * @ClassName TaskInfo
 * @Description
 * @Author xu xiao wei
 * @Date 2023/6/25 17:01
 * @Version 1.0
 */
public class CatchVideoInfo {

    /**
     * 任务id
     */
    Integer id;

    /**
     * 追剧的剧集id
     */
    String catchInfo;

    public String getCatchInfo() {
        return catchInfo;
    }

    public void setCatchInfo(String catchInfo) {
        this.catchInfo = catchInfo;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
