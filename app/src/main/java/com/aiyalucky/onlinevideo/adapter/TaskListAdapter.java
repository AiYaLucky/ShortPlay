package com.aiyalucky.onlinevideo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 17:32
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class TaskListAdapter extends BaseAdapter {
    private Context context;
    private List<VideoData> mTaskList = new ArrayList<>();

    public TaskListAdapter(Context context, List<VideoData> taskList) {
        this.context = context;
        this.mTaskList = taskList;
    }

    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task_list, parent, false);
        }

        return convertView;
    }
}