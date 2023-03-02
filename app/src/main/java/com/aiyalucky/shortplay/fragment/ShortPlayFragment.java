package com.aiyalucky.shortplay.fragment;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShortPlayFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;

    /**
     * 主页面的视频数据
     */
    private List<String> imageList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.short_play_fragment, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
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
            for (int j = 0; j < 6; j++) {
                ItemData item = new ItemData();
                item.setImageRes(getRandomImageResId());
                item.setText("说明： " + (j + 1));
                item.setVideoId(j + 1);
                itemList.add(item);
            }
            data.setItems(itemList);
            dataList.add(data);
        }
        return dataList;
    }



    private String getRandomImageResId() {
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
        Random random = new Random();
        int index = random.nextInt(imageList.size());

        return imageList.get(index);
    }
}