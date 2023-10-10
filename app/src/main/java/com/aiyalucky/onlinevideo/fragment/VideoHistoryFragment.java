package com.aiyalucky.onlinevideo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.adapter.ItemsAdapter;
import com.aiyalucky.onlinevideo.adapter.SuggestionItemsAdapter;
import com.aiyalucky.onlinevideo.adapter.VideoCatchAdapter;
import com.aiyalucky.onlinevideo.adapter.VideoPointItemAdapter;
import com.aiyalucky.onlinevideo.model.VideoModel;

import java.util.ArrayList;
import java.util.List;

public class VideoHistoryFragment extends Fragment {

    private GridView suggestionVideo;

    public VideoHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_video, container, false);

        suggestionVideo = view.findViewById(R.id.rank_grid);
//        suggestionVideo.setAdapter(new SuggestionItemsAdapter(view.getContext(), AllData.HOT_VIDEO_DATA));

        return view;
    }

}