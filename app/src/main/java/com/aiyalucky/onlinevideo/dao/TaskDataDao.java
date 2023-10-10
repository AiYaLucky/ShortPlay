package com.aiyalucky.onlinevideo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aiyalucky.onlinevideo.Data.TaskData;
import com.aiyalucky.onlinevideo.Data.VideoInfo;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/28 17:51
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
@Dao
public interface TaskDataDao {
    /**
     * 查询操作通过query来指定执行的sql语句
     *
     * @return
     */
    @Query("SELECT * FROM task_data")
    List<TaskData> selectAllData();

    /**
     * 插入操作，@Insert注解支持插入，不用query来指定
     *
     * @param dataList
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(TaskData dataList);

    /**
     * 删除操作，@Delete注解支持删除，不用query来指定
     *
     * @param taskData
     */
    @Delete
    void delete(TaskData taskData);
}
