package com.aiyalucky.onlinevideo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 17:32
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class CategoryAdapter extends BaseAdapter {
    private TextView categoryTitle;
    private Context context;
    private List<VideoData> allVideoList = new ArrayList<>();
    //视频信息
    private GridView mItemsGrid;

    public CategoryAdapter(Context context, List<VideoData> allVideoList) {
        this.context = context;
        this.allVideoList = allVideoList;
    }

    @Override
    public int getCount() {
        return allVideoList.size();
    }

    @Override
    public Object getItem(int position) {
        return allVideoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category, parent, false);
        }
        mItemsGrid = convertView.findViewById(R.id.category_grid);

        //根据数量计算出三个一行，一共多少行
        int rows = allVideoList.size() / 3 + (allVideoList.size() % 3 > 0 ? 1 : 0);

        //获得单个视频的高度数值
        int videoWidth = DensityUtil.getVideoWidth(convertView.getContext(), 3);

        //计算出单个视频的高度,图片的宽高比是600:900，标题和简介高度计算进去，还有行间距
        int titleHeight = DensityUtil.getDimens(convertView.getContext(), R.dimen.good_video_title_height);
        int descHeight = DensityUtil.getDimens(convertView.getContext(), R.dimen.good_video_desc);
        int horizontalSpacing = DensityUtil.getDimens(convertView.getContext(), R.dimen.good_video_vertical_spacing_dp);
        int imageHeight = (int) Math.ceil((double) videoWidth * 1.5);
        int videoHeight = imageHeight + titleHeight + descHeight + horizontalSpacing;

        //单个视频的高度乘以行数 + 主界面底部菜单栏的高度 + 最后一个水平间隔(*2这样显示起来好看点，不然简介直接贴底边很诡异)，就是Grid布局的高度
        int activityBottomMenuHeight = DensityUtil.getDimens(convertView.getContext(), R.dimen.main_activity_marginBottom);
        int height = rows * videoHeight + activityBottomMenuHeight + horizontalSpacing * 2;

        //Grid布局的宽度设置为屏幕宽度即可
        int width = DensityUtil.getScreenWidth(context);

        //使设置好的布局参数应用到控件
        ViewGroup.LayoutParams linearParams = new LinearLayout.LayoutParams(width, height);
        mItemsGrid.setLayoutParams(linearParams);

        //设置适配器，显示内容,排除一下重复部分
        List<VideoData> videoList = new ArrayList<>();
        List<Integer> videoIdList = new ArrayList<>();
        for (VideoData video : allVideoList) {
            if (videoIdList.contains(video.getVideoid())){
                continue;
            }
            videoIdList.add(video.getVideoid());
            videoList.add(video);
        }
        mItemsGrid.setAdapter(new ItemsAdapter(convertView.getContext(), videoList));
        return convertView;
    }
}