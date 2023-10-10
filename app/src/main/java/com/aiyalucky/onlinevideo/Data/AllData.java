package com.aiyalucky.onlinevideo.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName AllData
 * @Description
 * @Author xu xiao wei
 * @Date 2023/5/5 18:28
 * @Version 1.0
 */
public class AllData {
    public static TaskData MY_TASK_DATA = new TaskData();
    public static User USER = new User();
    /**
     * 所有视频数据 key为视频videoId，value为视频数据的剧集列表
     */
    public static HashMap<Integer, List<VideoData>> ALL_VIDEO_DATA = new HashMap<>();

    /**
     * 下面这四个list都是只存储一个视频数据，用于展示剧集以及点击的时候提供videoId，方便从ALL_VIDEO_DATA中获取剧集的完整视频和数量的数据
     */
    public static List<VideoData> BANNER_VIDEO_DATA = new ArrayList<>();

    public static List<VideoData> HOT_VIDEO_DATA = new ArrayList<>();

    public static List<VideoData> GOOD_VIDEO_DATA = new ArrayList<>();
    public static List<VideoData> ALL_VIDEO_DATA_LIST = new ArrayList<>();

    public static List<VideoInfo> ALL_VIDEO_INFO_LIST = new ArrayList<>();
    public static boolean RED_BAG_CAN_GET = false;

    /**
     * 所有任务对应名称
     */
    public static HashMap<Integer,String> TASK_NAME = new HashMap<>();
    static {
        TASK_NAME.put(1,"新手任务");
        TASK_NAME.put(2,"每日签到");
        TASK_NAME.put(3,"每日分享");
        TASK_NAME.put(4,"每日观看");
        TASK_NAME.put(5,"每日点赞");
        TASK_NAME.put(6,"每日收藏");
        TASK_NAME.put(7,"每日投币");
        TASK_NAME.put(8,"每日充值");
        TASK_NAME.put(9,"每日追剧");
        TASK_NAME.put(10,"每日登录");
    }

    /**
     * 所有任务对应名称
     */
    public static HashMap<Integer,String> TASK_REWARD_DESC = new HashMap<>();
    static {
        TASK_REWARD_DESC.put(1,"+1看点 +0.2红包 +10经验");
        TASK_REWARD_DESC.put(2,"+2看点 +0.3红包 +12经验");
        TASK_REWARD_DESC.put(3,"+3看点 +0.2红包 +13经验");
        TASK_REWARD_DESC.put(4,"+4看点 +0.4红包 +10经验");
        TASK_REWARD_DESC.put(5,"+5看点 +0.2红包 +14经验");
        TASK_REWARD_DESC.put(6,"+6看点 +0.2红包 +10经验");
        TASK_REWARD_DESC.put(7,"+7看点 +0.3红包 +20经验");
        TASK_REWARD_DESC.put(8,"+8看点 +0.2红包 +10经验");
        TASK_REWARD_DESC.put(9,"+9看点 +0.5红包 +30经验");
        TASK_REWARD_DESC.put(10,"+10看点 +0.2红包 +10经验");
    }

    /**
     * 所有任务对应名称
     */
    public static HashMap<Integer,String> TASK_DESC = new HashMap<>();
    static {
        TASK_DESC.put(1,"1分钟快速上手不走弯路");
        TASK_DESC.put(2,"今日签到可领取2个看点");
        TASK_DESC.put(3,"今日进行分享，获取3个看点");
        TASK_DESC.put(4,"每日观看视频，即可领取4个看点");
        TASK_DESC.put(5,"每日点赞，即可领取5个看点");
        TASK_DESC.put(6,"每日收藏，即可领取6个看点");
        TASK_DESC.put(7,"每日投币，即可领取7个看点");
        TASK_DESC.put(8,"每日充值，即可领取8个看点");
        TASK_DESC.put(9,"每日追剧，即可领取9个看点");
        TASK_DESC.put(10,"每日追剧，即可领取10个看点");
    }

    /**
     * 订单数据
     */
    public static List<Recharge> ORDER_INFO = new ArrayList<>();
    public static List<GetRecharge> ORDER_GET_INFO = new ArrayList<>();
    public static List<VideoPoint> VIDEO_POINT_INFO = new ArrayList<>();
    public static List<VideoPro> VIDEO_CATCH_INFO = new ArrayList<>();
    public static VideoHistory VIDEO_HISTORY_INFO = new VideoHistory();

}
