package com.xfkc.caimai.home.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.goods.city.GoodsCityActivity;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.home.adapter.ModuleAdapter;
import com.xfkc.caimai.home.vipcard.VipCardActivity;
import com.xfkc.caimai.order.ConfirmOrderActivity;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 大仓库
 */
public class HomeFragment extends BaseFragment {


    @Bind(R.id.home_list)
    ListView homeList;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;

    private ArrayList<String> listData = new ArrayList<>();
    private ModuleAdapter moduleAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {

        toolbarTitle.setText("幸福康城");

        listData.clear();
//        for (int i = 0; i < 8; i++) {
//            listData.add("" + i);
//        }

        listData.add("幸福公社");
        listData.add("招募大厅");
        listData.add("会员卡");
        listData.add("每晚8点直播");

        moduleAdapter = new ModuleAdapter(mContext);
        moduleAdapter.setData(listData);
        homeList.setAdapter(moduleAdapter);

        setListClick();

    }

    /*设置首页列表点击*/
    private void setListClick() {
        homeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position = position - 1;

                switch (position) {
                    case 0:
                      //  ToastUtil.showToast("暂未开放");
                        skip_classView(GoodsCityActivity.class,extraMap,false,false);
                        break;
                    case 1:
                        startActivity(new Intent(mContext, ConfirmOrderActivity.class));
                        break;
                    case 2:
                        skip_classView(VipCardActivity.class,extraMap,false,false);
                        break;
                    default:
                        ToastUtil.showToast(position + "");
                        break;
                }
            }
        });

    }

}
