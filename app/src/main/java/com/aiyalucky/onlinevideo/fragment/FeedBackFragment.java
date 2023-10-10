package com.aiyalucky.onlinevideo.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.aiyalucky.onlinevideo.MainActivity;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.model.VideoModel;

import java.util.ArrayList;
import java.util.List;

public class FeedBackFragment extends Fragment {

    private EditText feedEditText;
    private Button submit_button;
    private Button cancel_button;

    public FeedBackFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_back, container, false);

        feedEditText = view.findViewById(R.id.feedback_description);
        feedEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    feedEditText.setGravity(Gravity.START | Gravity.TOP);
                    feedEditText.setHint("");
                }else{
                    feedEditText.setGravity(Gravity.CENTER);
                    feedEditText.setHint("请输入反馈内容");
                }
            }
        });
        submit_button = view.findViewById(R.id.submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里是点击提交反馈到服务器的逻辑
                Toast.makeText(getContext(), "提交成功", Toast.LENGTH_SHORT).show();
                //提交成功之后，跳转回原来的我的界面
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MineFragment videoHistoryFragment = new MineFragment();
                fragmentTransaction.replace(R.id.container, videoHistoryFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.setNowFragment(3);
                }
            }
        });
        cancel_button = view.findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里是点击取消按钮，返回我的界面的逻辑
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MineFragment videoHistoryFragment = new MineFragment();
                fragmentTransaction.replace(R.id.container, videoHistoryFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.setNowFragment(3);
                }
            }
        });
        return view;
    }
}