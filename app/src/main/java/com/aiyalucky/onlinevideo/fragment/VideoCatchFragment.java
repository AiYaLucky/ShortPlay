package com.aiyalucky.onlinevideo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.adapter.VideoCatchAdapter;

public class VideoCatchFragment extends Fragment {

    private GridView videoCatchGridView;

    public VideoCatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_catch, container, false);

        videoCatchGridView = view.findViewById(R.id.rank_grid);
        if (AllData.VIDEO_CATCH_INFO != null) {

            videoCatchGridView.setAdapter(new VideoCatchAdapter(view.getContext(), AllData.VIDEO_CATCH_INFO));
        }
        return view;
    }
}
