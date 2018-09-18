package com.xfkc.caimai.order;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.StatusBarUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.bean.AddressBean;
import com.xfkc.caimai.bean.EmptyBean;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.customview.StateButton;
import com.xfkc.caimai.net.PayFactory;
import com.xfkc.caimai.net.RxHelper;
import com.xfkc.caimai.net.subscriber.ProgressSubscriber;
import com.xfkc.caimai.order.adapter.AddAddressListAdapter;
import com.xfkc.caimai.rx.activity.RxActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 选择收货地址
 */
public class ChooseAddressActivity extends RxActivity {


    @Bind(R.id.toolbar_left_img)
    ImageView toolbarLeftImg;
    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    //    @Bind(R.id.choose_address_listview)
//    CustomListView chooseAddressListview;
    @Bind(R.id.add_address_btn)
    StateButton addAddressBtn;
    @Bind(R.id.choose_address_listview)
    SwipeMenuListView chooseAddressListview;

    private AddAddressListAdapter addressListAdapter;

    //地址集合
    private ArrayList<AddressBean.DataBean> list_address = new ArrayList();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_choose_address;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        boolean isShow = getIntent().getBooleanExtra("isShow", false);

        toolbarTitle.setText("选择收货地址");
        toolbarLeftImg.setImageResource(R.mipmap.back_white);

        addressListAdapter = new AddAddressListAdapter(this);
        addressListAdapter.setData(list_address);
        chooseAddressListview.setAdapter(addressListAdapter);

        setlistClick();
        setinitList();
    }

    private void setlistClick() {

        chooseAddressListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AddressBean.DataBean dataBean = list_address.get(position);
                extraMap.put("name", dataBean.receiveName);
                extraMap.put("phone", dataBean.phone);
                extraMap.put("address", dataBean.detailAdress);
                extraMap.put("addressId", dataBean.id);
                extraMap.put("province", dataBean.province);
                backHistory(1005, true, true, extraMap);
            }
        });
    }

    @Override
    protected void loadData() {
        if (list_address.size() != 0) {
            list_address.clear();
        }
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        PayFactory.getPayService().getReceiveAdress(token)
                .compose(RxHelper.<AddressBean>io_main())
                .subscribe(new ProgressSubscriber<AddressBean>(this) {
                    @Override
                    public void onNext(AddressBean addressBean) {
                        if (addressBean.data != null && addressBean.data.size() != 0) {
                            list_address.addAll(addressBean.data);
                            addressListAdapter.setData(list_address);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (list_address.size() != 0) {
                            list_address.clear();
                        }
                        addressListAdapter.setData(list_address);
                    }
                });


    }

    /*设置地址集合*/
//    private void setListData(List<AddressListBean.AddressBean> addressList) {
//        list_address.clear();
//        for (int i = 0; i < addressList.size(); i++) {
//            if (addressList.get(i).isDefault.equals("0")) {
//                addressList.get(i).setMoRen(true);
//            } else {
//                addressList.get(i).setMoRen(false);
//            }
//            list_address.add(addressList.get(i));
//        }
//        addressListAdapter.setData(list_address);
//        chooseAddressListview.setAdapter(addressListAdapter);
//    }

    @Override
    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.white));
    }

    @OnClick({R.id.toolbar_left_img, R.id.add_address_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_left_img:
                finish();
                break;
            case R.id.add_address_btn:
                startActivityForResult(new Intent(this, AddAddressActivity.class).putExtra("type", 0), 10001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10001 && resultCode == 10002) {
            loadData();
        }

    }

    /*修改默认地址*/
    public void updateDefault(final int position) {
//        GetAllId getAllId = new GetAllId();
//        getAllId.setToken(token);
//        getAllId.addressId = list_address.get(position).addressId+"";
//        PayFactory.getPayService().updateDefault(getAllId)
//                .compose(RxHelper.<EmptyBean>io_main())
//                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
//                    @Override
//                    public void onNext(EmptyBean emptyBean) {
//                        for (int i=0;i<list_address.size();i++){
//                            if (i==position){
//                                list_address.get(i).setMoRen(true);
//                            }else {
//                                list_address.get(i).setMoRen(false);
//                            }
//                        }
//                        ToastUtil.showToast("默认地址设置成功");
//                        addressListAdapter.notifyDataSetChanged();
//                    }
//                });
    }

    /*设置侧滑功能*/
    private void setinitList() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem openItem = new SwipeMenuItem(mContext);
                openItem.setBackground(new ColorDrawable(Color.RED));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("删除");
                openItem.setTitleSize(20);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);

            }
        };

        chooseAddressListview.setMenuCreator(creator);

        chooseAddressListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
                //从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0:
//                        Toast.makeText(mContext, "删除:"+position,Toast.LENGTH_SHORT).show();

                        delete(list_address.get(position).id);
                        break;
                }

                // false : 当用户触发其他地方的屏幕时候，自动收起菜单。
                // true : 不改变已经打开菜单的样式，保持原样不收起。
                return false;
            }
        });

        // 监测用户在ListView的SwipeMenu侧滑事件。
        chooseAddressListview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int pos) {
                Log.d("位置:" + pos, "开始侧滑...");
            }

            @Override
            public void onSwipeEnd(int pos) {
                Log.d("位置:" + pos, "侧滑结束.");
            }
        });

    }

    private void delete(int id) {
        PayFactory.getPayService().rmReceiveAdress(token,id+"")
                .compose(RxHelper.<EmptyBean>io_main())
                .subscribe(new ProgressSubscriber<EmptyBean>(this) {
                    @Override
                    public void onNext(EmptyBean emptyBean) {
                        ToastUtil.showToast("删除成功");
                        loadData();
                    }
                });

    }

    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
