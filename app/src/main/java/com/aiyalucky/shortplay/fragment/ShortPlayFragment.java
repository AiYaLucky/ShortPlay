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
import com.aiyalucky.shortplay.pojo.ItemData;
import com.aiyalucky.shortplay.pojo.MyData;
import com.aiyalucky.shortplay.pojo.VideoData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.short_play_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        videoSP = getActivity().getSharedPreferences("video", Context.MODE_PRIVATE);
        videoSpEditor = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE).edit();
        videoDataToList();
        adapter = new CategoryAdapter(getContext(), createData());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setData(createData());
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });

        return view;
    }

    private ArrayList<MyData> createData() {
        ArrayList<MyData> dataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            MyData data = new MyData();
            data.setTitle("类别 " + (i + 1));
            List<ItemData> itemList = new ArrayList<>();
            for (int j = 0; j < imageList.size(); j++) {
                ItemData item = new ItemData();
                VideoData videoData = imageList.get(j);
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


    private List<VideoData> videoDataToList() {
        Map<String, ?> videoSPAll = videoSP.getAll();
        for (Map.Entry<String, ?> entry : videoSPAll.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            // do something with key and value
            VideoData videoData = new Gson().fromJson(value.toString(), VideoData.class);
            imageList.add(videoData);
        }
        Collections.sort(imageList);
        return imageList;

    }
}