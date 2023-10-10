package com.aiyalucky.onlinevideo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.multidex.MultiDex;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.TaskData;
import com.aiyalucky.onlinevideo.Data.VideoCatch;
import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.Data.VideoHistory;
import com.aiyalucky.onlinevideo.Data.VideoInfo;
import com.aiyalucky.onlinevideo.Data.VideoPro;
import com.aiyalucky.onlinevideo.RequestParams.UserAndIntRequestParams;
import com.aiyalucky.onlinevideo.datatask.InsertTaskDataTask;
import com.aiyalucky.onlinevideo.datatask.InsertVideoDataTask;
import com.aiyalucky.onlinevideo.datatask.InsertVideoInfoTask;
import com.aiyalucky.onlinevideo.fragment.HomePageFragment;
import com.aiyalucky.onlinevideo.fragment.HotVideosFragment;
import com.aiyalucky.onlinevideo.fragment.MineFragment;
import com.aiyalucky.onlinevideo.fragment.TaskCenterFragment;
import com.aiyalucky.onlinevideo.fragment.VideoCatchFragment;
import com.aiyalucky.onlinevideo.fragment.VideoHistoryFragment;
import com.aiyalucky.onlinevideo.fragment.VideoSiftFragment;
import com.aiyalucky.onlinevideo.utils.RetrofitClient;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.shuyu.gsyvideoplayer.cache.CacheFactory;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tv.danmaku.ijk.media.exo2.Exo2PlayerManager;
import tv.danmaku.ijk.media.exo2.ExoPlayerCacheManager;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mContainer;
    private LinearLayout mMenuLayoutTop;
    private LinearLayout mMenuLayoutBottom;

    //主界面的上下六个菜单
    private TextView homePage;
    private TextView randomLook;
    private TextView taskCenter;
    private TextView mine;
    private TextView hotVideos;
    private TextView suggestion;
    private TextView news;
    //当前页面展示的fragment是哪个
    private int nowFragment;

    private int lastLineId;
    private int lastTextId;
    public static final int HOME_PAGE = 0;
    private static Context context;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
        MainActivity.context = this;
    }
    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //设置初始默认显示的界面为主页
        setInitFragment();
        //初始化
        init();

        //设置点击事件监听
        setOnClickListeners();

        //播放器设置
        PlayerFactory.setPlayManager(Exo2PlayerManager.class); //exoplayer内核
        CacheFactory.setCacheManager(ExoPlayerCacheManager.class); //exo缓存模式

    }

    /**
     * 用于设置当前展示的fragment是哪个
     */
    public void clearNowFragment() {
        nowFragment = 0;
        showMenuLine(6);
    }

    /**
     * 设置当前展示的fragment是哪个
     *
     * @param num
     */
    public void setNowFragment(int num) {
        nowFragment = 0;
        showMenuLine(num);
    }

    private void setInitFragment() {
        // 根据选项不同，将对应的Fragment替换到容器中
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomePageFragment mainFragment = new HomePageFragment();

        //将HomePageFragment以tag标签homePageFragment添加到fragmentManager方便后续使用。
        fragmentTransaction.add(R.id.container, mainFragment, "homePageFragment");

        //默认生成主页
        fragmentTransaction.replace(R.id.container, mainFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        //设置初始的fragment展示的id
        nowFragment = R.id.menu_bottom_homePage;
    }

    /**
     * 初始化
     */
    private void init() {
        initHttpData();
        // 初始化顶部菜单栏
        mMenuLayoutTop = findViewById(R.id.menu_top);

        // 初始化底部菜单栏
        mMenuLayoutBottom = findViewById(R.id.menu_bottom);

        // 初始化Fragment容器
        mContainer = findViewById(R.id.container);

        //初始化各个菜单按钮
        homePage = findViewById(R.id.menu_bottom_homePage);
        randomLook = findViewById(R.id.menu_bottom_random);
        taskCenter = findViewById(R.id.menu_bottom_task_center);
        mine = findViewById(R.id.menu_bottom_mine);
        hotVideos = findViewById(R.id.menu_top_video);
        suggestion = findViewById(R.id.menu_top_suggestion);
        news = findViewById(R.id.menu_top_news);

        //初始化选中下划线为广场
        showMenuLine(1);

        //不要顶部菜单栏 赢藏
        mMenuLayoutTop.setVisibility(View.GONE);
    }

    private void initHttpData(){

        //默认设置用户的信息和请求的数据条目为初始值
        UserAndIntRequestParams userAndIntRequestParams = new UserAndIntRequestParams();
        userAndIntRequestParams.setParam1(AllData.USER);
        userAndIntRequestParams.setParam2(99999);

        //通过retrofit工具向服务器请求所有视频数据
        RetrofitClient.getInstance().getVideoDataList(0, new Callback<List<VideoData>>() {
            /**
             * retrofit请求成功逻辑
             *
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<List<VideoData>> call, Response<List<VideoData>> response) {
                if (response.isSuccessful()) {
                    List<VideoData> data = response.body();
                    //获取HomePageFragment引用
                    HomePageFragment homePageFragment = (HomePageFragment) getSupportFragmentManager().findFragmentByTag("homePageFragment");

                    //将获取的视频数据保存到数据库中,使用AsyncTask的异步任务
                    InsertVideoDataTask insertVideoDataTask = new InsertVideoDataTask(getApplicationContext(), data, new InsertVideoDataTask.OnInsertTaskCompleteListener() {
                        @Override
                        public void onInsertTaskComplete() {
                            homePageFragment.onDataTaskComplete();
                        }
                    });
                    insertVideoDataTask.execute();
                } else {
                    Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                    Toast.makeText(getApplicationContext(), "数据获取异常，请联系客服反馈", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * retrofit请求失败逻辑
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<List<VideoData>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "通讯异常：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //通过retrofit工具向服务器请求所有视频信息数据
        RetrofitClient.getInstance().getVideoInfoList(0, new Callback<List<VideoInfo>>() {
            @Override
            public void onResponse(Call<List<VideoInfo>> call, Response<List<VideoInfo>> response) {
                if (response.isSuccessful()) {
                    List<VideoInfo> data = response.body();

                    //将获取的视频数据保存到数据库中,使用AsyncTask的异步任务
                    InsertVideoInfoTask InsertVideoDataTask = new InsertVideoInfoTask(getApplicationContext(), data, new InsertVideoInfoTask.OnInsertTaskCompleteListener() {
                        @Override
                        public void onInsertTaskComplete() {
                            //数据加载完成之后，同步给全局数据
                            AllData.ALL_VIDEO_INFO_LIST = data;
                        }
                    });
                    InsertVideoDataTask.execute();
                } else {
                    Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                    Toast.makeText(getApplicationContext(), "数据获取异常，请联系客服反馈", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * retrofit请求失败逻辑
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<List<VideoInfo>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "通讯异常：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //通过retrofit工具向服务器请求所有任务信息数据
        RetrofitClient.getInstance().getTaskInfoList(userAndIntRequestParams, new Callback<TaskData>() {
            @Override
            public void onResponse(Call<TaskData> call, Response<TaskData> response) {
                if (response.isSuccessful()) {
                    TaskData data = response.body();

                    //将获取的视频数据保存到数据库中,使用AsyncTask的异步任务
                    InsertTaskDataTask InsertVideoDataTask = new InsertTaskDataTask(getApplicationContext(), data, new InsertTaskDataTask.OnInsertTaskCompleteListener() {
                        @Override
                        public void onInsertTaskComplete() {
                            //数据加载完成之后，同步给全局数据
                            AllData.MY_TASK_DATA = data;
                        }
                    });
                    InsertVideoDataTask.execute();
                } else {
                    Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                    Toast.makeText(getApplicationContext(), "数据获取异常，请联系客服反馈", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * retrofit请求失败逻辑
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<TaskData> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "通讯异常：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //通过retrofit工具向服务器请求玩家的追剧信息
        RetrofitClient.getInstance().getVideoCatch(userAndIntRequestParams, new Callback<VideoCatch>() {
            @Override
            public void onResponse(Call<VideoCatch> call, Response<VideoCatch> response) {
                if (response.isSuccessful()) {
                    VideoCatch data = response.body();
                    //数据加载完成之后，同步给全局数据

                    if (data != null) {
                        Type type = new TypeToken<HashMap<Integer, Integer>>() {
                        }.getType();
                        HashMap<Integer, Integer> videoMap = new Gson().fromJson(data.getCatchInfo(), type);
                        for (Integer videoId : videoMap.keySet()) {
                            VideoData videoData = AllData.ALL_VIDEO_DATA.get(videoId).get(0);
                            VideoPro tmpVideoPro = new VideoPro();
                            tmpVideoPro.setVideoId(videoId);
                            tmpVideoPro.setNum(videoMap.get(videoId));
                            tmpVideoPro.setName(videoData.getName());
                            tmpVideoPro.setImgurl(videoData.getImgurl());
                            tmpVideoPro.setVideourl(videoData.getVideourl());
                            AllData.VIDEO_CATCH_INFO.add(tmpVideoPro);
                        }
                    }

                } else {
                    Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                    Toast.makeText(getApplicationContext(), "数据获取异常，请联系客服反馈", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * retrofit请求失败逻辑
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<VideoCatch> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "通讯异常：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //通过retrofit工具向服务器请求玩家的观看历史信息
        RetrofitClient.getInstance().getVideoHistory(userAndIntRequestParams, new Callback<VideoHistory>() {
            @Override
            public void onResponse(Call<VideoHistory> call, Response<VideoHistory> response) {
                if (response.isSuccessful()) {
                    VideoHistory data = response.body();
                    //数据加载完成之后，同步给全局数据
                    AllData.VIDEO_HISTORY_INFO = data;
                } else {
                    Log.e(TAG, "onResponse: " + response.code() + " " + response.message());
                    Toast.makeText(getApplicationContext(), "数据获取异常，请联系客服反馈", Toast.LENGTH_SHORT).show();
                }
            }

            /**
             * retrofit请求失败逻辑
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<VideoHistory> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "通讯异常：" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 设置主页上的所有点击事件
     */
    private void setOnClickListeners() {
        //顶部热门菜单点击监听
        hotVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的标签id
                int id = v.getId();

                //如果点击的是当前展示的页面，不做处理
                if (nowFragment == id) {
                    return;
                }

                // 根据选项不同，将对应的Fragment替换到容器中
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment mainFragment = new HotVideosFragment();
                fragmentTransaction.replace(R.id.container, mainFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                nowFragment = id;
                mMenuLayoutTop.setActivated(true);

                //添加下划线判断
                showMenuLine(5);
            }
        });

        //顶部推荐菜单点击监听
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的标签id
                int id = v.getId();

                //如果点击的是当前展示的页面，不做处理
                if (nowFragment == id) {
                    return;
                }

                // 根据选项不同，将对应的Fragment替换到容器中
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment mainFragment = new VideoCatchFragment();
                fragmentTransaction.replace(R.id.container, mainFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                nowFragment = id;
                mMenuLayoutTop.setActivated(true);

                //添加下划线判断
                showMenuLine(6);
            }
        });

        //顶部资讯菜单点击监听
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的标签id
                int id = v.getId();

                //如果点击的是当前展示的页面，不做处理
                if (nowFragment == id) {
                    return;
                }

                // 根据选项不同，将对应的Fragment替换到容器中
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment mainFragment = new VideoHistoryFragment();
                fragmentTransaction.replace(R.id.container, mainFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                nowFragment = id;
                mMenuLayoutTop.setActivated(true);

                //添加下划线判断
                showMenuLine(7);
            }
        });

        //底部广场菜单点击监听
        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的标签id
                int id = v.getId();

                //如果点击的是当前展示的页面，不做处理
                if (nowFragment == id) {
                    return;
                }

                // 根据选项不同，将对应的Fragment替换到容器中
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment mainFragment = new HomePageFragment();
                fragmentTransaction.replace(R.id.container, mainFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                nowFragment = id;
                mMenuLayoutTop.setActivated(true);

                //添加下划线判断
                showMenuLine(1);
            }
        });


        //底部随心看菜单点击监听
        randomLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的标签id
                int id = v.getId();

                //如果点击的是当前展示的页面，不做处理
                if (nowFragment == id) {
                    return;
                }

                // 根据选项不同，将对应的Fragment替换到容器中
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment videoSiftFragment = new VideoSiftFragment();
                fragmentTransaction.replace(R.id.container, videoSiftFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                nowFragment = id;
                mMenuLayoutTop.setActivated(true);

                //添加下划线判断
                showMenuLine(2);
            }
        });

        //底部任务中心菜单点击监听
        taskCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的标签id
                int id = v.getId();

                //如果点击的是当前展示的页面，不做处理
                if (nowFragment == id) {
                    return;
                }

                // 根据选项不同，将对应的Fragment替换到容器中
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment taskCenterFragment = new TaskCenterFragment();
                fragmentTransaction.replace(R.id.container, taskCenterFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                nowFragment = id;
                mMenuLayoutTop.setActivated(true);

                //添加下划线判断
                showMenuLine(3);
            }
        });

        //底部我的菜单点击监听
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击的标签id
                int id = v.getId();

                //如果点击的是当前展示的页面，不做处理
                if (nowFragment == id) {
                    return;
                }

                // 根据选项不同，将对应的Fragment替换到容器中
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment mainFragment = new MineFragment();
                fragmentTransaction.replace(R.id.container, mainFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                nowFragment = id;
                mMenuLayoutTop.setActivated(false);

                //添加下划线判断
                showMenuLine(4);
            }
        });
    }

    /**
     * 切换按钮文本下划线显示
     */
    public void showMenuLine(int lineNumber) {
        //默认按钮名称是主页的
        int lineId = R.id.menu_bottom_homePage_line;
        int textId = R.id.menu_bottom_homePage;
        //确认具体的按钮id
        switch (lineNumber) {
            case 1:
                break;
            case 2:
                lineId = R.id.menu_bottom_random_line;
                textId = R.id.menu_bottom_random;
                break;
            case 3:
                lineId = R.id.menu_bottom_friends_line;
                textId = R.id.menu_bottom_task_center;
                break;
            case 4:
                lineId = R.id.menu_bottom_mine_line;
                textId = R.id.menu_bottom_mine;
                break;
            case 5:
                lineId = R.id.menu_top_video_line;
                textId = R.id.menu_top_video;
                break;
            case 6:
                lineId = R.id.menu_top_suggestion_line;
                textId = R.id.menu_top_suggestion;
                break;
            case 7:
                lineId = R.id.menu_top_news_line;
                textId = R.id.menu_top_news;
                break;
        }

        //如果点击的按钮是上一次的按钮，不做任何处理
        if (lastLineId == lineId) {
            return;
        }

        //设置上一次的按钮下划线赢藏
        if (lastLineId != 0) {
            View view = findViewById(lastLineId);
            view.setVisibility(View.INVISIBLE);
        }

        //设置上一次的文本颜色为黑色
        if (lastTextId != 0) {
            TextView textView = findViewById(lastTextId);
            textView.setTextColor(Color.BLACK);
        }
        //更新最后一次点击的按钮为当前的按钮id
        lastLineId = lineId;

        //更新文本id
        lastTextId = textId;

        //显示新按钮
        View newView = findViewById(lineId);
        newView.setVisibility(View.VISIBLE);

        //设置新文本颜色
        TextView textViewNew = findViewById(textId);
        textViewNew.setTextColor(Color.RED);
    }

}