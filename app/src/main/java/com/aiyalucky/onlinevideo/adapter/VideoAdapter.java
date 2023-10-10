package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.model.VideoModel;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/26 11:17
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<VideoData> mVideoList;

    public VideoAdapter(Context context, List<VideoData> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoData item = mVideoList.get(position);
        Glide.with(mContext).load(item.getImgurl()).into(holder.imageUrl);
        holder.videoName.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageUrl;
        private TextView videoName;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageUrl = itemView.findViewById(R.id.video_item_image);
            videoName = itemView.findViewById(R.id.video_item_name);
        }
    }

}
