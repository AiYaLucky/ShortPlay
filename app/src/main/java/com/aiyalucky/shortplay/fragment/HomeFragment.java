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
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private VideoAdapter adapter;

    //播放器
    private StandardGSYVideoPlayer player;
    //播放进度
    private long currentPositionWhenPlaying;

    private List<VideoData> homeVideoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = rootView.findViewById(R.id.video_list);
        player = rootView.findViewById(R.id.video_player);
        player.setUp("https://v26-web.douyinvod.com/fc12668e3391784a4646bc6be66bb692/64060788/video/tos/cn/tos-cn-ve-15c001-alinc2/o4ZBGbxLMAB7njeogjFDAeCEjBleQS8HHKdzNn/?a=6383&ch=26&cr=3&dr=0&lr=all&cd=0%7C0%7C0%7C3&cv=1&br=1595&bt=1595&cs=0&ds=4&ft=bvTKJbQQqUGXf_.ZPo0OW_EklpPiXp.AvMVJERahjgbPD-I&mime_type=video_mp4&qs=0&rc=ZWk7ODxnaDU1OWU1PGU6ZkBpajg5cjU6ZmlyaTMzNGkzM0BeLl81XjRiXzExLjRiYmEwYSMuYHJwcjRnazJgLS1kLS9zcw%3D%3D&l=20230306222949DBCB9F58EE1CA9204CED&btag=28000", true, "视频名称");
        player.setSeekOnStart(currentPositionWhenPlaying);
        player.setAutoFullWithSize(true);
        player.setRotateViewAuto(true);// 自动旋转屏幕
        player.setLockLand(true);  // 横屏时锁定屏幕
        player.startPlayLogic();
        // 添加滑动监听器
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 如果是向上滑动，并且滑动到了列表的最后一个项
                if (dy > 0 && layoutManager.findLastVisibleItemPosition() == adapter.getItemCount() - 1) {
                    // 加载更多数据
                    adapter.loadMore();
                } else if (dy > 0) {
                    player.setUp("https://v26-web.douyinvod.com/fc12668e3391784a4646bc6be66bb692/64060788/video/tos/cn/tos-cn-ve-15c001-alinc2/o4ZBGbxLMAB7njeogjFDAeCEjBleQS8HHKdzNn/?a=6383&ch=26&cr=3&dr=0&lr=all&cd=0%7C0%7C0%7C3&cv=1&br=1595&bt=1595&cs=0&ds=4&ft=bvTKJbQQqUGXf_.ZPo0OW_EklpPiXp.AvMVJERahjgbPD-I&mime_type=video_mp4&qs=0&rc=ZWk7ODxnaDU1OWU1PGU6ZkBpajg5cjU6ZmlyaTMzNGkzM0BeLl81XjRiXzExLjRiYmEwYSMuYHJwcjRnazJgLS1kLS9zcw%3D%3D&l=20230306222949DBCB9F58EE1CA9204CED&btag=28000", true, "视频名称111");
                    player.startPlayLogic();
                }
            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.onVideoPause();
        currentPositionWhenPlaying = player.getCurrentPositionWhenPlaying();
    }
}