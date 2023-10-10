package com.aiyalucky.onlinevideo.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aiyalucky.onlinevideo.dao.TaskDataDao;
import com.aiyalucky.onlinevideo.dao.VideoInfoDao;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/28 17:57
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
//exportSchema = false 参数，禁用 schema 导出功能
@Database(entities = {TaskData.class}, version = 1,exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDataDao taskDataDao();
}