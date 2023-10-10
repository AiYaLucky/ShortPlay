package com.aiyalucky.onlinevideo.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.LineHeightSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.utils.SharedPreferencesUtil;
import com.aiyalucky.onlinevideo.utils.Tools;

/**
 * 用户数据日志界面
 *
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class SettingsActivity extends AppCompatActivity {

    private LinearLayout updateCheckLayout;
    private LinearLayout clearCacheLayout;
    private LinearLayout argreementLayout;
    private LinearLayout loginOutLayout;
    private LinearLayout settings_layout;
    private LinearLayout privacyLayout;
    private Button buttonBack;
    private Button privacyBack;

    private TextView cacheSize;
    private TextView privacyTextView;
    private ProgressBar progressBar;

    public SettingsActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        updateCheckLayout = findViewById(R.id.update_check_layout);
        clearCacheLayout = findViewById(R.id.clear_cache_layout);
        argreementLayout = findViewById(R.id.agreement_layout);
        loginOutLayout = findViewById(R.id.login_out_layout);
        buttonBack = findViewById(R.id.button_back);
        privacyBack = findViewById(R.id.privacyBack);
        cacheSize = findViewById(R.id.cache_size);
        progressBar = findViewById(R.id.progressBar);
        privacyLayout = findViewById(R.id.privacy_layout);

        privacyTextView = findViewById(R.id.privacyTextView);
        String privacyText = getResources().getString(R.string.privacy_policy);

        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(Html.fromHtml(privacyText));
        spannableBuilder.setSpan(new LineHeightSpan() {
            @Override
            public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
                // 调整行间距
                fm.bottom += 1; // 根据需要设置行间距的偏移量
                fm.descent += 1;
            }
        }, 0, spannableBuilder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        spannableBuilder.setSpan(new RelativeSizeSpan(0.7F), 0, spannableBuilder.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
// 根据需要设置字体大小的缩放比例

        privacyTextView.setText(spannableBuilder);

        String cacheSizes = Tools.getCacheSize(this);
        cacheSize.setText(cacheSizes);
        /*
         * 检查更新
         */
        updateCheckLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*
         * 清除缓存
         */
        clearCacheLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Tools.clearCache(getApplicationContext());
                        String cacheSizes = Tools.getCacheSize(getApplicationContext());
                        cacheSize.setText(cacheSizes);
                        Toast.makeText(getApplicationContext(), "清除缓存成功", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }, 1000);

            }
        });

        /*
         * 用户协议
         */
        argreementLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacyLayout.setVisibility(View.VISIBLE);
            }
        });

        privacyBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privacyLayout.setVisibility(View.GONE);
            }
        });

        /*
         * 退出登录
         */
        loginOutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferencesUtil.getInstance(getApplicationContext()).removeKey("account");
                SharedPreferencesUtil.getInstance(getApplicationContext()).removeKey("password");
                finish();
            }
        });

        /*
         * 返回
         */
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
