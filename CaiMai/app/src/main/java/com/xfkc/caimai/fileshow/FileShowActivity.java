package com.xfkc.caimai.fileshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.PdfAndWorldUtils;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;
//import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
//import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
//import es.voghdev.pdfviewpager.library.remote.DownloadFile;


public class FileShowActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_title_image)
    ImageView toolbarTitleImage;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @Bind(R.id.toolbar_right_img)
    ImageView toolbarRightImg;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.pdfcontent)
    LinearLayout pdfcontent;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.linear)
    LinearLayout linear;
    @Bind(R.id.content)
    RelativeLayout content;
    @Bind(R.id.activity_file_show)
    LinearLayout activityFileShow;
//    @Bind(R.id.pdfview)
//    com.joanzapata.pdfview.PDFView pdfview;
    private WebView webView;
    private String urlString;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    //微软在线预览office文档
    private final String MicrosoftOnlinePreviewUrl = "https://view.officeapps.live.com/op/view.aspx?src=";
    //谷歌在线预览——不推荐使用
    private final String GoogleOnlinePreviewUrl = "http://docs.google.com/gview?embedded=true&url=";
    //    private RemotePDFViewPager remotePDFViewPager;
//    private PDFPagerAdapter adapter;
    private String fileName;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_fileshow;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setToolbar();
        initData();
        initView();
    }

    @Override
    protected void loadData() {

    }

    private void setToolbar() {
        toolbarTitle.setText("附件详情");
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
//        pcChildToolbarSubtitle.setText("活动详情");
//        pcChildToolbarSearchbtn.setVisibility(View.GONE);
//        setSupportActionBar(pcenterToolb);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = this.getIntent();
        if (intent == null) return;
        urlString = intent.getStringExtra("data");//接收上一个页面传过来的文件地址
        fileName = urlString.substring(urlString.lastIndexOf("/") + 1);//取文件名
        Log.e("文件名：", "----" + fileName);
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linear);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setMax(100);
        webView = (WebView) findViewById(R.id.webview);

        if (urlString.endsWith("pdf")) {//是pdf文件
            checkOsVersion();
        } else if (isPicture(urlString))//是图片，webview可以直接加载网络图片
            webView.loadUrl(urlString);
        else//是office文档，使用 微软在线预览地址+文件地址 预览，Google在线预览需会存在问题
            webView.loadUrl(MicrosoftOnlinePreviewUrl + urlString);

//        Log.e(Tools.getServerPath(this) + urlString);//LogD是一个log工具类，可以注释掉

        webView.setWebViewClient(new MyWebViewClient());    //设置在本页显示
        webView.setWebChromeClient(new MyWebChromeClient());  //设置进度监听，自定义类MyWebChromeClient继承自WebChromeClient
        webView.getSettings().setLoadWithOverviewMode(true);  //设置加载在本页
        webView.getSettings().setJavaScriptEnabled(true);   //加载javascript
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
    }

    /**
     * 验证版本号，5.0以上可以预览pdf,否则...
     * 使用RemotePDFViewPager需添加依赖：【支持5.0以上Android系统】
     * compile 'es.voghdev.pdfviewpager:library:1.0.1'
     */
    private void checkOsVersion() {
        webView.setVisibility(View.GONE);
        String osVersion = Tools.getSystemVersion();
//        LogD.d("Android系统版本号：" + osVersion);
        int index = osVersion.indexOf(".");
        int os = Integer.valueOf(osVersion.substring(index - 1, index));
//        if (os >= 5) {
            /**
             * 这里添加了pdf文件加载事件
             * Tools.getServerPath(this) + urlString  是pdf的网络地址
             */
//            pdfview.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
//            remotePDFViewPager = new RemotePDFViewPager(this,urlString, this);
            PdfAndWorldUtils.downLoadFromNet(urlString,"xfkc.pdf","pdf",mContext);
//        pdfview.fromFile(new File(urlString))
//                .defaultPage(1)
//                .onPageChange(new OnPageChangeListener() {
//                    @Override
//                    public void onPageChanged(int page, int pageCount) {
//                        // 当用户在翻页时候将回调。
//                        Toast.makeText(getApplicationContext(), page + " / " + pageCount, Toast.LENGTH_SHORT).show();
//                    }
//                }).load();

//        } else
            // 我在上一个页面已经判断了系统版本，这里不做出来
//            finish();
    }

    /**
     * 判断是不是office文档
     */
    private boolean isPicture(String filePath) {
        if (filePath.endsWith("doc") || filePath.endsWith("docx") ||
                filePath.endsWith("xls") || filePath.endsWith("xlsx") ||
                filePath.endsWith("ppt") || filePath.endsWith("pptx"))
            return false;
        return true;
    }


    /**
     * pdf预览下载事件
     * 加载成功
     *
     * @param url
     * @param destinationPath
     */
//    @Override
//    public void onSuccess(String url, String destinationPath) {
//        content.setVisibility(View.GONE);
//        pdfcontent.setVisibility(View.VISIBLE);
//        adapter = new PDFPagerAdapter(this, fileName);
//        remotePDFViewPager.setAdapter(adapter);
//        pdfcontent.addView(remotePDFViewPager);
//    }

//    @Override
//    public void onFailure(Exception e) {
//        content.setVisibility(View.GONE);
//        pdfcontent.setVisibility(View.VISIBLE);
//        Log.d("错误信息：" ,"==="+ e.getMessage());
//        Toast.makeText(this, "文件预览失败", Toast.LENGTH_SHORT).show();
//    }

    /**
     * 加载pdf进度
     *
     * @param // 当前进度——字节数
     * @param //total    总进度——字节数
     */
//    @Override
//    public void onProgressUpdate(int progress, int total) {
//        progressBar.setProgress((int) (((float) progress) / total * 100));
//    }
    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }


    //webview页面加载进度监听类
    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //加载进度newProgress
            progressBar.setProgress(newProgress);
            if (progressBar.getProgress() > 80) {
                webView.setVisibility(View.VISIBLE);
            }
            if (progressBar.getProgress() >= 100) {
                linearLayout.setVisibility(View.GONE);
            }
        }
    }

    //页面加载监听类
    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);//本webView加载【不会去调用系统的加载】
            return true;
        }
    }

}
