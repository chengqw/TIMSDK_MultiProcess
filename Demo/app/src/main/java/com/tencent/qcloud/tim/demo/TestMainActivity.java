package com.tencent.qcloud.tim.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.qcloud.tim.demo.login.LoginForDevActivity;
import com.tencent.qcloud.tim.demo.login.LoginForDevOtherActivity;
import com.tencent.qcloud.tuicore.component.activities.BaseLightActivity;

public class TestMainActivity extends BaseLightActivity {
    private static final String TAG = TestMainActivity.class.getSimpleName();

    Button mainButton, otherButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }

        mainButton = findViewById(R.id.main_processs);
        otherButton = findViewById(R.id.other_process);

        V2TIMManager.getInstance().unInitSDK();

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestMainActivity.this, LoginForDevActivity.class);
                intent.putExtras(getIntent());
                startActivity(intent);
                //finish();
            }
        });

        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestMainActivity.this, LoginForDevOtherActivity.class);
                intent.putExtras(getIntent());
                startActivity(intent);
                //finish();
            }
        });
    }
}
