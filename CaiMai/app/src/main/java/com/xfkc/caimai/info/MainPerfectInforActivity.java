package com.xfkc.caimai.info;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.util.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 完善信息
 */
public class MainPerfectInforActivity extends BaseActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.id_card)
    EditText idCard;
    @Bind(R.id.choose_address)
    SuperTextView chooseAddress;
    @Bind(R.id.address_content)
    EditText addressContent;
    @Bind(R.id.commit)
    StateButton commit;


    public String province = "北京市";//省份
    public String provinceId;//省份  id
    public String city = "北京市";//城市
    public String cityId;//城市Id
    public String district = "朝阳区";//区县
    public String districtId;//区县Id

    private String phone;
    private String address_content;
    @Override
    protected int getLayoutResource() {
        return R.layout.activity_perfectinfor;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
        toolbarTitle.setText("完善信息");

        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void loadData() {

    }

    @OnClick({R.id.toolbar_left_img, R.id.choose_address, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                backHistory(1010, true, true, extraMap);
                break;
            case R.id.choose_address:
                selectAddress();
                break;
            case R.id.commit:
                getUserData();
                break;
        }
    }

    private void getUserData() {
        String realName = name.getText().toString();
        String userIdCard = idCard.getText().toString();
        String detailAdress = addressContent.getText().toString();
        if (Tools.IsEmpty(realName) || StringUtils.isHave(realName)) {
            ToastUtil.showToast("请填写真实姓名!");
            return;
        }
        if (Tools.IsEmpty(userIdCard)) {
            ToastUtil.showToast("请输入身份证号!");
            return;
        }
        if (Tools.IsEmpty(detailAdress)) {
            ToastUtil.showToast("请输入详细地址!");
            detailAdress = address_content+detailAdress;
            return;
        }
        PayFactory.getPayService()
                .certification(realName, userIdCard, province, city, district, detailAdress, "18701546674")
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {
                        ToastUtil.showToast("提交成功");
                        finish();
                    }
                });

    }

    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
//                .titleTextColor("#696969")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province(province)
                .city(city)
                .district(district)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                province = citySelected[0];
                //城市
                city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                district = citySelected[2];
                //邮编
//                pro_code = citySelected[3];

                //为TextView赋值
//                address.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
//
//                code.setText(pro_code + "");
                chooseAddress.setRightString(province.trim() + "-" + city.trim() + "-" + district.trim());
                address_content = province.trim() +  city.trim() + district.trim();
//                addressContent.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }

    @Override
    public void onBackPressed() {
        ToastUtil.showToast("请完善信息");
//        backHistory(1010, true, true, extraMap);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        backHistory(1010, true, true, extraMap);
        ToastUtil.showToast("请完善信息");
        return false;
    }
}
