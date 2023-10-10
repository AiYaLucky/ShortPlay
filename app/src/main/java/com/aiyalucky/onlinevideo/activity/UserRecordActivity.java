package com.aiyalucky.onlinevideo.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aiyalucky.onlinevideo.Data.AllData;
import com.aiyalucky.onlinevideo.Data.GetRecharge;
import com.aiyalucky.onlinevideo.Data.Recharge;
import com.aiyalucky.onlinevideo.Data.VideoPoint;
import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.adapter.RecordGetRechargeItemAdapter;
import com.aiyalucky.onlinevideo.adapter.RecordRechargeItemAdapter;
import com.aiyalucky.onlinevideo.adapter.VideoPointItemAdapter;

/**
 * 用户数据日志界面
 *
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class UserRecordActivity extends AppCompatActivity {

    public UserRecordActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_record);
        TextView recordTitle = findViewById(R.id.record_title);
        String title = getIntent().getStringExtra("recordTitle");
        recordTitle.setText(title);
        RecyclerView recordRecycler = findViewById(R.id.record_recycler_view);
        makeTempData();
        //给recyclerview设置线性布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recordRecycler.setLayoutManager(layoutManager);
        //根据title不同，设置不同的适配器和展示信息
        switch (title) {
            case "充值记录":
                RecordRechargeItemAdapter rechargeAdapter = new RecordRechargeItemAdapter(this, AllData.ORDER_INFO);
                recordRecycler.setAdapter(rechargeAdapter);
                break;
            case "提现记录":
                RecordGetRechargeItemAdapter getRechargeAdapter = new RecordGetRechargeItemAdapter(this, AllData.ORDER_GET_INFO);
                recordRecycler.setAdapter(getRechargeAdapter);
                break;
            case "看点记录":
                VideoPointItemAdapter videoPointAdapter = new VideoPointItemAdapter(this, AllData.VIDEO_POINT_INFO);
                recordRecycler.setAdapter(videoPointAdapter);
                break;
            default:
                break;
        }

    }

    /**
     * 制造临时数据用于显示
     */
    private void makeTempData() {

        for (int i = 0; i < 10; i++) {
            Recharge tempRecharge = new Recharge();
            tempRecharge.setRechargeTime(202004201627L);
            tempRecharge.setRechargeMoney(100);
            tempRecharge.setOrderId(1000 + i);
            AllData.ORDER_INFO.add(tempRecharge);
        }

        for (int i = 0; i < 10; i++) {
            GetRecharge tempRecharge = new GetRecharge();
            tempRecharge.setRechargeTime(202004201627L);
            tempRecharge.setRechargeMoney(100);
            tempRecharge.setOrderId(1000 + i);
            AllData.ORDER_GET_INFO.add(tempRecharge);
        }

        for (int i = 0; i < 10; i++) {
            VideoPoint tempRecharge = new VideoPoint();
            tempRecharge.setRechargeTime(202004201627L);
            tempRecharge.setRechargeMoney(100);
            tempRecharge.setOrderId(1000 + i);
            AllData.VIDEO_POINT_INFO.add(tempRecharge);
        }
    }
}
