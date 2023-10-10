package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.VideoData;

import java.util.List;

/**
 * @ClassName HotAdapter
 * @Description
 * @Author xu xiao wei
 * @Date 2023/6/12 15:01
 * @Version 1.0
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    private Context context;
    private List<VideoData> mVideoModels;

    public HotAdapter(Context context, List<VideoData> videoList) {
        this.context = context;
        this.mVideoModels.addAll(videoList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    // 在这里定义必要的成员变量和构造函数

    // 在这里实现必要的方法，如 onCreateViewHolder()、onBindViewHolder() 和 getItemCount()

    // 在这里定义视图持有者类 ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 在这里定义视图持有者需要引用的视图组件

        public ViewHolder(View itemView) {
            super(itemView);
            // 在这里初始化视图持有者的视图组件
        }
    }
}

