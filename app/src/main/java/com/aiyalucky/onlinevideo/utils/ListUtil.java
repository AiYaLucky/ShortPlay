package com.aiyalucky.onlinevideo.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/6/24 18:58
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class ListUtil {
    public static <T> List<T> deepCopy(List<T> source) {
        if (source == null) {
            return null;
        }

        List<T> copy = new ArrayList<>(source.size());
        for (T item : source) {
            if (item instanceof Cloneable) {
                try {
                    @SuppressWarnings("unchecked")
                    T clonedItem = (T) item.getClass().getMethod("clone").invoke(item);
                    copy.add(clonedItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                copy.add(item);
            }
        }

        return copy;
    }

}
