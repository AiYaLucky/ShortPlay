package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.VideoPoint;
import com.aiyalucky.onlinevideo.R;

import java.util.List;


/**
 * 提取
 *
 * @Author: xu xiao wei
 * @Date: 2023/4/20 17:32
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class VideoPointItemAdapter extends RecyclerView.Adapter<VideoPointItemAdapter.ViewHolder> {
    private Context context;
    private List<VideoPoint> mRechargeRecordList;


    public VideoPointItemAdapter(Context context, List<VideoPoint> recharges) {
        this.context = context;
        this.mRechargeRecordList = recharges;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recharge_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoPoint recharge = mRechargeRecordList.get(position);

        // 设置数据到视图组件
        holder.orderName.setText(String.valueOf(recharge.getOrderId()));
        holder.orderTime.setText(String.valueOf(recharge.getRechargeTime()));
        holder.orderInfo.setText(String.valueOf(recharge.getRechargeMoney()));

        // 给去完成按钮添加点击事件
        holder.orderInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这里预留给点击订单查看订单详情这个需求用，当前界面加个布局弹出来，显示订单详情。
                Toast.makeText(context, "前往执行查看记录详情", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return mRechargeRecordList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderName;
        TextView orderTime;
        TextView orderInfo;

        RelativeLayout orderInfoLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            orderName = itemView.findViewById(R.id.order_name);
            orderTime = itemView.findViewById(R.id.order_time);
            orderInfo = itemView.findViewById(R.id.order_info);
            orderInfoLayout = itemView.findViewById(R.id.order_info_layout);
        }
    }
}