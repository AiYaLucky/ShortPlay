package com.aiyalucky.shortplay.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/2/22 22:36
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class ImageAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<String> mImages;
    private final List<String> mTexts;

    /**
     *
     * @param context   上下文
     * @param images    传入的图片list，这里用string直接URL进来。
     * @param texts     对应图片的文本内容
     */
    public ImageAdapter(Context context, List<String> images, List<String> texts) {
        mContext = context;
        mImages = images;
        mTexts = texts;
    }

    public int getCount() {
        return mImages.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        TextView textView;

        if (convertView == null) {
            //不存在的话重新初始化这些内容
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
            imageView.setPadding(12, 12, 12, 12);

            textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new GridView.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(12, 12, 12, 12);

            LinearLayout layout = new LinearLayout(mContext);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.addView(imageView);
            layout.addView(textView);

            convertView = layout;
        } else {
            LinearLayout layout = (LinearLayout) convertView;
            imageView = (ImageView) layout.getChildAt(0);
            textView = (TextView) layout.getChildAt(1);
        }
        // 加载在线的图片资源
        Glide.with(mContext).load(mImages.get(position)).into(imageView);
        // imageView.setImageDrawable(mImages.get(position));
        textView.setText(mTexts.get(position));

        return convertView;
    }
}