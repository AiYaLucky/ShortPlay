package com.aiyalucky.onlinevideo.utils;

import android.content.Context;

import androidx.room.Room;

import com.aiyalucky.onlinevideo.Data.TaskDatabase;
import com.aiyalucky.onlinevideo.Data.VideoDatabase;
import com.aiyalucky.onlinevideo.Data.VideoInfobase;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/28 17:59
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class DbUtil {
    private static VideoDatabase INSTANCE_VIDEO;
    private static VideoInfobase INSTANCE_VIDEO_INFO;
    private static TaskDatabase INSTANCE_TASK_DATA;

    public static VideoDatabase getSQLLite(Context context) {
        if (INSTANCE_VIDEO == null) {
            synchronized (DbUtil.class) {
                String DBNAME = "online_videos";
                INSTANCE_VIDEO = Room.databaseBuilder(context, VideoDatabase.class, DBNAME).build();
            }
        }
        return INSTANCE_VIDEO;
    }

    public static VideoInfobase getSQLLiteInfo(Context context) {
        if (INSTANCE_VIDEO_INFO == null) {
            synchronized (DbUtil.class) {
                String DBNAME = "online_videos_info";
                INSTANCE_VIDEO_INFO = Room.databaseBuilder(context, VideoInfobase.class, DBNAME).build();
            }
        }
        return INSTANCE_VIDEO_INFO;
    }

    public static TaskDatabase getSQLLiteTaskData(Context context) {
        if (INSTANCE_TASK_DATA == null) {
            synchronized (DbUtil.class) {
                String DBNAME = "task_data";
                INSTANCE_TASK_DATA = Room.databaseBuilder(context, TaskDatabase.class, DBNAME).build();
            }
        }
        return INSTANCE_TASK_DATA;
    }
}
