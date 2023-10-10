package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
 * @Date: 2023/4/26 11:17
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class HotVideoAdapter extends RecyclerView.Adapter<HotVideoAdapter.VideoViewHolder> {

    private Context mContext;
    private List<VideoData> mVideoList;

    public HotVideoAdapter(Context context, List<VideoData> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.hot_item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoData item = mVideoList.get(position);

        // 设置视频名字
        holder.videoName.setText(item.getName());

        // 设置简介文本文本
        holder.videoDesc.setText(item.getDesc());

        // 设置图片圆角
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).placeholder(R.drawable.loading).error(R.drawable.loaderror);

        // 设置视频封面
        Glide.with(mContext).load(item.getImgurl()).apply(options)
//                .optionalCircleCrop() //圆形图片
//                .optionalCenterInside()
//                .optionalCenterCrop()
                .centerCrop() //图片铺满
                .into(holder.imageView);

        //获取的热门视频的总高度
        int hotVideoHeight = DensityUtil.getDimens(mContext, R.dimen.hot_video_layout_height);

        //获取热门视频标题和简介的高度
        int hotVideoTitleHeight = DensityUtil.getDimens(mContext, R.dimen.hot_video_title_height);

        //获取热门视频简介的高度
        int hotVideoDescHeight = DensityUtil.getDimens(mContext, R.dimen.hot_video_desc_height);

        //计算图片应该有的高度
        int imageHeight = hotVideoHeight - hotVideoTitleHeight - hotVideoDescHeight;

        //设置图片高度和宽度
        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.height = imageHeight;
        params.width = (int) (imageHeight / 1.5);
        holder.imageView.setLayoutParams(params);

        holder.videoName.setWidth(params.width);
        holder.videoDesc.setWidth(params.width);

        //设置热门视频的点击事件
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideosPlayerActivity.class);
                Integer videoId = item.getVideoid();
                intent.putExtra("videoId", videoId);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView videoName;
        private TextView videoDesc;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.video_item_image);
            videoName = itemView.findViewById(R.id.video_item_name);
            videoDesc = itemView.findViewById(R.id.video_desc);
        }
    }


    public static class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int horizontalSpacing;

        public HorizontalSpaceItemDecoration(int spacing) {
            this.horizontalSpacing = spacing;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int itemCount = parent.getAdapter().getItemCount();

            // 判断是否是最后一个 item,如果不是最后一个就设置间隔，这样可以达到最后一个右侧不会出现间隔导致右侧空白很多。
            if (position != itemCount - 1) {
                outRect.right = horizontalSpacing;
            }
        }
    }

}
