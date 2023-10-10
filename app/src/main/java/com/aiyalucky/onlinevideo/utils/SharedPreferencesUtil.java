package com.aiyalucky.onlinevideo.utils;

/*
 * 存储工具类
 * @Author: xu xiao wei
 * @Date: 2023/7/2 16:36
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    /**
     * SharedPreferences 文件名
     */
    private static final String PREF_NAME = "MyPrefs";

    /**
     * SharedPreferencesUtil 实例
     */
    private static SharedPreferencesUtil instance;

    /**
     * SharedPreferences 实例
     */
    private SharedPreferences sharedPreferences;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    private SharedPreferencesUtil(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 获取 SharedPreferencesUtil 实例
     *
     * @param context 上下文
     * @return SharedPreferencesUtil 实例
     */
    public static SharedPreferencesUtil getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtil(context.getApplicationContext());
        }
        return instance;
    }

    /**
     * 存储字符串类型的数据
     *
     * @param key   键名
     * @param value 字符串值
     */
    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 获取存储的字符串值
     *
     * @param key          键名
     * @param defaultValue 默认值（当键不存在时返回该值）
     * @return 存储的字符串值
     */
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 存储整数类型的数据
     *
     * @param key   键名
     * @param value 整数值
     */
    public void saveInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 获取存储的整数值
     *
     * @param key          键名
     * @param defaultValue 默认值（当键不存在时返回该值）
     * @return 存储的整数值
     */
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 存储布尔类型的数据
     *
     * @param key   键名
     * @param value 布尔值
     */
    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 获取存储的布尔值
     *
     * @param key          键名
     * @param defaultValue 默认值（当键不存在时返回该值）
     * @return 存储的布尔值
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 删除指定键的数据
     *
     * @param key 键名
     */
    public void removeKey(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}


