package com.aiyalucky.onlinevideo.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.aiyalucky.onlinevideo.R;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class UserGuideActivity extends AppCompatActivity {

    public UserGuideActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_new_user_guide);
        CardView lookFreeVideo = findViewById(R.id.guide_look_free_video);
        lookFreeVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView levelUseful = findViewById(R.id.guide_level_useful);
        levelUseful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        CardView otherTutorial = findViewById(R.id.guide_other_tutorial);
        otherTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
