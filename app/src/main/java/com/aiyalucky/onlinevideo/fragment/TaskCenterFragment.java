package com.aiyalucky.onlinevideo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.TaskData;
import com.aiyalucky.onlinevideo.Data.TaskInfo;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.adapter.TaskItemAdapter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class TaskCenterFragment extends Fragment {
    private RecyclerView recyclerView;

    public TaskCenterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_center, container, false);

        recyclerView = view.findViewById(R.id.task_center_info_recycler);

        //设置分类列表数据
        if (AllData.MY_TASK_DATA.getTaskinfo() != null) {
            updateUI(AllData.MY_TASK_DATA);
        }

        return view;
    }

    /**
     * 更新UI界面数据的方法
     *
     * @param taskData 任务信息
     */
    public void updateUI(TaskData taskData) {

        List<TaskInfo> taskInfoList = new ArrayList<>();
        Type type = new TypeToken<HashMap<Integer, Integer>>() {
        }.getType();
        HashMap<Integer, Integer> hashMap = new Gson().fromJson(taskData.getTaskinfo(), type);
        for (Integer taskId : hashMap.keySet()) {
            Integer finishNum = hashMap.get(taskId);
            TaskInfo tempTaskInfo = new TaskInfo();
            tempTaskInfo.setId(taskId);
            tempTaskInfo.setFinishNum(finishNum);
            taskInfoList.add(tempTaskInfo);
        }

        //给recyclerview设置线性布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //更新界面视频数据
        recyclerView.setAdapter(new TaskItemAdapter(getContext(), taskInfoList));
    }
}
