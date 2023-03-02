package com.aiyalucky.shortplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.pojo.MyData;

import java.util.ArrayList;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/1 22:49
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MyData> dataList;

    public CategoryAdapter(Context context, ArrayList<MyData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public void setData(ArrayList<MyData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.short_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyData data = dataList.get(position);

        holder.titleTextView.setText(data.getTitle());

        // 创建一个 GridLayoutManager，指定列数为3
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        // 设置 RecyclerView 的布局管理器为 GridLayoutManager
        holder.recyclerView.setLayoutManager(layoutManager);

        // 新建适配器，设置给recyclerView
        ItemAdapter itemAdapter = new ItemAdapter(context, data.getItems());
        holder.recyclerView.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.categoryTitleTextView);
            recyclerView = itemView.findViewById(R.id.categoryRecyclerView);
        }
    }
}
