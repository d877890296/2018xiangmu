package com.xfkc.caimai.home.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.dev.customview.MyGridView;
import com.goods.mineOrderforgoods.OrderforgoodsActivity;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.bean.MineVipCardBean;
import com.xfkc.caimai.bean.UserInfoBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.home.SettingActivity;
import com.xfkc.caimai.home.adapter.MyGridAdapter;
import com.xfkc.caimai.home.mineinfo.MineInfoActivity;
import com.xfkc.caimai.home.mineprofit.MineProfitActivity;
import com.xfkc.caimai.home.myjoinshop.MyJoinShopActivity;
import com.xfkc.caimai.home.vipcard.MineVipCardActivity;
import com.xfkc.caimai.home.wallet.WalletActivity;
import com.xfkc.caimai.loading.LoadingActivity;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;


/**
 * 社员中心
 */
public class SocialCentreFragment extends BaseFragment {


    //    @Bind(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @Bind(R.id.toolbar)
//    Toolbar toolbar;
    @Bind(R.id.account_iv)
    CircleImageView accountIv;
    @Bind(R.id.setting_iv)
    ImageView settingIv;
    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.mine_id)
    TextView mineId;
    @Bind(R.id.surplus_days)
    TextView surplusDays;
    @Bind(R.id.years_vip)
    TextView yearsVip;
    @Bind(R.id.kb_number)
    TextView kbNumber;
    @Bind(R.id.kb_surplus)
    TextView kbSurplus;
    @Bind(R.id.account_layout)
    LinearLayout accountLayout;
    @Bind(R.id.order_tv)
    SuperTextView orderTv;
    @Bind(R.id.wait_pay_tv)
    TextView waitPayTv;
    @Bind(R.id.wait_goods_number_tv)
    TextView waitGoodsNumberTv;
    @Bind(R.id.psz_tv)
    TextView pszTv;
    @Bind(R.id.dsh_number_tv)
    TextView dshNumberTv;
    @Bind(R.id.dpj_tv)
    TextView dpjTv;
    @Bind(R.id.dpj_number_tv)
    TextView dpjNumberTv;
    @Bind(R.id.tk_sh_tv)
    TextView tkShTv;
    @Bind(R.id.tksh_number_tv)
    TextView tkshNumberTv;
    @Bind(R.id.gridview)
    MyGridView gridview;
    @Bind(R.id.vip_year_layout)
    LinearLayout vipYearLayout;
    @Bind(R.id.kbye_layout)
    LinearLayout kbyeLayout;

    private MyGridAdapter myGridAdapter;
    private ArrayList<EmptyBean> button_listInfo = new ArrayList();
    private String token;

    private String payPwd = "", kbAmount = "";
    private UserInfoBean.DataBean userData;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_socialcentre;
    }

    @Override
    protected void initData() {
//        toolbarTitle.setTextColor(Color.WHITE);
//        toolbar.setBackgroundColor(Color.parseColor("#d51628"));
//        toolbarTitle.setText("社员中心");
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        myGridAdapter = new MyGridAdapter(mContext);
        for (int i = 0; i < 7; i++) {
            button_listInfo.add(new EmptyBean());
        }
        gridview.setAdapter(myGridAdapter);
        setGridClick();
        getVipCardTime();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    /*获取个人信息*/
    private void getData() {
        PayFactory.getPayService()
                .findUserDetByPhone(token)
                .compose(RxHelper.<UserInfoBean>io_main())
                .subscribe(new ProgressSubscriber<UserInfoBean>(mContext) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {

                        setInfo(userInfoBean);

                    }
                });
    }

    /*设置用户信息*/
    private void setInfo(UserInfoBean userInfoBean) {
        if (Tools.IsEmpty(userInfoBean.data.nicName)) {
            mineName.setText("昵称");
        } else {
            mineName.setText(userInfoBean.data.nicName);
        }
        Glide.with(mContext).load(userInfoBean.data.userImg).error(R.mipmap.heart_icon).into(accountIv);
        userData = userInfoBean.data;

        mineId.setText("幸福ID:" + userInfoBean.data.kcId);
        payPwd = userInfoBean.data.payPwd;
        kbAmount = userInfoBean.data.kbAmount + "";
        kbNumber.setText(kbAmount + "康币");

    }

    /*设置更多监听*/
    private void setGridClick() {
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0://康币钱包
                        extraMap.put("payPwd", payPwd + "");
                        extraMap.put("kbAmount", kbAmount + "");
                        skip_classView(WalletActivity.class, extraMap, false, true);
                        break;
                    case 1://我的会员卡
                        skip_classView(MineVipCardActivity.class, extraMap, false, true);
                        break;
                    case 2://我的收益
                        skip_classView(MineProfitActivity.class, extraMap, false, true);
                        break;
                    case 3://我加入的店铺
                        skip_classView(MyJoinShopActivity.class, extraMap, false, true);
                        break;
                    case 4://我发布的服务
                        ToastUtil.showToast("该功能暂未开放!");
                        break;
                    case 5://我提供的服务
                        ToastUtil.showToast("该功能暂未开放!");
                        break;
                }
            }
        });
    }


    @OnClick({R.id.account_iv, R.id.setting_iv,
            R.id.order_tv, R.id.wait_pay_tv, R.id.psz_tv,
            R.id.dpj_tv, R.id.tk_sh_tv, R.id.vip_year_layout, R.id.kbye_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_iv:
                extraMap.put("imageUrl", userData.userImg + "");
                extraMap.put("nickName", userData.nicName + "");
                extraMap.put("phone", userData.phone + "");
                extraMap.put("detailAdress", userData.detailAdress + "");
                skip_classView(MineInfoActivity.class, extraMap, false, true);
                break;
            case R.id.setting_iv:
                skip_classView(SettingActivity.class, extraMap, false, 1005);
                break;
            case R.id.order_tv:


                extraMap.put("baseType", -1);
                skip_classView(OrderforgoodsActivity.class, extraMap, false, false);
                // startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"0"));
                break;
            case R.id.wait_pay_tv:
                //     startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"1"));
                extraMap.put("baseType", 1);
                skip_classView(OrderforgoodsActivity.class, extraMap, false, false);
                break;
            case R.id.psz_tv:

                // startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"2"));
                extraMap.put("baseType", 2);
                skip_classView(OrderforgoodsActivity.class, extraMap, false, false);
                break;
            case R.id.dpj_tv:
                // startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"3"));
                extraMap.put("baseType", 3);
                skip_classView(OrderforgoodsActivity.class, extraMap, false, false);
                break;
            case R.id.tk_sh_tv:
                //   startActivity(new Intent(mContext, OrderActivity.class).putExtra(Constant.CATEGORY_ID,"4"));
                extraMap.put("baseType", 4);
                skip_classView(OrderforgoodsActivity.class, extraMap, false, false);

                break;
            case R.id.vip_year_layout:
                break;
            case R.id.kbye_layout:
                extraMap.put("payPwd", payPwd + "");
                extraMap.put("kbAmount", kbAmount + "");
                skip_classView(WalletActivity.class, extraMap, false, true);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1005 && resultCode == 1006) {
            skip_classView(LoadingActivity.class, extraMap, true, true);
        }
    }


    private void getVipCardTime() {
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
                            surplusDays.setText(mineVipCardBean.data.get(0).remainDays+"天");
                            yearsVip.setText(mineVipCardBean.data.get(0).cardName+"");
                        }
                    }
                });

    }
}
