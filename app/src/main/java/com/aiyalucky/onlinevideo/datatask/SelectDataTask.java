package com.aiyalucky.onlinevideo.datatask;

import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.FragmentManager;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.TaskData;
import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.dao.VideoDao;
import com.aiyalucky.onlinevideo.fragment.HomePageFragment;
import com.aiyalucky.onlinevideo.utils.DbUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GetDataTask
 * @Description
 * @Author xu xiao wei
 * @Date 2023/5/6 15:09
 * @Version 1.0
 */
//定义一个异步任务
public class SelectDataTask extends AsyncTask<Void, Void, List<VideoData>> {
    private VideoDao videoDao;

    /**
     * 这个是待更新目标
     */

    private FragmentManager mFragmentManager;


    private OnDataTaskCompleteListener mListener;

    public SelectDataTask(Context context, FragmentManager fragmentManager, OnDataTaskCompleteListener listener) {
        this.videoDao = DbUtil.getSQLLite(context).videoDao();
        this.mFragmentManager = fragmentManager;
        this.mListener = listener;
    }

    @Override
    protected List<VideoData> doInBackground(Void... voids) {
        return videoDao.selectAllData();
    }

    /**
     * 这个方法会在doInBackground()方法执行完毕后被调用，并且doInBackground()的返回结果会作为他的参数传入。
     *
     * @param videoDataList The result of the operation computed by {@link #doInBackground}.
     */
    @Override
    protected void onPostExecute(List<VideoData> videoDataList) {
        List<Integer> alreadyAddBanner = new ArrayList<>();
        List<Integer> alreadyHot = new ArrayList<>();
        List<Integer> alreadyGood = new ArrayList<>();
        List<Integer> alreadyAll = new ArrayList<>();
        for (VideoData videoData : videoDataList) {
            int videoId = videoData.getVideoid();

            //根据id进行整理电视剧
            if (AllData.ALL_VIDEO_DATA.containsKey(videoId)) {
                AllData.ALL_VIDEO_DATA.get(videoId).add(videoData);
            } else {
                List<VideoData> tmpVideoList = new ArrayList<>();
                tmpVideoList.add(videoData);
                AllData.ALL_VIDEO_DATA.put(videoData.getVideoid(), tmpVideoList);
            }

            //banner展示的视频信息
            if (videoData.getBanner() == 1 && !alreadyAddBanner.contains(videoId)) {
                AllData.BANNER_VIDEO_DATA.add(videoData);
                alreadyAddBanner.add(videoId);
                continue;
            }

            //热门视频信息
            if (videoData.getHot() == 1 && !alreadyHot.contains(videoId)) {
                AllData.HOT_VIDEO_DATA.add(videoData);
                alreadyHot.add(videoId);
                continue;
            }

            //好剧视频信息[非banner和热门推荐剩下的都在这里]
            if (!alreadyGood.contains(videoId) && videoData.getBanner() == 0 && videoData.getHot() == 0) {
                AllData.GOOD_VIDEO_DATA.add(videoData);
                alreadyGood.add(videoId);
            }

            //所有视频信息
            if (!alreadyAll.contains(videoId)) {
                AllData.ALL_VIDEO_DATA_LIST.add(videoData);
                alreadyAll.add(videoId);
            }
        }

        // 在主线程中更新UI
        HomePageFragment fragment = (HomePageFragment) mFragmentManager.findFragmentById(R.id.container);
        if (fragment != null) {
            mListener.onDataTaskComplete(AllData.HOT_VIDEO_DATA, AllData.BANNER_VIDEO_DATA, AllData.GOOD_VIDEO_DATA);
        }
    }


    /**
     * 任务完成的回调接口，确保本异步任务执行完毕.
     */
    public interface OnDataTaskCompleteListener {
        void onDataTaskComplete(List<VideoData> hotList, List<VideoData> bannerList, List<VideoData> videoList);
    }
}
