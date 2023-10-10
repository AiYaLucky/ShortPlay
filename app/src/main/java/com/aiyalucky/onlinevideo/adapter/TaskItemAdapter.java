package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.TaskInfo;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.activity.UserGuideActivity;

import java.util.List;


/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 17:32
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {
    private Context context;
    private List<TaskInfo> mTaskList;


    public TaskItemAdapter(Context context, List<TaskInfo> taskList) {
        this.context = context;
        this.mTaskList = taskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskInfo taskData = mTaskList.get(position);

        // 设置数据到视图组件
        holder.taskNameTextView.setText(AllData.TASK_NAME.get(taskData.getId()));
        holder.taskRewardDescTextView.setText(AllData.TASK_REWARD_DESC.get(taskData.getId()));
        holder.taskDescTextView.setText(AllData.TASK_DESC.get(taskData.getId()));

        // 给去完成按钮添加点击事件
        holder.taskButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 根据任务信息执行相应的操作
                performTaskAction(taskData);
            }
        });
    }

    // 执行任务操作的方法
    private void performTaskAction(TaskInfo taskData) {
        // 根据任务的属性执行相应的操作
        int taskId = taskData.getId();

        // 根据不同的任务ID执行不同的操作
        switch (taskId) {
            case 1:
                Intent intent = new Intent(context, UserGuideActivity.class);
                intent.putExtra("videoId", taskId);
                context.startActivity(intent);
                break;
            case 2:
                // 任务2是前往签到页面签到
                Toast.makeText(context, "执行任务2的操作", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                // 任务3是生成一个分享页面进行分享
                Toast.makeText(context, "执行任务3的操作", Toast.LENGTH_SHORT).show();
                break;
            // 添加其他任务的操作...
            default:
                Toast.makeText(context, "执行任务" + taskId + "的操作", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView taskNameTextView;
        TextView taskRewardDescTextView;
        TextView taskDescTextView;
        Button taskButtonView;
        ImageView  taskRewardIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            taskNameTextView = itemView.findViewById(R.id.task_name);
            taskRewardDescTextView = itemView.findViewById(R.id.task_reward_desc);
            taskDescTextView = itemView.findViewById(R.id.task_desc);
            taskButtonView = itemView.findViewById(R.id.task_button);
        }
    }
}