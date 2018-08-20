package com.xfkc.caimai.order;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.dev.customview.CustomListView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.StatusBarUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.xfkc.caimai.R;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.rx.activity.RxActivity;

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
    @Bind(R.id.choose_pro)
    SuperTextView choosePro;
    @Bind(R.id.pro_list)
    CustomListView proList;
    @Bind(R.id.choose_city)
    SuperTextView chooseCity;
    @Bind(R.id.city_list)
    CustomListView cityList;
    //    @Bind(choose_region)
//    SuperTextView chooseRegion;
    @Bind(R.id.region_list)
    CustomListView regionList;


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

    private boolean isSwitch = false;//指纹手势开关  默认false

    private int TYPE;// 0 添加收货地址   1修改收货地址
//    private ChinaBean foodCategoryBean;
//    private ProListAdapter proListAdapter;
//    private CityListAdapter cityListAdapter;
//    private RegionListAdapter regionListAdapter;
//    private ArrayList<ChinaBean.ProvListBean> proListInfo = new ArrayList();
//    private ArrayList<ChinaBean.ProvListBean.CityListBean> cityListInfo = new ArrayList();
//    private ArrayList<ChinaBean.ProvListBean.CityListBean.RegionListBean> regionListInfo = new ArrayList();

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
//            setAddressInfo();//设置编辑收货地址信息
        }
        toolbarTitle.setTextColor(Color.BLACK);
        toolbarLeftImg.setImageResource(R.mipmap.back_white);
//        toolbarRightText.setVisibility(View.VISIBLE);
//        toolbarRightText.setText("保存");
//        toolbarRightText.setTextColor(Color.RED);

        address.setFocusable(false);

        //解析地址本地数据
//        getLocalData();
        //设置省份数据
//        setProData();

    }

//    /*设置省份数据*/
//    private void setProData() {
//        proListAdapter = new ProListAdapter(this);
//        proListAdapter.setData(proListInfo);
//        proList.setAdapter(proListAdapter);
//
//        proList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                position = position - 1;
//                ChinaBean.ProvListBean pro = proListInfo.get(position);
//
//                province = pro.provName;
//                provinceId = pro.provId;
//
////                address.setText(province);
//                choosePro.setLeftString(province);
//                cityList.setVisibility(View.GONE);
//                proList.setVisibility(View.GONE);
//                regionList.setVisibility(View.GONE);
//                disshow(choosePro, proList);
//                //设置  城市列表数据
//                setCityData(pro.cityList);
//
//            }
//        });
//    }
//
//    /*设置城市列表数据*/
//    private void setCityData(List<ChinaBean.ProvListBean.CityListBean> List) {
//        cityListInfo.clear();
//        cityListInfo.addAll(List);
//        cityListAdapter = new CityListAdapter(this);
//        cityListAdapter.setData(cityListInfo);
//        cityList.setAdapter(cityListAdapter);
//
//        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                position = position - 1;
//                ChinaBean.ProvListBean.CityListBean cityBean = cityListInfo.get(position);
//                city = cityBean.cityName;
//                cityId = cityBean.cityId;
//                cityList.setVisibility(View.GONE);
//                proList.setVisibility(View.GONE);
//                regionList.setVisibility(View.GONE);
//                disshow(chooseCity, cityList);
////                address.setText(province + "-" + city);
//                chooseCity.setLeftString(city);
//                //设置 区县数据
//                setRegionData(cityBean.regionList);
//            }
//        });
//    }
//
//    /*设置区县数据*/
//    private void setRegionData(List<ChinaBean.ProvListBean.CityListBean.RegionListBean> list) {
//        regionListInfo.clear();
//        regionListInfo.addAll(list);
//        regionListAdapter = new RegionListAdapter(this);
//        regionListAdapter.setData(regionListInfo);
//        regionList.setAdapter(regionListAdapter);
//
//        regionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                position = position - 1;
//                ChinaBean.ProvListBean.CityListBean.RegionListBean regison = regionListInfo.get(position);
//                district = regison.regionName;
//                districtId = regison.regionId;
//                cityList.setVisibility(View.GONE);
//                proList.setVisibility(View.GONE);
//                regionList.setVisibility(View.GONE);
//                disshow(chooseRegion, regionList);
////                address.setText(province + "-" + city + "-" + district);
//                chooseRegion.setLeftString(district);
//            }
//        });
//    }
//
//    /*解析本地  地址数据*/
//    private void getLocalData() {
//        //得到本地json文本内容
//        String fileName = "chinaJSON.json";
//        String foodJson = LocalJsonResolutionUtils.getJson(this, fileName);
//        //转换为对象
//        foodCategoryBean = LocalJsonResolutionUtils.JsonToObject(foodJson, ChinaBean.class);
//
//        proListInfo.addAll(foodCategoryBean.provList);
//    }

    /*设置编辑收货地址信息*/
    private String addressId;

//    private void setAddressInfo() {
//        addressId = getIntent().getStringExtra("addressId");
//        String userName = getIntent().getStringExtra("userName");
//        String userPhone = getIntent().getStringExtra("userPhone");
//        String usAddress = getIntent().getStringExtra("usAddress");
//        String postCode = getIntent().getStringExtra("postCode");
//
//        shouhuorenEd.setText(userName);
//        phone.setText(userPhone);
//        if (Tools.IsEmpty(postCode)) {
//            postCode = "";
//        }
//        code.setText(postCode);
//        String[] strs = usAddress.split(" ");
////        address.setText(strs[0] + "-" + strs[1] + "-" + strs[2]);
//        choosePro.setLeftString(strs[0]);
//        chooseCity.setLeftString(strs[1]);
//        chooseRegion.setLeftString(strs[2]);
//        String str = "";
//        for (int i = 3; i < strs.length; i++) {
//            str += strs[i];
//        }
//        addressContent.setText(str);
//        province = strs[0];//省份
//        city = strs[1];//城市
//        district = strs[2];//区县
//    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
    }


    @OnClick({R.id.toolbar_left_img, R.id.toolbar_right_text,
            R.id.address, R.id.choose_pro, R.id.choose_city,R.id.address_quyu, R.id.switch_bt, R.id.quit_btn
//            choose_region
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
//            case R.id.address:
//                //判断输入法的隐藏状态
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (imm.isActive()) {
//                    imm.hideSoftInputFromWindow(view.getWindowToken(),
//                            InputMethodManager.HIDE_NOT_ALWAYS);
//                    selectAddress();//调用CityPicker选取区域
//                }
//                break;
//            case R.id.choose_pro://选择省份
//                if (proList.getVisibility() == View.GONE) {
//                    show(choosePro, proList);
//                } else {
//                    disshow(choosePro, proList);
//                }
//                break;
//            case R.id.choose_city:
//                if (cityListInfo.size() != 0) {
//                    showChooseCity();//展示城市选择框
//                } else {
//                    ToastUtil.showToast("请选择省份");
//                }
//                break;
//            case choose_region:
//                if (regionListInfo.size() != 0) {
//                    showChooseRegison();//展示区县选择框
//                } else {
//                    ToastUtil.showToast("请选择城市");
//                }
//                break;
            case R.id.address_quyu:
                selectAddress();
                break;
            case R.id.switch_bt:
                if (isSwitch) {//关
                    isSwitch = false;
                    switchBt.setRightIcon(R.drawable.icon_kaiguan_default);
                } else {//开
                    isSwitch = true;
                    switchBt.setRightIcon(R.drawable.icon_kaiguan_pre);
                }
                break;

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
        String address_code = code.getText().toString();
        String addr = address.getText().toString();
        String addr_content = addressContent.getText().toString();
        if (Tools.IsEmpty(name)) {
            ToastUtil.showToast("姓名不能为空");
            return;
        }
        if (Tools.IsEmpty(phone_number)) {
            ToastUtil.showToast("手机号不能为空");
            return;
        }
        if (Tools.IsEmpty(address_code)) {
            ToastUtil.showToast("邮编不能为空");
            return;
        }
        if (Tools.IsEmpty(provinceId)) {
            ToastUtil.showToast("请选择省份");
            return;
        }
        if (Tools.IsEmpty(cityId)) {
            ToastUtil.showToast("请选择城市");
            return;
        }
        if (Tools.IsEmpty(districtId)) {
            ToastUtil.showToast("请选择区县");
            return;
        }
        if (Tools.IsEmpty(addr_content)) {
            ToastUtil.showToast("请填写详细收货地址");
            return;
        }
//        if (TYPE == 0) {
//            addAddress(name, phone_number, address_code, addr, addr_content);
//        } else if (TYPE == 1) {
//            editAddress(name, phone_number, address_code, addr, addr_content);
//        }

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
//    private void addAddress(String name, String phone_number, String address_code, String addr, String addr_content) {
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
//        PayFactory.getPayService().AddWstAddress(newAddAddressId)
//                .compose(RxHelper.<EmptyBean>io_main())
//                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
//                    @Override
//                    public void onNext(EmptyBean emptyBean) {
//                        ToastUtil.showToast("添加成功");
//                        backHistory(10002, true, true, extraMap);
//                    }
//                });
//    }
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
