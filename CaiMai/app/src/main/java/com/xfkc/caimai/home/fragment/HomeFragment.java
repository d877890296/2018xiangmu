package com.xfkc.caimai.home.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.goods.city.GoodsCityActivity;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.BannerBean;
import com.xfkc.caimai.bean.MineVipCardBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.dialog.CommonDialog;
import com.xfkc.caimai.home.adapter.ModuleAdapter;
import com.xfkc.caimai.home.recruitmenthall.RecruitmentHallActivity;
import com.xfkc.caimai.home.vipcard.VipCardActivity;
import com.xfkc.caimai.loading.LoadingActivity;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

import static com.hyf.tdlibrary.utils.ToastUtil.showToast;

/**
 * 大仓库
 */
public class HomeFragment extends BaseFragment {


    @Bind(R.id.home_list)
    ListView homeList;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ArrayList<String> listData = new ArrayList<>();
    private ModuleAdapter moduleAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        toolbar.setBackgroundColor(Color.parseColor("#ff704d"));
        toolbarTitle.setText("幸福康城");
        toolbarTitle.setTextColor(Color.WHITE);
        listData.clear();

//        listData.add("幸福公社");
        listData.add("公社仓库");
        listData.add("招募大厅");
//        listData.add("会员卡");
        listData.add("专项服务卡");
        listData.add("每晚8点直播");


        getBanner();//获取轮播图
        setListClick();
    }

    /*获取轮播图*/
    private void getBanner() {
        PayFactory.getPayService()
                .getBannerData("0", "10")
                .compose(RxHelper.<BannerBean>io_main())
                .subscribe(new Subscriber<BannerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        moduleAdapter = new ModuleAdapter(mContext);
                        moduleAdapter.setData(listData, bannerBean.data.list);
                        homeList.setAdapter(moduleAdapter);
                    }
                });
    }

    /*设置首页列表点击*/
    private void setListClick() {
        homeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position = position - 1;

                switch (position) {
                    case 0:
                        skip_classView(GoodsCityActivity.class, extraMap, false, false);
                        break;
                    case 1:
                        startActivity(new Intent(mContext, RecruitmentHallActivity.class));
                        break;
                    case 2:
                        skip_classView(VipCardActivity.class, extraMap, false, false);
                        break;
                    case 3:
                        showToast("该功能暂未开放");
                        break;
                    default:

                        break;
                }
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getMineVipCard();
    }

    private void getMineVipCard() {
        String token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        PayFactory.getPayService()
                .getUserVipCard(token)
                .compose(RxHelper.<MineVipCardBean>io_main())
                .subscribe(new Subscriber<MineVipCardBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(MineVipCardBean mineVipCardBean) {
                        if (mineVipCardBean.data != null && mineVipCardBean.data.size() != 0) {
                        } else {
                            new CommonDialog(mContext).builder().setTitle("提示")
                                    .setContentMsg("新用户请购买会员卡")
                                    .setCancelable(false)
//                                    .setCanceledOnTouchOutside(false)
                                    .setPositiveBtn("确定", new CommonDialog.OnPositiveListener() {
                                        @Override
                                        public void onPositive(View view) {
                                            skip_classView(VipCardActivity.class, extraMap, false, true);
                                        }
                                    })
                                    .setNegativeBtn("切换账号", new CommonDialog.OnNegativeListener() {
                                        @Override
                                        public void onNegative(View view) {
                                            SharedPrefUtil.put(mContext, SharedPref.TOKEN, "");
                                            skip_classView(LoadingActivity.class, extraMap, true, true);
                                        }
                                    })
                                    .show();
                        }
                    }
                });

    }
}
