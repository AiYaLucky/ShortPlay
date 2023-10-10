package com.aiyalucky.onlinevideo.Data;

/**
 * @ClassName TaskInfo
 * @Description
 * @Author xu xiao wei
 * @Date 2023/6/25 17:01
 * @Version 1.0
 */
public class HistoryVideoInfo {

    /**
     * 任务id
     */
    Integer id;

    /**
     * 追剧的剧集id
     */
    String historyInfo;

    public String getHistoryInfo() {
        return historyInfo;
    }

    public void setHistoryInfo(String historyInfo) {
        this.historyInfo = historyInfo;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
