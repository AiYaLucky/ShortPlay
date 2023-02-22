package com.aiyalucky.shortplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.adapter.ImageAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShortPlayFragment extends Fragment {

    private ShortPlayViewModel mViewModel;
    private GridView mGridView;
    private List<String> mImageList;
    private List<String> mTexts;
    private ImageAdapter mAdapter;

    public static ShortPlayFragment newInstance() {
        return new ShortPlayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_short_play, container, false);
        HashMap<String, List<String>> itemData = this.getItemData();
        mGridView = view.findViewById(R.id.grid_view);
        mGridView.getLayoutParams().height = getResources().getDisplayMetrics().heightPixels / 2;
        ImageAdapter adapter = new ImageAdapter(getActivity(), itemData.get("imageList"), itemData.get("texts"));
        mGridView.setAdapter(adapter);

        return view;
    }

    /**
     * 初始化 mImages 和 mTexts 数据【后续这里有服务器回来的数据内容，直接用服务器的数据内容即可】
     * @return 返回一个hashmap 里面imageList是图片信息  texts是对应文本信息
     */
    public HashMap<String, List<String>> getItemData() {
        HashMap<String, List<String>> resultMap = new HashMap<>(16);
        List<String> imageList = new ArrayList<>();
        imageList.add("https://img1.baidu.com/it/u=953680778,1162160249&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=2720349980,3743060535&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=2028618468,66183889&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img0.baidu.com/it/u=2137593542,2407782906&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img0.baidu.com/it/u=1851170573,2768937094&fm=253&fmt=auto&app=138&f=JPG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=2028618468,66183889&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=953680778,1162160249&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=2720349980,3743060535&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=2028618468,66183889&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=953680778,1162160249&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=2720349980,3743060535&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        imageList.add("https://img1.baidu.com/it/u=2028618468,66183889&fm=253&fmt=auto&app=138&f=JPEG?w=200&h=200");
        resultMap.put("imageList", imageList);

        List<String> texts = new ArrayList<>();
        texts.add("Image 1");
        texts.add("Image 2");
        texts.add("Image 3");
        texts.add("Image 4");
        texts.add("Image 5");
        texts.add("Image 6");
        texts.add("Image 7");
        texts.add("Image 8");
        texts.add("Image 9");
        resultMap.put("texts", texts);
        return resultMap;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShortPlayViewModel.class);
        // TODO: Use the ViewModel
    }
}