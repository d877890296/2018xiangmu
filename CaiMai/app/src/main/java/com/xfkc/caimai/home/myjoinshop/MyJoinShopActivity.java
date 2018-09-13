package com.xfkc.caimai.home.myjoinshop;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.MyJoinBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import rx.Subscriber;

/*我加入的店铺*/
public class MyJoinShopActivity extends BaseActivity {


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
    @Bind(R.id.listview)
    CustomListView listview;

    private MyJoinListAdapter myJoinListAdapter;
    private ArrayList<MyJoinBean.DataBean.InrecruiListBean> list_data = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_myjoin_shop;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("我加入的店铺");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        myJoinListAdapter = new MyJoinListAdapter(this);

    }

    @Override
    protected void loadData() {
        PayFactory.getPayService()
                .myjoinshop(token)
                .compose(RxHelper.<MyJoinBean>io_main())
                .subscribe(new Subscriber<MyJoinBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyJoinBean myJoinBean) {
                        if (myJoinBean.data != null && myJoinBean.data.inrecruiList.size() != 0) {
                            myJoinListAdapter.setData(myJoinBean.data);
                            listview.setAdapter(myJoinListAdapter);
                        }
                    }
                });


    }


    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }
}
