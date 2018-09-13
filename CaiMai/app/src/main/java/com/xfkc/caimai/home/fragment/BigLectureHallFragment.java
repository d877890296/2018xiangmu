package com.xfkc.caimai.home.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.BigLectureBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.fileshow.FileShowActivity;
import com.xfkc.caimai.home.adapter.BigListAdapter;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 大讲堂
 */
public class BigLectureHallFragment extends BaseFragment {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.wqsp_tv)
    TextView wqspTv;
    @Bind(R.id.wqsp_line)
    View wqspLine;
    @Bind(R.id.wqsp)
    LinearLayout wqsp;
    @Bind(R.id.ppt_tv)
    TextView pptTv;
    @Bind(R.id.ppt_line)
    View pptLine;
    @Bind(R.id.ppt)
    LinearLayout ppt;
    @Bind(R.id.mine_colect_tv)
    TextView mineColectTv;
    @Bind(R.id.mine_colect_line)
    View mineColectLine;
    @Bind(R.id.mine_colect)
    LinearLayout mineColect;
    @Bind(R.id.listview)
    CustomListView listview;

    //标题集合
    private ArrayList<TextView> list_tv = new ArrayList<>();
    //下划线
    private ArrayList<View> list_view = new ArrayList<>();

    private BigListAdapter bigListAdapter;
    private ArrayList<BigLectureBean.DataBean> list_data = new ArrayList<>();
    private String token;
    private int type = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_biglecturehall;
    }

    @Override
    protected void initData() {

        toolbarTitle.setText("大讲堂");
        toolbarTitle.setTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#ff704d"));

        list_tv.clear();
        list_tv.add(wqspTv);
        list_tv.add(pptTv);
        list_tv.add(mineColectTv);

        list_view.clear();
        list_view.add(wqspLine);
        list_view.add(pptLine);
        list_view.add(mineColectLine);

        bigListAdapter = new BigListAdapter(mContext);
        bigListAdapter.setData(list_data);
        listview.setAdapter(bigListAdapter);
        updateShow(0);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position = position - 1;
                startActivity(new Intent(mContext, FileShowActivity.class)
                        .putExtra("data", list_data.get(position).url));
            }
        });
    }


    @OnClick({R.id.wqsp, R.id.ppt, R.id.mine_colect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wqsp:
                type = 0;
                updateShow(0);
                break;
            case R.id.ppt:
                type = 1;
                updateShow(1);
                break;
            case R.id.mine_colect:
                updateShow(2);
                break;
        }
    }

    /*加载数据*/
    private void loadData() {
        if (list_data.size() != 0)
            list_data.clear();

        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        PayFactory.getPayService().findVedioByType(token, type + "")
                .compose(RxHelper.<BigLectureBean>io_main())
                .subscribe(new ProgressSubscriber<BigLectureBean>(mContext) {
                    @Override
                    public void onNext(BigLectureBean bigLectureBean) {
                        if (bigLectureBean.data != null && bigLectureBean.data.size() != 0) {
                            list_data.addAll(bigLectureBean.data);
                            bigListAdapter.setData(list_data);
                        } else {
                            bigListAdapter.setData(list_data);
                        }
                    }
                });

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
        if (id == 2) {
            if (list_data.size() != 0){
                list_data.clear();
                bigListAdapter.setData(list_data);
            }
        } else {
            loadData();
        }
    }
}
