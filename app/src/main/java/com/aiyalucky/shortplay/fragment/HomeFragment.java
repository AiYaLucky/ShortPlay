package com.aiyalucky.shortplay.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.https.DataUtils;
import com.aiyalucky.shortplay.pojo.VideoData;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

public class HomeFragment extends Fragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    //播放器
    private StandardGSYVideoPlayer player;
    //播放进度
    private long currentPositionWhenPlaying;

    private static List<VideoData> homeVideoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        player = rootView.findViewById(R.id.video_player);
        player.setUp(DataUtils.baseUrl + homeVideoList.get(0).getVideourl(), true, homeVideoList.get(0).getName());
        player.setSeekOnStart(currentPositionWhenPlaying);
        player.setAutoFullWithSize(true);
        player.setRotateViewAuto(true);// 自动旋转屏幕
        player.setLockLand(true);  // 横屏时锁定屏幕
        player.startPlayLogic();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        player.onVideoPause();
        currentPositionWhenPlaying = player.getCurrentPositionWhenPlaying();
    }

    public static void initHomeVideo(List<VideoData> videoDataList) {
        homeVideoList = videoDataList;
    }
}