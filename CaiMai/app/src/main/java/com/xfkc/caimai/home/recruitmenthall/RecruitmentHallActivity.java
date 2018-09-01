package com.xfkc.caimai.home.recruitmenthall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.customview.CustomListView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 招募大厅
 */
public class RecruitmentHallActivity extends BaseActivity {


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
    @Bind(R.id.zming_tv)
    TextView zmingTv;
    @Bind(R.id.zming_line)
    View zmingLine;
    @Bind(R.id.zming)
    LinearLayout zming;
    @Bind(R.id.zm_complete_tv)
    TextView zmCompleteTv;
    @Bind(R.id.zm_complete_line)
    View zmCompleteLine;
    @Bind(R.id.zm_complete)
    LinearLayout zmComplete;
    @Bind(R.id.listview)
    CustomListView listview;

    //标题集合
    private ArrayList<TextView> list_tv = new ArrayList<>();
    //下划线
    private ArrayList<View> list_view = new ArrayList<>();

    private ArrayList<EmptyBean> list = new ArrayList<>();

    private RecruHallListAdapter recruHallListAdapter;
    private RecruedHallListAdapter recruedHallListAdapter;

    private int TYPE = 0;

    private int pageNum = 0, pageSize = 20;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_recruitment_hall;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        toolbarTitle.setText("招募大厅");
        toolbarTitle.setTextColor(Color.WHITE);
        toolbar.setBackgroundColor(Color.parseColor("#ff704d"));
        toolbarLeftImg.setImageResource(R.mipmap.back_orage);

        list_tv.clear();
        list_tv.add(zmingTv);
        list_tv.add(zmCompleteTv);

        list_view.clear();
        list_view.add(zmingLine);
        list_view.add(zmCompleteLine);

        recruHallListAdapter = new RecruHallListAdapter(this);
        recruedHallListAdapter = new RecruedHallListAdapter(this);

        setClick();

    }

    /*设置监听*/
    private void setClick() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TYPE == 0) {

                } else {

                }
            }
        });
    }

    @Override
    protected void loadData() {

        list.add(new EmptyBean());
        list.add(new EmptyBean());

        PayFactory.getPayService()
                .recruitmentHall(pageNum, pageSize,token)
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {


                    }
                });


    }


    @OnClick({R.id.toolbar_left_img, R.id.zming, R.id.zm_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.zming:
                TYPE = 0;
                updateShow(0);
                break;
            case R.id.zm_complete:
                TYPE = 1;
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
        if (id == 1) {
            recruHallListAdapter.setData(list);
            listview.setAdapter(recruHallListAdapter);
        } else {
            recruedHallListAdapter.setData(list);
            listview.setAdapter(recruedHallListAdapter);
        }

    }

}
