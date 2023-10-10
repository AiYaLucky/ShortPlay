package com.aiyalucky.onlinevideo.RequestParams;

import com.aiyalucky.onlinevideo.Data.User;

/**
     * 用于和服务器通讯的参数类， 用于传递一个User和一个整数
 *
 * @Author: xu xiao wei
 * @Date: 2023/7/1 22:54
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class UserAndIntRequestParams {
    User param1;
    Integer param2;

    public User getParam1() {
        return param1;
    }

    public void setParam1(User param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }
}
