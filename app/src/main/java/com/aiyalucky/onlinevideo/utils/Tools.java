package com.aiyalucky.onlinevideo.utils;

import android.content.Context;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: xu xiao wei
 * @Date: 2023/7/2 22:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class Tools {

    /**
     * 判断是否为手机号
     *
     * @param phoneNumber
     * @return
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // 定义手机号的正则表达式
        String regex = "^(?:(?:\\+|00)86)?1[3-9]\\d{9}$";

        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);

        // 创建Matcher对象
        Matcher matcher = pattern.matcher(phoneNumber);

        // 判断是否匹配成功
        return matcher.matches();
    }

    /**
     * 获取应用缓存大小
     */
    public static String getCacheSize(Context context) {

        File cacheDir = context.getCacheDir(); // 获取应用的内部缓存目录
        long totalSize = 0;
        File[] cacheFiles = cacheDir.listFiles(); // 获取缓存目录中的所有文件
        for (File file : cacheFiles) {
            totalSize += file.length(); // 累加文件大小
        }

        File externalCacheDir = context.getExternalCacheDir(); // 获取应用的外部缓存目录
        File[] cacheFilesExt = externalCacheDir.listFiles(); // 获取缓存目录中的所有文件
        for (File file1 : cacheFilesExt) {
            totalSize += file1.length(); // 累加文件大小
        }

        return formatSize(totalSize);
    }

    public static String formatSize(long size) {
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double formattedSize = size;

        while (formattedSize > 1024 && unitIndex < units.length - 1) {
            formattedSize /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", formattedSize, units[unitIndex]);
    }

    /**
     * 清理缓存
     */
    public static void clearCache(Context context) {
        File cacheDir = context.getCacheDir();
        deleteRecursive(cacheDir);


        File externalCacheDir = context.getExternalCacheDir(); // 获取应用的外部缓存目录
        deleteRecursive(externalCacheDir);
    }

    public static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] files = fileOrDirectory.listFiles();
            if (files != null) {
                for (File child : files) {
                    deleteRecursive(child);
                }
            }
        }
        fileOrDirectory.delete();
    }
}
