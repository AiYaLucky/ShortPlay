package com.aiyalucky.onlinevideo.adapter;

import android.content.Context;
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
import com.aiyalucky.onlinevideo.utils.DensityUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * @ClassName ShowListAdapter
 * @Description
 * @Author xu xiao wei
 * @Date 2023/5/11 15:31
 * @Version 1.0
 */
public class ShowListAdapter extends BaseAdapter {

    private Context context;
    private List<VideoData> data;
    private OnVideoClickListener mVideoClickListener;

    public ShowListAdapter(Context context, List<VideoData> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnVideoClickListener(OnVideoClickListener listener) {
        mVideoClickListener = listener;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            //获取单个视频的宽度
            int imageWidth = DensityUtil.getVideoWidth(convertView.getContext(), 4);
            //这里是设置整个视频展示包括标题和简介的高度
            //视频图片宽高比600:900,然后每个视频还有一个标题和一个简介文本的高度
            int horizontalSpacing = DensityUtil.getDimens(convertView.getContext(), R.dimen.good_video_vertical_spacing_dp);
            int height = (int) ((imageWidth * 1.5) + horizontalSpacing);

            convertView.setLayoutParams(new AbsListView.LayoutParams(imageWidth, height));
        }

        VideoData item = data.get(position);

        // 设置图片圆角
        RoundedCorners roundedCorners = new RoundedCorners(10);
        RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).placeholder(R.drawable.loading).error(R.drawable.loading);

        //获取单个视频的宽度
        int imageWidth = DensityUtil.getVideoWidth(convertView.getContext(), 4);

        //这个是视频展示里面单独视频封面的高度
        int imageHeight = (int) (imageWidth * 1.5);
        ImageView imageView = convertView.findViewById(R.id.video_item_image);

        //设置图片的宽高
        imageView.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight));

        // 设置图片
        Glide.with(convertView.getContext()).load(item.getImgurl()).apply(options).centerCrop().into(imageView);

        //给这个图片设置点击监听
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoClickListener != null) {
                    mVideoClickListener.onVideoClick(position);
                }
            }
        });

        //给集数说明文本设置宽高,
        TextView textView = convertView.findViewById(R.id.video_item_text);
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        layoutParams.width = imageWidth;
        textView.setLayoutParams(layoutParams);
        textView.setText("第" + (position + 1) + "集");
        return convertView;
    }

    public interface OnVideoClickListener {
        void onVideoClick(int position);
    }
}