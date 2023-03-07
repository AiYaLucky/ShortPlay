package com.aiyalucky.shortplay.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.adapter.CategoryAdapter;
import com.aiyalucky.shortplay.https.DataUtils;
import com.aiyalucky.shortplay.https.VideoDataCallback;
import com.aiyalucky.shortplay.pojo.ItemData;
import com.aiyalucky.shortplay.pojo.MyData;
import com.aiyalucky.shortplay.pojo.VideoData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShortPlayFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;


    /**
     * 主页面的视频数据
     */
    private static List<VideoData> imageList = new ArrayList<>();
    private SharedPreferences videoSP;
    private SharedPreferences.Editor videoSpEditor;

    /**
     * 分类有几种，默认三个
     */
    private static Set<String> category;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.short_play_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        videoSP = getActivity().getSharedPreferences("video", Context.MODE_PRIVATE);
        videoSpEditor = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        adapter = new CategoryAdapter(getContext(), createData(false));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setData(createData(true));
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });

        return view;
    }

    /**
     * 数据更新
     *
     * @param refresh 是否需要从服务器重新获取数据
     * @return
     */
    private ArrayList<MyData> createData(boolean refresh) {

        //从服务器更新数据
        if (refresh) {
            DataUtils.videoDataRefresh(18, new VideoDataCallback() {
                @Override
                public void onVideoDataReceived(List<VideoData> videoDataList) {
                    initVideoData(videoDataList);
                }
            });
        }

        ArrayList<MyData> dataList = new ArrayList<>();

        for (String categoryName : category) {
            MyData data = new MyData();
            data.setTitle(categoryName);
            List<ItemData> itemList = new ArrayList<>();
            for (int j = 0; j < imageList.size(); j++) {
                ItemData item = new ItemData();
                VideoData videoData = imageList.get(j);
                if (!videoData.getType().equals(categoryName)) {
                    continue;
                }
                item.setImageRes(videoData.getImgurl());
                item.setText(videoData.getDesc());
                item.setVideoId(videoData.getVideoid());
                itemList.add(item);
            }
            data.setItems(itemList);
            dataList.add(data);
        }
        return dataList;
    }

    public void initVideoData(List<VideoData> videoDataList) {
        imageList = videoDataList;
        category = new HashSet<>();
        //更新分类数据
        for (VideoData videoData : imageList) {
            category.add(videoData.getType());
        }
    }

    public static List<VideoData> getImageList() {
        return imageList;
    }
}