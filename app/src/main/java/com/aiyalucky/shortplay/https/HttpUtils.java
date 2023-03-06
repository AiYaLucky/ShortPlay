package com.aiyalucky.shortplay.https;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/5 22:14
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class HttpUtils {
    private static HttpUtils instance;
    private final Retrofit retrofit;
//    private final static String baseUrl = "http://192.168.2.165:8080/";
    private final static String baseUrl = "http://192.168.3.218:8080/";

    private HttpUtils() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized HttpUtils getInstance() {
        if (instance == null) {
            instance = new HttpUtils();
        }
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
