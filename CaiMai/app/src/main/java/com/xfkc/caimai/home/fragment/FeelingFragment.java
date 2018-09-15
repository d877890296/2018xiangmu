package com.xfkc.caimai.home.fragment;


import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.FeelingBean;
import com.xfkc.caimai.bean.UserInfoBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.home.adapter.FeelingAdapter;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Subscriber;

/**
 * 情怀链
 */
public class FeelingFragment extends BaseFragment {


    @Bind(R.id.account_iv)
    CircleImageView accountIv;
    @Bind(R.id.feeling_name)
    TextView feelingName;
    @Bind(R.id.people_number)
    TextView peopleNumber;
    @Bind(R.id.get_money)
    TextView getMoney;
    @Bind(R.id.share_iv)
    ImageView shareIv;
    @Bind(R.id.feelint_list)
    ListView feelingList;

    private int pageNum=0,pageSize=20;

    private ArrayList<FeelingBean.DataBean.NextMemUserPageBean.ListBean> listData = new ArrayList<>();
    private FeelingAdapter feelingAdapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_feeling;
    }

    @Override
    protected void initData() {

        listData.clear();
//        for (int i = 0; i < 8; i++) {
//            listData.add("" + i);
//        }

        feelingAdapter = new FeelingAdapter(mContext);
        String token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        getUserInfo(token);
        PayFactory.getPayService().findChain(token,pageNum,pageSize)
        .compose(RxHelper.<FeelingBean>io_main())
        .subscribe(new Subscriber<FeelingBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(FeelingBean feelingBean) {
                peopleNumber.setText("情怀链人数: "+feelingBean.data.nextNum);
                getMoney.setText("情怀链收益: "+feelingBean.data.nextMoney);

                if (feelingBean.data.nextMemUserPage.list!=null&&feelingBean.data.nextMemUserPage.list.size()!=0){
                    listData.addAll(feelingBean.data.nextMemUserPage.list);
                    feelingAdapter.setData(listData);
                    feelingList.setAdapter(feelingAdapter);
                }
            }
        });


    }

    /*获取用户信息*/
    private void getUserInfo(String token) {
        PayFactory.getPayService()
                .findUserDetByPhone(token)
                .compose(RxHelper.<UserInfoBean>io_main())
                .subscribe(new Subscriber<UserInfoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        feelingName.setText(userInfoBean.data.realName);
                        Glide.with(mContext).load(userInfoBean.data.userImg).error(R.mipmap.heart_icon).into(accountIv);
                        feelingAdapter.setTypeData(userInfoBean.data.realName,userInfoBean.data.userImg,userInfoBean.data.createTime);
                    }
                });

    }


    @OnClick(R.id.share_iv)
    public void onViewClicked() {
        ToastUtil.showToast("情怀链分享暂未开放");
    }
}
