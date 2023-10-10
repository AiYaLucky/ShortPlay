package com.aiyalucky.onlinevideo.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.VideoCatch;
import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.Data.VideoPro;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.RequestParams.UserAndIntRequestParams;
import com.aiyalucky.onlinevideo.adapter.ShowListAdapter;
import com.aiyalucky.onlinevideo.adapter.ViewPagerAdapter;
import com.aiyalucky.onlinevideo.holder.RecyclerItemNormalHolder;
import com.aiyalucky.onlinevideo.utils.CircularProgressBarManager;
import com.aiyalucky.onlinevideo.utils.DensityUtil;
import com.aiyalucky.onlinevideo.utils.RetrofitClient;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/24 9:47
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class VideosPlayerActivity extends AppCompatActivity implements ShowListAdapter.OnVideoClickListener {
    List<VideoData> dataList = new ArrayList<>();
    ViewPagerAdapter viewPagerAdapter;
    private ViewPager2 viewPager;

    private ImageView redBag;
    private ObjectAnimator animator;
    LinearLayout showListLayout;
    /**
     * 点赞视频按钮
     */
    private ImageView likeVideo;

    /**
     * 追剧按钮
     */
    private ImageView catchVideo;

    /**
     * 分享按钮
     */
    private ImageView shareVideo;

    //红包领取状态
    private boolean canGetRedBag = false;


    private int playIndex = 0;

    /**
     * 构造的时候，传入视频列表和需要播放的集数
     *
     * @param videoList
     * @param videoIndex
     */
    public VideosPlayerActivity(List<VideoData> videoList, int videoIndex) {
        dataList = videoList;
        playIndex = videoIndex;
    }

    public VideosPlayerActivity(List<VideoData> videoList) {
        dataList = videoList;
    }

    public VideosPlayerActivity() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        CircularProgressBarManager progressBarManager = CircularProgressBarManager.getInstance();
        viewPager = findViewById(R.id.video_view_pager);

        redBag = findViewById(R.id.video_red_bag_icon);
        likeVideo = findViewById(R.id.like_video);
        catchVideo = findViewById(R.id.catch_video);
        shareVideo = findViewById(R.id.share_video);
        //创建摇晃动画
        animator = ObjectAnimator.ofFloat(redBag, "rotation", 0f, 10f, -10f, 10f, 0f);
        redBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AllData.RED_BAG_CAN_GET) {
                    //点击红包的时候，停止进度条
                    progressBarManager.setMaxProgress(100);
                    progressBarManager.setTotalTime(30000);
                    progressBarManager.startProgress();
                    AllData.RED_BAG_CAN_GET = false;
                    animator.cancel();
                }
            }
        });

        //点赞按钮
        likeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //追剧按钮
        catchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                int videoId = intent.getIntExtra("videoId", 1);
                UserAndIntRequestParams params = new UserAndIntRequestParams();
                params.setParam1(AllData.USER);
                params.setParam2(videoId);
                RetrofitClient.getInstance().addVideoCatch(params, new Callback<VideoCatch>() {
                    @Override
                    public void onResponse(Call<VideoCatch> call, Response<VideoCatch> response) {
                        VideoCatch videoCatch = response.body();
                        if (videoCatch != null) {
                            VideoPro videoPro = new VideoPro();
                            VideoData videoData = AllData.ALL_VIDEO_DATA.get(videoId).get(0);
                            videoPro.setVideoId(videoCatch.getId());
                            videoPro.setNum(videoId);
                            videoPro.setName(videoData.getName());
                            videoPro.setImgurl(videoData.getImgurl());
                            videoPro.setVideourl(videoData.getVideourl());
                            //本地追剧数据添加
                            AllData.VIDEO_CATCH_INFO.add(videoPro);
                            Toast.makeText(VideosPlayerActivity.this, "追剧成功", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<VideoCatch> call, Throwable t) {
                        Toast.makeText(VideosPlayerActivity.this, "追剧异常", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //分享按钮
        shareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //显示圆形倒计时，先不播放，等加载完视频开始播放的时候才继续
        CircularProgressBar circularProgressBar = findViewById(R.id.video_red_bag);
        progressBarManager.setProgressBar(circularProgressBar);
        progressBarManager.setMaxProgress(100);
        progressBarManager.setTotalTime(30000);

        //环形进度条变化数值的时候监听
        progressBarManager.getProgressBar().setOnProgressChangeListener(progress -> {
            System.out.println("progress = " + progress);
            //进度条到达99%就表示满了，这个时候就停止进度条红包摇晃起来
            if (progress >= 100) {
                progressBarManager.stopProgress();
                AllData.RED_BAG_CAN_GET = true;
                sharkRedBag();
            }
            return Unit.INSTANCE;
        });

        // 初始化视频播放器
        resolveData();
        viewPagerAdapter = new ViewPagerAdapter(this, dataList);
        viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                //大于0说明有播放
                int playPosition = GSYVideoManager.instance().getPlayPosition();
                if (playPosition >= 0) {
                    //对应的播放列表TAG
                    if (GSYVideoManager.instance().getPlayTag().equals(RecyclerItemNormalHolder.TAG) && (position != playPosition)) {
                        GSYVideoManager.releaseAllVideos();
                        playPosition(position);
                    }
                }
            }
        });
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                playPosition(0);
            }
        });

        RelativeLayout buttonContainer = findViewById(R.id.button_container);

        GridView listView = findViewById(R.id.list_grid_view);
        showListLayout = findViewById(R.id.show_list_close_click_layout);

        //设置选集界面的数量一行四个
        listView.setNumColumns(4);

        //设置选集的高度为屏幕高度的一半
        int screenHeight = DensityUtil.getScreenHeight(this);
        ViewGroup.LayoutParams listViewLayoutParams = listView.getLayoutParams();
        listViewLayoutParams.height = screenHeight / 3;
        listView.setLayoutParams(listViewLayoutParams);

        TextView show_list_name = findViewById(R.id.show_list_name);

        //当前视频的名字
        String videoName = dataList.get(0).getName();

        //当前视频集数
        Integer videoNum = dataList.get(0).getEpnum();

        //选集按钮显示信息
        String videoInfo = videoName + "  ·  更新至第" + dataList.size() + "集 | 选集";
        show_list_name.setText(videoInfo);

        ImageView arrowIcon = findViewById(R.id.arrow_icon);
        arrowIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_up_24));
        showListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listView.getVisibility() != View.GONE) {
                    //隐藏了列表之后修改点击事件无效
                    showListLayout.setClickable(false);
                    // Hide the list view with animation
                    Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    listView.setVisibility(View.GONE);
                    listView.startAnimation(slideDown);
                    arrowIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_up_24));
                }
            }
        });
        //默认点击事件无效
        showListLayout.setClickable(false);
        buttonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listView.getVisibility() == View.GONE) {
                    //显示列表的时候需要生效点击事件
                    showListLayout.setClickable(true);
                    // Show the list view with animation
                    Animation slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                    listView.setVisibility(View.VISIBLE);
                    listView.startAnimation(slideUp);
                    arrowIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_down_24));
                } else {
                    //隐藏了列表之后修改点击事件无效
                    showListLayout.setClickable(false);
                    // Hide the list view with animation
                    Animation slideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
                    listView.setVisibility(View.GONE);
                    listView.startAnimation(slideDown);
                    arrowIcon.setImageDrawable(getResources().getDrawable(R.drawable.arrow_up_24));
                }
            }
        });

        if (progressBarManager.getIsPaused()) {
            progressBarManager.togglePauseResume();
        }

        //添加数据进行显示
        ShowListAdapter adapter = new ShowListAdapter(this, dataList);
        adapter.setOnVideoClickListener(this);
        listView.setAdapter(adapter);
    }


    /**
     * 摇晃红包
     */
    private void sharkRedBag() {
        animator.setDuration(1000); // 设置动画持续时间
        animator.setRepeatCount(ObjectAnimator.INFINITE); // 设置动画重复次数（无限循环）
        animator.setRepeatMode(ObjectAnimator.REVERSE); // 设置动画重复模式（逆向循环）
        animator.start(); // 启动动画1
    }

    @Override
    public void onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    /**
     * 视频列表数据
     */
    @SuppressLint("NotifyDataSetChanged")
    private void resolveData() {
        //获取点击视频封面过来的类别和id，然后根据数据取出对应的视频
        Intent intent = getIntent();
        int videoId = intent.getIntExtra("videoId", 1);

        dataList = AllData.ALL_VIDEO_DATA.get(videoId);

        if (viewPagerAdapter != null) {
            viewPagerAdapter.notifyDataSetChanged();
        }
    }

    public void playPosition(int position) {
        viewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView.ViewHolder viewHolder = ((RecyclerView) viewPager.getChildAt(0)).findViewHolderForAdapterPosition(position);
                if (viewHolder != null) {
                    RecyclerItemNormalHolder recyclerItemNormalHolder = (RecyclerItemNormalHolder) viewHolder;
                    recyclerItemNormalHolder.getPlayer().startPlayLogic();
                }
            }
        }, 50);
    }

    /**
     * 这里是处理播放界面的上拉列表点击过来直接设置播放哪一集的逻辑
     *
     * @param position
     */
    @Override
    public void onVideoClick(int position) {
//        //这个是播放界面的视频列表选择界面
//        TextView listViewTitle = findViewById(R.id.show_list_title);
//
//        //当前视频的名字
//        String videoName = dataList.get(position).getName();
//
//        //当前视频集数
//        Integer videoNum = dataList.get(position).getEpnum();
//
//        //设置列表的视频名和当前第几集
//        String videoTitleInfo = "正在播放: " + videoName + " 第" + videoNum + "集";
//        listViewTitle.setText(videoTitleInfo);

        viewPager.setCurrentItem(position, false);
    }

    //重写关闭界面的方法
    @Override
    public void finish() {
        super.finish();

        //关闭播放界面需要暂停进度条
        CircularProgressBarManager progressBarManager = CircularProgressBarManager.getInstance();
        progressBarManager.togglePauseResume();
    }
}
