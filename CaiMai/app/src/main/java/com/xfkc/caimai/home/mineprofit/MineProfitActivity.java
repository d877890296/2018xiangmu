package com.xfkc.caimai.home.mineprofit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/*我的收益*/
public class MineProfitActivity extends BaseActivity {


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
    @Bind(R.id.mineprofit_price)
    TextView mineprofitPrice;
    @Bind(R.id.gridview)
    CustomListView listView;

    private MineProfitListAdapter mineProfitListAdapter;
    private ArrayList<EmptyBean> list_data=new ArrayList<>();


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_mine_profit;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("我的收益");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        list_data.add(new EmptyBean());
        list_data.add(new EmptyBean());
        list_data.add(new EmptyBean());

        mineProfitListAdapter = new MineProfitListAdapter(this);
        mineProfitListAdapter.setData(list_data);
        listView.setAdapter(mineProfitListAdapter);

    }

    @Override
    protected void loadData() {

    }



    @OnClick(R.id.toolbar_left_img)
    public void onViewClicked() {
        finish();
    }
}
