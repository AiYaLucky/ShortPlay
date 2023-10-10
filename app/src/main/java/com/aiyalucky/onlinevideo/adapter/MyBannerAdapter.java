package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.activity.VideosPlayerActivity;
import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/21 15:05
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class MyBannerAdapter extends BannerAdapter<VideoData, MyBannerAdapter.BannerViewHolder> {

    public MyBannerAdapter(List<VideoData> mDatas) {
        //设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
        super(mDatas);
    }

    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setMaxHeight(300);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, VideoData videoData, int position, int size) {
        Glide.with(holder.imageView.getContext()).load(videoData.getImgurl()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.imageView.getContext();
                Intent intent = new Intent(context, VideosPlayerActivity.class);
                Integer videoId = videoData.getVideoid();
                intent.putExtra("videoId", videoId);
                context.startActivity(intent);
            }
        });
    }

    static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}