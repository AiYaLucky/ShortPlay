package com.aiyalucky.shortplay.pojo;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/1 22:53
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class MyData {
    private String title;
    private List<ItemData> items;

    public MyData() {
    }

    public MyData(String title, List<ItemData> items) {
        this.title = title;
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemData> getItems() {
        return items;
    }

    public void setItems(List<ItemData> items) {
        this.items = items;
    }
}
