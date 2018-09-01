package com.xfkc.caimai.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.StatusBarUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.rx.activity.RxActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 添加收货地址  修改收货地址
 */
public class AddAddressActivity extends RxActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar_right_text)
    TextView toolbarRightText;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.shouhuoren_ed)
    EditText shouhuorenEd;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.code)
    EditText code;
    @Bind(R.id.address)
    EditText address;
    @Bind(R.id.address_content)
    EditText addressContent;
//    @Bind(R.id.choose_pro)
//    SuperTextView choosePro;
//    @Bind(R.id.pro_list)
//    CustomListView proList;
//    @Bind(R.id.choose_city)
//    SuperTextView chooseCity;
//    @Bind(R.id.city_list)
//    CustomListView cityList;
    //    @Bind(choose_region)
//    SuperTextView chooseRegion;


    public String province = "北京市";//省份
    public String provinceId;//省份  id
    public String city = "北京市";//城市
    public String cityId;//城市Id
    public String district = "朝阳区";//区县
    public String districtId;//区县Id
    public String pro_code;//邮编
    @Bind(R.id.address_quyu)
    SuperTextView addressQuyu;
    @Bind(R.id.add_tv)
    TextView addTv;
    @Bind(R.id.home)
    TextView home;
    @Bind(R.id.company)
    TextView company;
    @Bind(R.id.school)
    TextView school;
    @Bind(R.id.switch_bt)
    SuperTextView switchBt;
//    @Bind(R.id.choose_region)
//    SuperTextView chooseRegion;
//    @Bind(R.id.region_list)
//    CustomListView regionList;

    private boolean isSwitch = false;//指纹手势开关  默认false

    private int TYPE;// 0 添加收货地址   1修改收货地址

    private ArrayList<TextView> list_location = new ArrayList<>();
    private int location = 0;//0家  1公司  2学校
    private int MOREN = 1; //1默认  2非默认

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        TYPE = getIntent().getIntExtra("type", 0);

        if (TYPE == 0) {
            toolbarTitle.setText("新增收货地址");
        } else if (TYPE == 1) {
            toolbarTitle.setText("编辑收货地址");
        }
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        address.setFocusable(false);

        list_location.add(home);
        list_location.add(company);
        list_location.add(school);


    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
    }


    @OnClick({R.id.toolbar_left_img, R.id.toolbar_right_text,
            R.id.address, R.id.address_quyu, R.id.switch_bt, R.id.quit_btn
            , R.id.home, R.id.company, R.id.school
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.toolbar_right_text:
            case R.id.quit_btn:
                //获取所填写信息
                getEditInfo();
                break;
            case R.id.address_quyu:
                selectAddress();
                break;
            case R.id.switch_bt:
                if (isSwitch) {//关
                    isSwitch = false;
                    MOREN = 2;
                    switchBt.setRightIcon(R.drawable.icon_kaiguan_default);
                } else {//开
                    isSwitch = true;
                    MOREN = 1;
                    switchBt.setRightIcon(R.drawable.icon_kaiguan_pre);
                }
                break;
            case R.id.home:
                location = 0;
                setLocation(0);
                break;
            case R.id.company:
                location = 1;
                setLocation(1);
                break;
            case R.id.school:
                location = 2;
                setLocation(2);
                break;
        }
    }

    private void setLocation(int id) {
        for (int i = 0; i < list_location.size(); i++) {
            if (id == i) {
                list_location.get(i).setBackgroundResource(R.drawable.wallet_tv_bg);
            } else {
                list_location.get(i).setBackgroundResource(R.drawable.order_tv_bg02);
            }
        }
    }



    /*展示区县列表*/
//    private void showChooseRegison() {
//        if (regionList.getVisibility() == View.GONE) {
//            show(chooseRegion, regionList);
//        } else {
//            disshow(chooseRegion, regionList);
//        }
//    }

    /*展示城市选择框*/
//            private void showChooseCity() {
//                if (cityList.getVisibility() == View.GONE) {
//                    show(chooseCity, cityList);
//        } else {
//            disshow(chooseCity, cityList);
//        }
//    }

//    public void show(SuperTextView textView, CustomListView listView) {
//        textView.setRightIcon(R.mipmap.icon_xaila_pre);
//        textView.setLeftTextColor(Color.RED);
//        listView.setVisibility(View.VISIBLE);
//    }

//    public void disshow(SuperTextView textView, CustomListView listView) {
//        textView.setLeftTextColor(Color.parseColor("#333333"));
//        textView.setRightIcon(R.mipmap.icon_xaila_default);
//        listView.setVisibility(View.GONE);
//    }


    /*获取收货地址填写信息*/
    private void getEditInfo() {
        String name = shouhuorenEd.getText().toString();
        String phone_number = phone.getText().toString();
//        String address_code = code.getText().toString();
//        String addr = address.getText().toString();
        String addr_content = addressContent.getText().toString();
        if (Tools.IsEmpty(name)) {
            ToastUtil.showToast("姓名不能为空");
            return;
        }
        if (Tools.IsEmpty(phone_number)) {
            ToastUtil.showToast("手机号不能为空");
            return;
        }
        if (TYPE == 0) {
            addAddress(name, phone_number, addr_content);
        } else if (TYPE == 1) {
//            editAddress(name, phone_number, address_code, addr, addr_content);
        }

    }

    /*编辑收货地址*/
//    private void editAddress(String name, String phone_number, String address_code, String addr, String addr_content) {
//        NewAddAddressId newAddAddressId = new NewAddAddressId();
//        newAddAddressId.token = token;
//        newAddAddressId.userName = name;
//        newAddAddressId.userPhone = phone_number;
//        newAddAddressId.provinceId = provinceId;
//        newAddAddressId.cityId = cityId;
//        newAddAddressId.regionId = districtId;
//        newAddAddressId.postCode = address_code;
//        newAddAddressId.usAddress = addr_content;
//        newAddAddressId.isDefault = "1";
//        newAddAddressId.addressId = addressId;
//        PayFactory.getPayService().updateWstAddress(newAddAddressId)
//                .compose(RxHelper.<EmptyBean>io_main())
//                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
//                    @Override
//                    public void onNext(EmptyBean emptyBean) {
//                        ToastUtil.showToast("修改成功");
//                        backHistory(10002, true, true, extraMap);
//                    }
//                });
//    }

    /*新增收货地址*/
    private void addAddress(String name, String phone_number, String addr_content) {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);

        PayFactory.getPayService()
                .AddWstAddress(name, phone_number, token, province, city, district, addr_content, MOREN)
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {
                        ToastUtil.showToast("添加成功");
//                        finish();
                        backHistory(10002, true, true, extraMap);
                    }
                });
    }

    //
    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(AddAddressActivity.this)
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
                pro_code = citySelected[3];

                //为TextView赋值
//                address.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
//
//                code.setText(pro_code + "");

                addressContent.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }

}
