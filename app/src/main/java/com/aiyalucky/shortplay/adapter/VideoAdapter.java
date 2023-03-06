package com.aiyalucky.shortplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.fragment.ShortPlayFragment;
import com.aiyalucky.shortplay.pojo.VideoData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/6 18:07
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<VideoData> mVideoList;
    private OnVideoItemClickListener mOnVideoItemClickListener;

    public VideoAdapter(Context context,List<VideoData> videoList,OnVideoItemClickListener listener) {
        mContext = context;
        mVideoList = videoList;
        mOnVideoItemClickListener = listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoData videoItem = mVideoList.get(position);
        // 绑定数据到视图上
        holder.bindData(videoItem);
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    /**
     * 加载更多数据
     */
    public void loadMore() {
        int startPosition = mVideoList.size();
        List<VideoData> videoList = ShortPlayFragment.getImageList();
        mVideoList.addAll(videoList);
        notifyItemRangeInserted(startPosition, videoList.size());
    }

    public interface OnVideoItemClickListener {
        void onVideoItemClick(VideoData videoItem);
    }

    class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mCoverImage;
        private TextView mTitleText;

        VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            mCoverImage = itemView.findViewById(R.id.cover);
            mTitleText = itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        void bindData(VideoData videoItem) {
            Glide.with(mContext).load(videoItem.getVideourl()).into(mCoverImage);
            mTitleText.setText(videoItem.getName());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            VideoData videoItem = mVideoList.get(position);
            mOnVideoItemClickListener.onVideoItemClick(videoItem);
        }
    }
}

