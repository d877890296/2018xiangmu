package com.xfkc.caimai.home.vipcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/*会员卡*/
public class VipCardActivity extends BaseActivity {

    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.zchyk_tv)
    TextView zchykTv;
    @Bind(R.id.zchyk_line)
    View zchykLine;
    @Bind(R.id.zchyk)
    LinearLayout zchyk;
    @Bind(R.id.jkhyk_tv)
    TextView jkhykTv;
    @Bind(R.id.jkhyk_line)
    View jkhykLine;
    @Bind(R.id.jkhyk)
    LinearLayout jkhyk;
    @Bind(R.id.listview)
    CustomListView listview;

    //标题集合
    private ArrayList<TextView> list_tv = new ArrayList<>();
    //下划线
    private ArrayList<View> list_view = new ArrayList<>();

    private ArrayList<EmptyBean> list = new ArrayList<>();

    private VipCardListAdapter vipCardListAdapter;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_vip_card;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("大讲堂");
        toolbarTitle.setTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#ff704d"));
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        list_tv.clear();
        list_tv.add(zchykTv);
        list_tv.add(jkhykTv);

        list_view.clear();
        list_view.add(zchykLine);
        list_view.add(jkhykLine);
        updateShow(0);
    }

    @Override
    protected void loadData() {
        list.clear();
        for (int i = 0; i < 3; i++) {
            list.add(new EmptyBean());
        }
        vipCardListAdapter = new VipCardListAdapter(this);
        vipCardListAdapter.setData(list);
        listview.setAdapter(vipCardListAdapter);
    }


    @OnClick({R.id.toolbar_left_img, R.id.zchyk, R.id.jkhyk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.zchyk:
                updateShow(0);
                break;
            case R.id.jkhyk:
                updateShow(1);
                break;
        }
    }


    /*查询线条变化*/
    private void updateShow(int id) {
        for (int i = 0; i < list_view.size(); i++) {
            if (id == i) {
                list_tv.get(i).setTextColor(Color.parseColor("#ff704d"));
                list_view.get(i).setBackgroundColor(Color.parseColor("#ff704d"));
            } else {
                list_tv.get(i).setTextColor(Color.BLACK);
                list_view.get(i).setBackgroundColor(Color.WHITE);
            }
        }
        loadData();
    }
}
