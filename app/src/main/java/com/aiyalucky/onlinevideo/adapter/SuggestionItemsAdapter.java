package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.activity.VideosPlayerActivity;
import com.aiyalucky.onlinevideo.model.VideoModel;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 17:32
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class SuggestionItemsAdapter extends BaseAdapter {
    private Context context;
    private List<VideoModel> mVideoModels;
    public SuggestionItemsAdapter(Context context, List<VideoModel> videoModels) {
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_suggestion_video_item, parent, false);
        }

        VideoModel item = mVideoModels.get(position);

        // 设置视频文本
        TextView name = convertView.findViewById(R.id.video_item_name);
        name.setText(item.getVideoName());

        // 视频介绍
        TextView desc = convertView.findViewById(R.id.video_item_desc);
        desc.setText(item.getVideoDesc());

        // 设置图片
        ImageView imageView = convertView.findViewById(R.id.video_item_image);
        Glide.with(convertView.getContext()).load(item.getVideoImage()).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideosPlayerActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}