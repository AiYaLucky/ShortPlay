package com.aiyalucky.onlinevideo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.MainActivity;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.activity.VideosPlayerActivity;
import com.aiyalucky.onlinevideo.adapter.HotVideoAdapter;
import com.aiyalucky.onlinevideo.adapter.ItemsAdapter;
import com.aiyalucky.onlinevideo.adapter.MyBannerAdapter;
import com.aiyalucky.onlinevideo.datatask.SelectDataTask;
import com.aiyalucky.onlinevideo.utils.DensityUtil;
import com.youth.banner.Banner;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class HomePageFragment extends Fragment implements SelectDataTask.OnDataTaskCompleteListener, View.OnClickListener {
    /**
     * 热门视频
     */
    private RecyclerView mHotRecycler;

    /**
     * 分类视频
     */
    private GridView mCategoryGrid;
    private Banner<VideoData, MyBannerAdapter> banner;

    /**
     * 剧集分类
     */
    private ImageView videoSiftButton;

    /**
     * 我的追剧
     */
    private ImageView myCatchVideo;

    /**
     * 播放历史
     */
    private ImageView videoPlayHistory;

    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);

        //设置banner轮播图
        banner = view.findViewById(R.id.banner);

        //设置热门视频
        mHotRecycler = view.findViewById(R.id.hot_recycler_view);

        //设置分类列表数据
        mCategoryGrid = view.findViewById(R.id.video_sift_grid);

//        videoSiftButton = view.findViewById(R.id.video_sift_button);

        myCatchVideo = view.findViewById(R.id.my_catch_video);

        videoPlayHistory = view.findViewById(R.id.video_play_history);

        initClickListener();
        if (AllData.BANNER_VIDEO_DATA.size() > 0 || AllData.HOT_VIDEO_DATA.size() > 0 || AllData.ALL_VIDEO_DATA.size() > 0) {
            updateUI(AllData.HOT_VIDEO_DATA, AllData.BANNER_VIDEO_DATA, AllData.GOOD_VIDEO_DATA);
        }
        return view;
    }

    /**
     * 本界面的按钮点击事件
     */
    private void initClickListener() {
//        videoSiftButton.setOnClickListener(this);
        myCatchVideo.setOnClickListener(this);
        videoPlayHistory.setOnClickListener(this);
    }

    /**
     * 更新UI界面数据的方法
     *
     * @param bannerList 轮播图数据
     * @param videoList  视频数据
     */
    public void updateUI(List<VideoData> hotList, List<VideoData> bannerList, List<VideoData> videoList) {

        //更新轮播图
        banner.setAdapter(new MyBannerAdapter(bannerList));

        //设置banner指示器信息
        banner.setIndicator(new CircleIndicator(getContext()))
                .setIndicatorGravity(IndicatorConfig.Direction.CENTER)
                .setIndicatorSelectedColor(Color.RED)
                .setIndicatorNormalColor(Color.WHITE);

        //处理轮播图点击事件
        banner.setOnBannerListener((data, position) -> {
            //获取点击的视频剧集id，然后跳转到播放类
            Integer videoId = data.getVideoid();
            Intent intent = new Intent(getContext(), VideosPlayerActivity.class);
            intent.putExtra("videoId", videoId);
            startActivity(intent);
        });

        //更新界面视频数据
        // 设置布局管理器,设置为水平布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHotRecycler.setLayoutManager(layoutManager);


        //热门视频水平间隔，跟好剧看视频的间隔一样
        int horizontalSpacing = DensityUtil.getDimens(getContext(), R.dimen.good_video_horizontal_spacing_dp);
        HotVideoAdapter.HorizontalSpaceItemDecoration itemDecoration = new HotVideoAdapter.HorizontalSpaceItemDecoration(horizontalSpacing);
        mHotRecycler.addItemDecoration(itemDecoration);

        // 设置热门资源适配器
        mHotRecycler.setAdapter(new HotVideoAdapter(getContext(), hotList));

        //好剧追不停适配器设置
        mCategoryGrid.setAdapter(new ItemsAdapter(getContext(), videoList));
    }

    /**
     * 这个给等待insertdatatask完成之后执行
     */
    public void onDataTaskComplete() {
        //执行查询数据的任务 因为execute()已经弃用了，换成executeOnExecutor()方法
        new SelectDataTask(getContext(), getParentFragmentManager(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * 这个是给等待SelectDataTask里面执行的更新调用的
     *
     * @param bannerList banner数据
     * @param videoList  视频数据
     */

    @Override
    public void onDataTaskComplete(List<VideoData> hotList, List<VideoData> bannerList, List<VideoData> videoList) {
        updateUI(hotList, bannerList, videoList);
    }

    /**
     * 按钮点击事件
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (viewId == R.id.video_sift_button) {
            // 视频剧集分类按钮被点击
            // 显示VideoSiftFragment界面
            VideoSiftFragment videoSiftFragment = new VideoSiftFragment();
            fragmentTransaction.replace(R.id.container, videoSiftFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (viewId == R.id.my_catch_video) {
            // 我的追剧按钮被点击
            // 视频剧集分类按钮被点击
            // 显示VideoSiftFragment界面
            VideoCatchFragment videoCatchFragment = new VideoCatchFragment();
            fragmentTransaction.replace(R.id.container, videoCatchFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (viewId == R.id.video_play_history) {
            // 播放历史按钮被点击
            // 视频剧集分类按钮被点击
            // 显示VideoSiftFragment界面
            VideoHistoryFragment videoHistoryFragment = new VideoHistoryFragment();
            fragmentTransaction.replace(R.id.container, videoHistoryFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }

        //这个触发给主界面的底部按钮选中状态取消掉
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.clearNowFragment();
        }
    }
}
