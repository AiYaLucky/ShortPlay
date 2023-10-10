package com.aiyalucky.onlinevideo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.User;
import com.aiyalucky.onlinevideo.MainActivity;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.activity.SettingsActivity;
import com.aiyalucky.onlinevideo.activity.UserRecordActivity;
import com.aiyalucky.onlinevideo.utils.RetrofitClient;
import com.aiyalucky.onlinevideo.utils.SharedPreferencesUtil;
import com.aiyalucky.onlinevideo.utils.Tools;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class MineFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ImageView mHeadBg;
    private ImageView mUserHead;
    private TextView mUserName;
    private TextView mUserDesc;
    private TextView mUserFlowers;
    private TextView mUserFocus;
    private TextView mUserPlay;

    private LinearLayout rechargeRecordLayout;
    private LinearLayout getRechargeRecordLayout;
    private LinearLayout videoPointRecordLayout;

    private LinearLayout catchVideoLayout;
    private LinearLayout videoHistoryLayout;
    private LinearLayout feedBackLayout;
    private RelativeLayout loginLayout;

    private Button buttonLogin;
    private Button buttonCancel;
    EditText editTextAccount;
    EditText editTextPassword;
    private TextView loginResultText;
    private ImageView settings;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        // 初始化
//        mRecyclerView = view.findViewById(R.id.my_video_list);
        mHeadBg = view.findViewById(R.id.head_bg);
        mUserHead = view.findViewById(R.id.user_head);
        mUserName = view.findViewById(R.id.user_name);
        mUserDesc = view.findViewById(R.id.user_description);
        mUserFlowers = view.findViewById(R.id.flowers);
        mUserFocus = view.findViewById(R.id.focus);
        mUserPlay = view.findViewById(R.id.play);
        rechargeRecordLayout = view.findViewById(R.id.recharge_record_layout);
        getRechargeRecordLayout = view.findViewById(R.id.get_recharge_record_layout);
        videoPointRecordLayout = view.findViewById(R.id.video_point_record_layout);
        catchVideoLayout = view.findViewById(R.id.catch_video_layout);
        videoHistoryLayout = view.findViewById(R.id.video_history_layout);
        feedBackLayout = view.findViewById(R.id.feed_back_layout);
        loginLayout = view.findViewById(R.id.login_layout);
        buttonLogin = view.findViewById(R.id.buttonLogin);
        buttonCancel = view.findViewById(R.id.buttonCancel);
        editTextAccount = view.findViewById(R.id.editTextAccount);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        loginResultText = view.findViewById(R.id.login_result_text);
        settings = view.findViewById(R.id.settings);
        //充值记录
        rechargeRecordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到UserRecordActivity
                Intent intent = new Intent(getActivity(), UserRecordActivity.class);
                intent.putExtra("recordTitle", "充值记录");
                startActivity(intent);
            }
        });

        //提现记录
        getRechargeRecordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到UserRecordActivity
                Intent intent = new Intent(getActivity(), UserRecordActivity.class);
                intent.putExtra("recordTitle", "提现记录");
                startActivity(intent);
            }
        });

        //看点记录
        videoPointRecordLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到UserRecordActivity
                Intent intent = new Intent(getActivity(), UserRecordActivity.class);
                intent.putExtra("recordTitle", "看点记录");
                startActivity(intent);
            }
        });

        //我的追剧
        catchVideoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VideoCatchFragment videoCatchFragment = new VideoCatchFragment();
                fragmentTransaction.replace(R.id.container, videoCatchFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.clearNowFragment();
                }
            }
        });

        //观看历史
        videoHistoryLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VideoHistoryFragment videoHistoryFragment = new VideoHistoryFragment();
                fragmentTransaction.replace(R.id.container, videoHistoryFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.clearNowFragment();
                }
            }
        });

        //投诉反馈
        feedBackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FeedBackFragment videoHistoryFragment = new FeedBackFragment();
                fragmentTransaction.replace(R.id.container, videoHistoryFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                MainActivity mainActivity = (MainActivity) getActivity();
                if (mainActivity != null) {
                    mainActivity.clearNowFragment();
                }
            }
        });

        mUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginLayout.getVisibility() == View.GONE) {
                    loginLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        mUserHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginLayout.getVisibility() == View.GONE) {
                    loginLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
//                EditText editTextAccount = v.findViewById(R.id.editTextAccount);
//                EditText editTextPassword = v.findViewById(R.id.editTextPassword);
                user.setAccount(editTextAccount.getText().toString());
                user.setPassword(editTextPassword.getText().toString());
                if (!Tools.isValidPhoneNumber(editTextAccount.getText().toString())) {
                    Toast.makeText(getContext(), "手机号不正确，请重新输入~", Toast.LENGTH_SHORT).show();
                    return;
                }
                RetrofitClient.getInstance().userLogin(user, new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user != null) {
                            AllData.USER = user;
                            SharedPreferencesUtil instance = SharedPreferencesUtil.getInstance(getContext());
                            instance.saveString("account", user.getAccount());
                            instance.saveString("password", user.getPassword());
                            Toast.makeText(getContext(), "登录成功", Toast.LENGTH_SHORT).show();
                            loginLayout.setVisibility(View.GONE);
                            reloadMineFragment();
                        } else {
                            Toast.makeText(getContext(), "登录失败", Toast.LENGTH_SHORT).show();
                            loginResultText.setText("登录失败，请检查账号密码是否正确");
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLayout.setVisibility(View.GONE);
                reloadMineFragment();
            }
        });

        //跳转到设置界面
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        //设置一个默认的用户头像，通用的。后期可以根据用户的信息进行修改
        String headStr = RetrofitClient.BASE_RESOURCE_URL + AllData.USER.getHeadurl();

        //初始化用户头像和背景
        Glide.with(this).load(headStr).into(mHeadBg);
        Glide.with(this).load(headStr).circleCrop().into(mUserHead);

        if (AllData.USER.getAccount() == null) {
            mUserName.setText("请点击头像登录");
            mUserDesc.setText("登录后可享受更多功能,这里可以显示用户自己设置的简介内容或者个性签名内容。显示长度设置为三行，具体表现形式为：第一行显示完整，第二行显示完整，第三行显示完整，超出部分显示省略号。");
        } else {
            //设置用户的名字和描述
            mUserName.setText(AllData.USER.getUsername());
            mUserDesc.setText(AllData.USER.getAddress());
        }

        return view;
    }


    private void reloadMineFragment() {
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
}
