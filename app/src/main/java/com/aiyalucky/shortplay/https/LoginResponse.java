package com.aiyalucky.shortplay.https;

/**
 * @Author: xu xiao wei
 * @Date: 2023/2/21 21:28
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class LoginResponse {
    public boolean success;
    public User user;

    public static class User {
        public int id;
        public String name;
        public String email;
    }
}


