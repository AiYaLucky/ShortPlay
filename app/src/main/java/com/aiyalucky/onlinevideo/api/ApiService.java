package com.aiyalucky.onlinevideo.api;

import com.aiyalucky.onlinevideo.Data.TaskData;
import com.aiyalucky.onlinevideo.Data.User;
import com.aiyalucky.onlinevideo.Data.VideoCatch;
import com.aiyalucky.onlinevideo.Data.VideoData;
import com.aiyalucky.onlinevideo.Data.VideoHistory;
import com.aiyalucky.onlinevideo.Data.VideoInfo;
import com.aiyalucky.onlinevideo.RequestParams.UserAnd2IntRequestParams;
import com.aiyalucky.onlinevideo.RequestParams.UserAndIntRequestParams;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @ClassName ApiService
 * @Description
 * @Author xu xiao wei
 * @Date 2023/5/5 16:45
 * @Version 1.0
 */
public interface ApiService {
    @POST("/video/getList")
    Call<List<VideoData>> getVideoDataList(@Body Integer param);

    @POST("/video/getVideoInfo")
    Call<List<VideoInfo>> getVideoInfoList(@Body Integer param);

    @POST("/video/getVideoCatch")
    Call<VideoCatch> getVideoCatch(@Body UserAndIntRequestParams param);

    @POST("/video/addVideoCatch")
    Call<VideoCatch> addVideoCatch(@Body UserAndIntRequestParams param);

    @POST("/video/getVideoHistory")
    Call<VideoHistory> getVideoHistory(@Body UserAndIntRequestParams param);

    @POST("/video/addVideoHistory")
    Call<VideoHistory> addVideoHistory(@Body UserAnd2IntRequestParams param);

    @POST("/task/taskList")
    Call<TaskData> getTaskInfoList(@Body UserAndIntRequestParams param1);

    @POST("/user/register")
    Call<User> userRegister(@Body User user);

    @POST("/user/login")
    Call<User> userLogin(@Body User user);
}
