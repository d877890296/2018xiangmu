package com.xfkc.caimai.home.fragment;


import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseFragment;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.home.adapter.BigListAdapter;

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
    private ArrayList<EmptyBean> list_data = new ArrayList<>();

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
        updateShow(0);
    }


    @OnClick({R.id.wqsp, R.id.ppt, R.id.mine_colect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wqsp:
                updateShow(0);
                break;
            case R.id.ppt:
                updateShow(1);
                break;
            case R.id.mine_colect:
                updateShow(2);
                break;
        }
    }

    /*加载数据*/
    private void loadData() {
        list_data.clear();
        for (int i = 0; i < 3; i++) {
            list_data.add(new EmptyBean());
        }
        bigListAdapter = new BigListAdapter(mContext);
        bigListAdapter.setData(list_data);
        listview.setAdapter(bigListAdapter);
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
