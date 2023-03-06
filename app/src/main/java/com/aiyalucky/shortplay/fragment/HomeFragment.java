package com.aiyalucky.shortplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.adapter.VideoAdapter;
import com.aiyalucky.shortplay.pojo.VideoData;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private VideoAdapter adapter;

    //...

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.video_list);
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new VideoAdapter(getActivity(), ShortPlayFragment.getImageList(), new VideoAdapter.OnVideoItemClickListener() {
            @Override
            public void onVideoItemClick(VideoData videoItem) {
                System.out.println(123);
            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // 添加滑动监听器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 如果是向上滑动，并且滑动到了列表的最后一个项
                if (dy > 0 && layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
                    // 加载更多数据
                    adapter.loadMore();
                }
            }
        });

        return rootView;
    }

}