package com.aiyalucky.shortplay.https.Response;

import java.util.Map;

/**
 * 服务器响应
 * 用于接收服务器返回数据的对象类
 *
 * @Author xu xiao wei
 * @ClassName ServerResponse
 * @Package com.aiyalucky.shortplay.https.Response
 * @Date 2023/2/22 11:02
 * @Version 1.0
 */

public class ServerResponse {

    /**
     * 代码
     */
    private int code;

    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private Map<String, Object> data;

    /**
     * 服务器响应
     * 构造函数
     *
     * @param code    代码
     * @param message 消息
     * @param data    数据
     */
    public ServerResponse(int code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ServerResponse() {
    }

    /**
     * 获取代码
     *
     * @return int
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 得到消息
     *
     * @return {@link String}
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置消息
     *
     * @param message 消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 获取数据
     *
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 集数据
     *
     * @param data 数据
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 是否登录成功
     * @return
     */
    public boolean isSuccessLogin() {
        return this.code >= 200 && this.code < 300;
    }
}
