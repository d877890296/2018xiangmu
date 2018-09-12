package com.xfkc.caimai.home.vipcard;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.VipCardBean;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

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

    private ArrayList<VipCardBean.DataBean.ListBean> list = new ArrayList<>();

    private VipCardListAdapter vipCardListAdapter;

    private String pageNum = "0", pageSize = "20";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_vip_card;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("会员卡");
        toolbarTitle.setTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#ff704d"));
        toolbarLeftImg.setImageResource(R.mipmap.back_orage);

        list_tv.clear();
        list_tv.add(zchykTv);
        list_tv.add(jkhykTv);

        list_view.clear();
        list_view.add(zchykLine);
        list_view.add(jkhykLine);
        vipCardListAdapter = new VipCardListAdapter(this);
        vipCardListAdapter.setData(list);
        vipCardListAdapter.setType(1);
        listview.setAdapter(vipCardListAdapter);
        setListViewClick();
    }

    private void setListViewClick() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VipCardBean.DataBean.ListBean listBean = list.get(position - 1);
                extraMap.put("price", listBean.cardPrice);
                extraMap.put("name", listBean.cardName);
                extraMap.put("carid", listBean.id);
                extraMap.put("cartype", position % 3);
                skip_classView(VipContentActivity.class, extraMap, false);
            }
        });
    }

    @Override
    protected void loadData() {
        if (list.size() != 0) {
            list.clear();
        }
//        for (int i = 0; i < 3; i++) {
//            list.add(new EmptyBean());
//        }

        PayFactory.getPayService()
                .findAllVipCard(pageNum, pageSize)
                .compose(RxHelper.<VipCardBean>io_main())
                .subscribe(new ProgressSubscriber<VipCardBean>(this) {
                    @Override
                    public void onNext(VipCardBean vipCardBean) {
                        if (vipCardBean.data.list != null && vipCardBean.data.list.size() != 0) {
                            list.addAll(vipCardBean.data.list);
                            vipCardListAdapter.setData(list);
                        }
                    }
                });

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
        vipCardListAdapter.setData(list);
        if (id == 0)
            vipCardListAdapter.setType(1);
        else
            vipCardListAdapter.setType(2);
    }
}
