package com.xfkc.caimai.home.fragment;


import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.home.adapter.FeelingAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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

    private ArrayList<String> listData = new ArrayList<>();
    private FeelingAdapter feelingAdapter;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_feeling;
    }

    @Override
    protected void initData() {

        listData.clear();
        for (int i = 0; i < 8; i++) {
            listData.add("" + i);
        }

        feelingAdapter = new FeelingAdapter(mContext);
        feelingAdapter.setData(listData);
        feelingList.setAdapter(feelingAdapter);

    }


    @OnClick(R.id.share_iv)
    public void onViewClicked() {
        ToastUtil.showToast("情怀链分享暂未开放");
    }
}
