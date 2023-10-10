package com.aiyalucky.onlinevideo.datatask;

import android.content.Context;
import android.os.AsyncTask;

import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.dao.VideoDao;
import com.aiyalucky.onlinevideo.utils.DbUtil;

import java.util.List;

/**
 * @ClassName InsertDataTask
 * @Description
 * @Author xu xiao wei
 * @Date 2023/5/6 15:03
 * @Version 1.0
 */
//定义一个异步任务
public class InsertVideoDataTask extends AsyncTask<Void, Void, Void> {
    private VideoDao videoDao;
    private List<VideoData> videoDataList;

    private OnInsertTaskCompleteListener listener;

    /**
     * 构造传入上下文和待存储数据
     * @param context 上下文
     * @param videoDataList 待存储数据
     */
    public InsertVideoDataTask(Context context, List<VideoData> videoDataList, OnInsertTaskCompleteListener listener) {
        this.videoDao = DbUtil.getSQLLite(context).videoDao();
        this.videoDataList = videoDataList;
        this.listener = listener;
    }

    /**
     * 实例化的 InsertDataTask对象执行execute()的时候会触发这个方法
     * @param voids The parameters of the task.
     *
     * @return
     */
    @Override
    protected Void doInBackground(Void... voids) {
        videoDao.insertAll(videoDataList);
        return null;
    }

    @Override
    protected void onPostExecute(Void unused) {
        listener.onInsertTaskComplete();
    }

    public interface OnInsertTaskCompleteListener {
        void onInsertTaskComplete();
    }
}

