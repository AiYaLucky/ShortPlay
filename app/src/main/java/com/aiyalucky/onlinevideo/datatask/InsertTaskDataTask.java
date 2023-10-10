package com.aiyalucky.onlinevideo.datatask;

import android.content.Context;
import android.os.AsyncTask;

import com.aiyalucky.onlinevideo.Data.TaskData;
import com.aiyalucky.onlinevideo.dao.TaskDataDao;
import com.aiyalucky.onlinevideo.utils.DbUtil;

/**
 * @ClassName InsertDataTask
 * @Description
 * @Author xu xiao wei
 * @Date 2023/5/6 15:03
 * @Version 1.0
 */
//定义一个异步任务
public class InsertTaskDataTask extends AsyncTask<Void, Void, Void> {
    private TaskDataDao taskDataDao;
    private TaskData taskData;

    private OnInsertTaskCompleteListener listener;

    /**
     * 构造传入上下文和待存储数据
     * @param context 上下文
     * @param TaskData 待存储数据
     */
    public InsertTaskDataTask(Context context, TaskData TaskData, OnInsertTaskCompleteListener listener) {
        this.taskDataDao = DbUtil.getSQLLiteTaskData(context).taskDataDao();
        this.taskData = TaskData;
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
        taskDataDao.insertAll(taskData);
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

