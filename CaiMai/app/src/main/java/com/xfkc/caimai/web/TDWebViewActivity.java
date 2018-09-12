package com.xfkc.caimai.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.library.AgentWeb;
import com.just.library.AgentWebSettings;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.WebDefaultSettingsManager;
import com.xfkc.caimai.R;
import com.xfkc.caimai.config.Constant;
import com.xfkc.caimai.web.base.RxActivity;


/**
 * Created by LK on 2017/8/10 11:40.
 */

public class TDWebViewActivity extends RxActivity {

    private AgentWeb mAgentWeb;
    private LinearLayout layout;
    private TextView titleText;
    private String url;

    @Override
    protected void setUpContentView() {
        setContentView(R.layout.act_webview, R.string.h5_title);
    }


    @Override
    protected void initViews(Bundle savedInstanceState) {
        url = getIntent().getStringExtra(Constant.WEB_URL);
        layout = (LinearLayout) this.findViewById(R.id.container);
        titleText = (TextView) this.findViewById(R.id.toolbar_title);
        titleText.setTextSize(16);
    }

    @Override
    protected void loadData() {
        buildAgentWeb();
    }

    protected void buildAgentWeb() {
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent(layout, new LinearLayout.LayoutParams(-1, -1))//
                .useDefaultIndicator()//
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(getReceivedTitleCallback())//标题回调
                .setAgentWebSettings(getAgentWebSettings())
                .setSecutityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()
                .go(url);
//        mAgentWeb.getJsInterfaceHolder().addJavaObject("android",new AndroidInterface(mAgentWeb, mContext));

    }


    private
    @Nullable
    ChromeClientCallbackManager.ReceivedTitleCallback getReceivedTitleCallback() {
        return new ChromeClientCallbackManager.ReceivedTitleCallback() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                titleText.setText(title);
            }
        };
    }

    public
    @Nullable
    AgentWebSettings getAgentWebSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onPause() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mAgentWeb != null)
            mAgentWeb.uploadFileResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    protected void onDestroy() {
        if (mAgentWeb != null)
            mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }


}
