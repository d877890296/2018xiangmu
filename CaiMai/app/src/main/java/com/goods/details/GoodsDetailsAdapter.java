package com.goods.details;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;

import com.dev.customview.MyWebView;
import com.goods.city.GoodsListModel;
import com.goods.city.GoodsValue;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.application.MyApplication;


public class GoodsDetailsAdapter extends BaseAdapter {
    private int[] type = {0, 1, 2};
    public Context context;
    public LayoutInflater inflater;
    final String mimeType = "text/html";
    final String encoding = "UTF-8";

    private GoodsListModel goodsListModel;



    public GoodsDetailsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        // TODO Auto-generated constructor stub
        goodsListModel = GoodsValue.getInstance().getGoodsListModel();


    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return type[position];
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub


        switch (position) {
            case 0://详情
                convertView = inflater.inflate(R.layout.gd_goodsother_fristitem, null);
                MyWebView detailsContent_web = (MyWebView) convertView.findViewById(R.id.detailsContent_web);
                try {
                    if (Tools.IsEmpty(goodsListModel.content)) {

                        detailsContent_web.loadData("暂无详情", mimeType, encoding);
                    } else {

                        String content = goodsListModel.content;

                            // 启动js
                            detailsContent_web.getSettings().setJavaScriptEnabled(true);
                        // ScrollBar显示
                        detailsContent_web.setHorizontalScrollBarEnabled(false);// 取消Horizontal
                        // ScrollBar显示
                        detailsContent_web.setScrollable(false);
                            detailsContent_web.loadDataWithBaseURL("", backString(content), mimeType, encoding, null);


                     //   detailsContent_web.loadData(backString(content),"text/html","UTF-8");
                      //  detailsContent_web.setWebViewClient(new MyWebViewClient());
//                        if (setWebViewContent == null) {
//                            new SetWebViewContent(context, detailsContent_web, content);
//                        }
                    }

                } catch (Exception ex) {

                    ex.printStackTrace();

                }

                break;
            case 1://评价
                convertView = inflater.inflate(R.layout.gd_goodsother_seconditem, null);
                break;
            case 2://推荐
                convertView = inflater.inflate(R.layout.gd_goodsother_threeitem, null);
                break;
            default:
                break;
        }

        return convertView;
    }



    public String backString(String string) {

        String dd = "<div style=\"text-align:center\">" + "<img ";

        String javascript = "<script type=\"text/javascript" + "\">" + "var maxWidth =350;"
                + "var img = document.getElementsByTagName('img');" + "	for(var i=0;i<img.length;i++) {"
                + "img[i].onload = function() {" + "if(this.width>maxWidth) {" + "this.width=maxWidth;" + "}}}"
                + "</script>";

        string = string.replace("<img", dd);
        string = string.replace("/>", "></div>") + javascript;
        String htm = "<html>" + "<head>" + "<meta http-equiv=\"Content-Type\""
                + " content=\"text/html; charset=gb2312\">" + "<title>Untitled Document</title><style>body{margin:0px;}</style>" + "</head>" + "<body>";
        String whtml = "</body></html>";
        string = htm + string + whtml;
        return string;
    }

    // 监听
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // // //页面加载好以后，在放开图片：
            // mWebView.getSettings().setBlockNetworkImage(false);
            view.getSettings().setJavaScriptEnabled(true);
            super.onPageFinished(view, url);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            view.getSettings().setJavaScriptEnabled(true);
            // // //页面加载好以后，在放开图片：
           // mWebView.getSettings().setBlockNetworkImage(false);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

            super.onReceivedError(view, errorCode, description, failingUrl);

        }
    }
}
