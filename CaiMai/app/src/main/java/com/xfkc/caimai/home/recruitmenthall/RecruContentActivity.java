package com.xfkc.caimai.home.recruitmenthall;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.customview.StateButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 招募详情
 */
public class RecruContentActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.dianpu_title)
    TextView dianpuTitle;
    @Bind(R.id.a_type)
    TextView aType;
    @Bind(R.id.a_type_des)
    TextView aTypeDes;
    @Bind(R.id.a_type_price)
    TextView aTypePrice;
    @Bind(R.id.a_rb)
    RadioButton aRb;
    @Bind(R.id.b_type)
    TextView bType;
    @Bind(R.id.b_type_des)
    TextView bTypeDes;
    @Bind(R.id.b_type_price)
    TextView bTypePrice;
    @Bind(R.id.b_rb)
    RadioButton bRb;
    @Bind(R.id.c_type)
    TextView cType;
    @Bind(R.id.c_type_des)
    TextView cTypeDes;
    @Bind(R.id.c_type_price)
    TextView cTypePrice;
    @Bind(R.id.c_rb)
    RadioButton cRb;
    @Bind(R.id.commit)
    StateButton commit;
    private ArrayList<RadioButton> list_radio = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_recru_content;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarTitle.setText("招募详情");
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        list_radio.add(aRb);
        list_radio.add(bRb);
        list_radio.add(cRb);

    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.toolbar_left_img, R.id.commit,R.id.a_rb, R.id.b_rb, R.id.c_rb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.commit:
                break;
            case R.id.a_rb:
                setRadioButton(0);
                break;
            case R.id.b_rb:
                setRadioButton(1);
                break;
            case R.id.c_rb:
                setRadioButton(2);
                break;
        }
    }


    //设置radiobutton  选中
    private void setRadioButton(int number) {
        for (int i = 0; i < list_radio.size(); i++) {
            if (i == number) {
                list_radio.get(i).setChecked(true);
            } else {
                list_radio.get(i).setChecked(false);
            }
        }
    }


}
