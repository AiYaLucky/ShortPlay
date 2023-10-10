package com.aiyalucky.onlinevideo.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.Data.VideoInfo;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.adapter.ItemsAdapter;
import com.aiyalucky.onlinevideo.utils.ListUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * 剧集分类页面
 *
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class VideoSiftFragment extends Fragment implements View.OnClickListener {

    /**
     * 分类视频
     */
    private GridView mCategoryGrid;
    /**
     * 综合排序
     */
    private TextView tv_comprehensive;
    private TextView tv_most_followed;
    private TextView tv_most_played;
    private TextView tv_most_liked;
    private TextView tv_recent_updates;

    /**
     * 全部风格
     */
    private TextView tv_all_styles;
    private TextView tv_campus_story;
    private TextView tv_underdog_revival;
    private TextView tv_crossing_story;
    private TextView tv_jianghu_anecdote;
    private TextView tv_comedy;
    private TextView tv_fantasy_epic;


    /**
     * 完结状态
     */
    private TextView tv_completion_status;
    private TextView tv_finished;
    private TextView tv_ongoing;

    /**
     * 付费类型
     */
    private TextView tv_payment_type;
    private TextView tv_free;
    private TextView tv_limited_free;
    private TextView tv_paid;

    /**
     * 最后一次选中点击的对象
     */
    private TextView lastSelectTextViewType_1;
    private TextView lastSelectTextViewType_2;
    private TextView lastSelectTextViewType_3;
    private TextView lastSelectTextViewType_4;


    /**
     * 综合排序分组
     */
    private final List<Integer> typeComprehensive = new ArrayList<>();

    /**
     * 风格分组
     */
    private final List<Integer> typeAllStyles = new ArrayList<>();

    /**
     * 完结状态分组
     */
    private final List<Integer> typeCompletionStatus = new ArrayList<>();

    /**
     * 付费类型分组
     */
    private final List<Integer> typePaymentType = new ArrayList<>();


    /**
     * 选中的标签 HashMap<四个大类,每个大类的选项>
     * 1类： 1综合排序 2最多追剧 3最多播放 4最多点赞 5最近更新
     * 2类： 1全部风格 2校园故事 3屌丝逆袭 4穿越故事 5江湖轶事 6搞笑喜剧 7玄幻史诗
     * 3类： 1完结状态 2 已完结 4连载中
     * 4类： 1付费类型 2免费 3限免 4付费
     */
    private HashMap<Integer, Integer> selectTypeMap = new HashMap<>();

    /**
     * 存放对应文本控件id的对应类型 map的key是文本控件id，value是对应的类型
     */
    private HashMap<Integer, Integer> textviewTypeMap = new HashMap<>();

    public VideoSiftFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_sift, container, false);


        //初始默定布局控件
        initFindView(view);

        //初始默认选择的标签
        initSelectTag();

        //初始化每个标签的点击事件
        initClickTag();

        if (AllData.ALL_VIDEO_DATA_LIST.size() > 0) {
            mCategoryGrid.setAdapter(new ItemsAdapter(getContext(), AllData.ALL_VIDEO_DATA_LIST));
        }

        return view;
    }

    private void initClickTag() {
        tv_comprehensive.setOnClickListener(this);
        tv_most_followed.setOnClickListener(this);
        tv_most_played.setOnClickListener(this);
        tv_most_liked.setOnClickListener(this);
        tv_recent_updates.setOnClickListener(this);

        tv_all_styles.setOnClickListener(this);
        tv_campus_story.setOnClickListener(this);
        tv_underdog_revival.setOnClickListener(this);
        tv_crossing_story.setOnClickListener(this);
        tv_jianghu_anecdote.setOnClickListener(this);
        tv_comedy.setOnClickListener(this);
        tv_fantasy_epic.setOnClickListener(this);

        tv_completion_status.setOnClickListener(this);
        tv_finished.setOnClickListener(this);
        tv_ongoing.setOnClickListener(this);

        tv_payment_type.setOnClickListener(this);
        tv_free.setOnClickListener(this);
        tv_limited_free.setOnClickListener(this);
        tv_paid.setOnClickListener(this);
    }

    private void initFindView(View view) {
        //设置分类列表数据
        mCategoryGrid = view.findViewById(R.id.video_sift_grid);

        tv_comprehensive = view.findViewById(R.id.tv_comprehensive);
        tv_most_followed = view.findViewById(R.id.tv_most_followed);
        tv_most_played = view.findViewById(R.id.tv_most_played);
        tv_most_liked = view.findViewById(R.id.tv_most_liked);
        tv_recent_updates = view.findViewById(R.id.tv_recent_updates);
        //综合排序分组id存储好后续使用
        typeComprehensive.add(R.id.tv_comprehensive);
        typeComprehensive.add(R.id.tv_most_followed);
        typeComprehensive.add(R.id.tv_most_played);
        typeComprehensive.add(R.id.tv_most_liked);
        typeComprehensive.add(R.id.tv_recent_updates);
        //设置一下每个文本控件对应的id
        textviewTypeMap.put(R.id.tv_comprehensive, 1);
        textviewTypeMap.put(R.id.tv_most_followed, 2);
        textviewTypeMap.put(R.id.tv_most_played, 3);
        textviewTypeMap.put(R.id.tv_most_liked, 4);
        textviewTypeMap.put(R.id.tv_recent_updates, 5);


        tv_all_styles = view.findViewById(R.id.tv_all_styles);
        tv_campus_story = view.findViewById(R.id.tv_campus_story);
        tv_underdog_revival = view.findViewById(R.id.tv_underdog_revival);
        tv_crossing_story = view.findViewById(R.id.tv_crossing_story);
        tv_jianghu_anecdote = view.findViewById(R.id.tv_jianghu_anecdote);
        tv_comedy = view.findViewById(R.id.tv_comedy);
        tv_fantasy_epic = view.findViewById(R.id.tv_fantasy_epic);
        //风格分组id存储好后续使用
        typeAllStyles.add(R.id.tv_all_styles);
        typeAllStyles.add(R.id.tv_campus_story);
        typeAllStyles.add(R.id.tv_underdog_revival);
        typeAllStyles.add(R.id.tv_crossing_story);
        typeAllStyles.add(R.id.tv_jianghu_anecdote);
        typeAllStyles.add(R.id.tv_comedy);
        typeAllStyles.add(R.id.tv_fantasy_epic);
        //设置一下每个文本控件对应的id
        textviewTypeMap.put(R.id.tv_all_styles, 1);
        textviewTypeMap.put(R.id.tv_campus_story, 2);
        textviewTypeMap.put(R.id.tv_underdog_revival, 3);
        textviewTypeMap.put(R.id.tv_crossing_story, 4);
        textviewTypeMap.put(R.id.tv_jianghu_anecdote, 5);
        textviewTypeMap.put(R.id.tv_comedy, 6);
        textviewTypeMap.put(R.id.tv_fantasy_epic, 7);

        tv_completion_status = view.findViewById(R.id.tv_completion_status);
        tv_finished = view.findViewById(R.id.tv_finished);
        tv_ongoing = view.findViewById(R.id.tv_ongoing);
        //完结状态分组id存储好后续使用
        typeCompletionStatus.add(R.id.tv_completion_status);
        typeCompletionStatus.add(R.id.tv_finished);
        typeCompletionStatus.add(R.id.tv_ongoing);
        //设置一下每个文本控件对应的id
        textviewTypeMap.put(R.id.tv_completion_status, 1);
        textviewTypeMap.put(R.id.tv_finished, 2);
        textviewTypeMap.put(R.id.tv_ongoing, 3);

        tv_payment_type = view.findViewById(R.id.tv_payment_type);
        tv_free = view.findViewById(R.id.tv_free);
        tv_limited_free = view.findViewById(R.id.tv_limited_free);
        tv_paid = view.findViewById(R.id.tv_paid);
        //付费类型分组id存储好后续使用
        typePaymentType.add(R.id.tv_payment_type);
        typePaymentType.add(R.id.tv_free);
        typePaymentType.add(R.id.tv_limited_free);
        typePaymentType.add(R.id.tv_paid);
        //设置一下每个文本控件对应的id
        textviewTypeMap.put(R.id.tv_payment_type, 1);
        textviewTypeMap.put(R.id.tv_free, 2);
        textviewTypeMap.put(R.id.tv_limited_free, 3);
        textviewTypeMap.put(R.id.tv_paid, 4);
    }

    /**
     * 默认选中的标签
     */
    private void initSelectTag() {
        //初始默认选中四个类型的第一个
        selectTag(tv_comprehensive, null);
        lastSelectTextViewType_1 = tv_comprehensive;
        selectTag(tv_all_styles, null);
        lastSelectTextViewType_2 = tv_all_styles;
        selectTag(tv_completion_status, null);
        lastSelectTextViewType_3 = tv_completion_status;
        selectTag(tv_payment_type, null);
        lastSelectTextViewType_4 = tv_payment_type;
        selectTypeMap.put(1, 1);
        selectTypeMap.put(2, 1);
        selectTypeMap.put(3, 1);
        selectTypeMap.put(4, 1);
    }

    /**
     * 选中的时候，改变背景颜色
     *
     * @param tagTextView  选中的目标文本
     * @param cancelSelect 取消选择恢复默认的文本对象，即最后一次点击选中的文本对象
     */
    private void selectTag(TextView tagTextView, TextView cancelSelect) {
        tagTextView.setBackgroundColor(getResources().getColor(R.color.video_sift_color));
        tagTextView.setTextColor(getResources().getColor(R.color.white));
        tagTextView.setBackground(getResources().getDrawable(R.drawable.video_sift_select_bg_rounded));
        if (cancelSelect != null) {
            cancelSelect.setBackgroundColor(getResources().getColor(R.color.video_sift_color_default));
            cancelSelect.setTextColor(getResources().getColor(R.color.black));
        }
    }

    /**
     * 按钮的选择事件
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int selectId = v.getId();
        TextView textView = (TextView) v;

        if (typeComprehensive.contains(selectId)) {
            //分类1
            if (selectId == lastSelectTextViewType_1.getId()) {
                return;
            }
            //选择新的，取消旧的
            selectTag(textView, lastSelectTextViewType_1);
            //更新一下最后一次选中的文本对象
            lastSelectTextViewType_1 = textView;

            //更新类别的选择目标
            selectTypeMap.put(1, textviewTypeMap.get(selectId));

            //更新数据
            updateVideoList();
        } else if (typeAllStyles.contains(selectId)) {
            //分类2
            if (selectId == lastSelectTextViewType_2.getId()) {
                return;
            }
            //选择新的，取消旧的
            selectTag(textView, lastSelectTextViewType_2);
            //更新一下最后一次选中的文本对象
            lastSelectTextViewType_2 = textView;

            //更新类别的选择目标
            selectTypeMap.put(2, textviewTypeMap.get(selectId));

            //更新数据
            updateVideoList();
        } else if (typeCompletionStatus.contains(selectId)) {
            //分类3
            if (selectId == lastSelectTextViewType_3.getId()) {
                return;
            }
            //选择新的，取消旧的
            selectTag(textView, lastSelectTextViewType_3);
            //更新一下最后一次选中的文本对象
            lastSelectTextViewType_3 = textView;

            //更新类别的选择目标
            selectTypeMap.put(3, textviewTypeMap.get(selectId));

            //更新数据
            updateVideoList();
        } else if (typePaymentType.contains(selectId)) {
            //分类4
            if (selectId == lastSelectTextViewType_4.getId()) {
                return;
            }
            //选择新的，取消旧的
            selectTag(textView, lastSelectTextViewType_4);
            //更新一下最后一次选中的文本对象
            lastSelectTextViewType_4 = textView;

            //更新类别的选择目标
            selectTypeMap.put(4, textviewTypeMap.get(selectId));

            //更新数据
            updateVideoList();
        }
    }

    /**
     * 根据设置的全责类型，具体优化显示的视频内容
     */
    private void updateVideoList() {
        //综合排序
        int type_comprehensive = selectTypeMap.get(1);
        //风格
        int type_style = selectTypeMap.get(2);
        //完结状态
        int type_status = selectTypeMap.get(3);
        //付费类型
        int type_pay = selectTypeMap.get(4);

        List<VideoData> updateVideoList = new ArrayList<>();
        List<VideoInfo> videoList = ListUtil.deepCopy(AllData.ALL_VIDEO_INFO_LIST);

        Log.e("xxxxx", "updateVideoList: " + videoList.size());
        Log.e("xxxxx", "type_comprehensive: " + type_comprehensive);
        Log.e("xxxxx", "type_style: " + type_style);
        Log.e("xxxxx", "type_status: " + type_status);
        Log.e("xxxxx", "type_pay: " + type_pay);
        //根据所选的选项进行数据整理
        switch (type_comprehensive) {
            case 1:
                //综合排序
                break;
            case 2:
                //最多追剧
                //根据收藏数量进行降序排序
                videoList.sort(new Comparator<VideoInfo>() {
                    @Override
                    public int compare(VideoInfo o1, VideoInfo o2) {
                        return o2.getCatchnum().compareTo(o1.getCatchnum());
                    }
                });
                break;
            case 3:
                //最多播放
                //根据播放数量进行降序排序
                videoList.sort(new Comparator<VideoInfo>() {
                    @Override
                    public int compare(VideoInfo o1, VideoInfo o2) {
                        return o2.getPlaynum().compareTo(o1.getPlaynum());
                    }
                });
                break;
            case 4:
                //最多点赞
                //根据点赞数量进行降序排序
                videoList.sort(new Comparator<VideoInfo>() {
                    @Override
                    public int compare(VideoInfo o1, VideoInfo o2) {
                        return o2.getLikenum().compareTo(o1.getLikenum());
                    }
                });
                break;
            case 5:
                //最新更新 根据更新时间排序 降序
                videoList.sort(new Comparator<VideoInfo>() {
                    @Override
                    public int compare(VideoInfo o1, VideoInfo o2) {
                        return o2.getUpdatetime().compareTo(o1.getUpdatetime());
                    }
                });
                break;
        }

        //筛选风格
        if (type_style > 1) {
            Iterator<VideoInfo> iterator = videoList.iterator();
            while (iterator.hasNext()) {
                VideoInfo videoInfo = iterator.next();
                String[] styleArray = videoInfo.getVideostyle().split(",");
                List<Integer> styleList = new ArrayList<>();

                for (String style : styleArray) {
                    try {
                        int intValue = Integer.parseInt(style);
                        styleList.add(intValue);
                    } catch (NumberFormatException e) {
                        // 处理解析整数失败的情况
                        e.printStackTrace();
                    }
                }

                //排除类型
                if (!styleList.contains(type_style)) {
                    iterator.remove();
                }
            }
        }

        //筛选完结状态
        if (type_status > 1) {
            Iterator<VideoInfo> iterator = videoList.iterator();
            while (iterator.hasNext()) {
                VideoInfo videoInfo = iterator.next();
                if (videoInfo.getFinish() != type_status) {
                    iterator.remove();
                }
            }
        }

        //筛选付费类型
        if (type_pay > 1) {
            Iterator<VideoInfo> iterator = videoList.iterator();
            while (iterator.hasNext()) {
                VideoInfo videoInfo = iterator.next();
                if (videoInfo.getPaytype() != type_pay) {
                    iterator.remove();
                }
            }
        }

        for (VideoInfo videoInfo : videoList) {
            Integer videoid = videoInfo.getVideoid();
            List<VideoData> videoData = AllData.ALL_VIDEO_DATA.get(videoid);
            updateVideoList.add(videoData.get(0));
        }

        mCategoryGrid.setAdapter(new ItemsAdapter(getContext(), updateVideoList));
    }
}
