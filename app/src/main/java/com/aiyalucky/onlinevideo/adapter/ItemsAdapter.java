package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.activity.VideosPlayerActivity;
import com.aiyalucky.onlinevideo.utils.DensityUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 17:32
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class ItemsAdapter extends BaseAdapter {
    private Context context;
    private List<VideoData> mVideoModels;

    public ItemsAdapter(Context context, List<VideoData> videoModels) {
        this.context = context;
        this.mVideoModels = videoModels;
    }

    @Override
    public int getCount() {
        return mVideoModels.size();
    }

    @Override
    public Object getItem(int position) {
        return mVideoModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);

            // 第一次创建convertView，进行宽度调整
            int width = DensityUtil.getVideoWidth(convertView.getContext(), 3);
            int titleHeight = DensityUtil.getDimens(convertView.getContext(), R.dimen.good_video_title_height);
            int descHeight = DensityUtil.getDimens(convertView.getContext(), R.dimen.good_video_desc);
            int horizontalSpacing = DensityUtil.getDimens(convertView.getContext(), R.dimen.good_video_vertical_spacing_dp);

            //计算出来视频整体的高度
            int height = (int) (width * 1.5) + titleHeight + descHeight + horizontalSpacing;

            convertView.setLayoutParams(new AbsListView.LayoutParams(width, height));
        }

        //视频对象
        VideoData item = mVideoModels.get(position);

        // 设置图片圆角
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).placeholder(R.drawable.loading).error(R.drawable.loading);

        //获取单个视频的宽度
        int width = DensityUtil.getVideoWidth(convertView.getContext(), 3);

        // 设置视频文本
        TextView textView = convertView.findViewById(R.id.video_item_name);
        textView.setText(item.getName());

        // 设置简介文本文本
        TextView textDesc = convertView.findViewById(R.id.video_desc);
        textDesc.setText(item.getDesc());

        //这个是视频展示里面单独视频封面的高度
        int imageHeight = (int) (width * 1.5);
        ImageView imageView = convertView.findViewById(R.id.video_item_image);

        //设置图片的宽高
        imageView.setLayoutParams(new FrameLayout.LayoutParams(width, imageHeight));

        // 设置图片
        Glide.with(convertView.getContext()).load(item.getImgurl()).apply(options)
//                .optionalCircleCrop() //圆形图片
//                .optionalCenterInside()
//                .optionalCenterCrop()
                .centerCrop() //图片铺满
                .into(imageView);

        //给这个图片设置点击监听
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideosPlayerActivity.class);
                Integer videoId = item.getVideoid();
                intent.putExtra("videoId", videoId);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}