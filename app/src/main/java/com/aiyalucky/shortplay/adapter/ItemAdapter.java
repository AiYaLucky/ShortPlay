package com.aiyalucky.shortplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.shortplay.R;
import com.aiyalucky.shortplay.pojo.ItemData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/3/1 22:50
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private Context context;
    private List<ItemData> itemList;


    public ItemAdapter(Context context, List<ItemData> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.short_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData item = itemList.get(position);

        Glide.with(context)
                .load(item.getImageRes())
                .into(holder.imageView);
//        holder.imageView.setImageResource(item.getImageRes());
        holder.textView.setText(item.getText());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
