package com.xfkc.caimai;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.goods.netrequst.DefaultRequstLocation;
import com.goods.sortlsitview.SortModel;
import com.hyf.tdlibrary.utils.ActivityUtil;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.hyf.tdlibrary.utils.ToastUtil;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.home.fragment.BigLectureHallFragment;
import com.xfkc.caimai.home.fragment.FeelingFragment;
import com.xfkc.caimai.home.fragment.HomeFragment;
import com.xfkc.caimai.home.fragment.SocialCentreFragment;
import com.xfkc.caimai.rx.activity.RxActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends RxActivity {


    @Bind(R.id.tabcontent)
    FrameLayout tabcontent;
    @Bind(android.R.id.tabhost)
    FragmentTabHost mTabHost;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
//    public AMapLocationListener mLocationListener = new AMapLocationListener();
    /***第一次进入商店定位并获取当前商品信息*/
    public DefaultRequstLocation defaultRequstLocation;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        token = SharedPrefUtil.get(mContext, SharedPref.TOKEN);
        initTabHost();

        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = null;
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissions = new ArrayList<>();
                permissions.add(Manifest.permission.CAMERA);
            }
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (permissions == null) {
                    permissions = new ArrayList<>();
                }
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permissions != null) {
                String[] permissionArray = new String[permissions.size()];
                permissions.toArray(permissionArray);
                requestPermissions(permissionArray, 5);
            }
        }

        setLocation();

    }


    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    /*设置定位*/
    private void setLocation() {
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
        //初始化获取商店信息
        defaultRequstLocation=new DefaultRequstLocation(mContext);
        defaultRequstLocation.setOnLocationCallBack(onLocationCallBack);

    }
private DefaultRequstLocation.OnLocationCallBack onLocationCallBack=new DefaultRequstLocation.OnLocationCallBack(){
    @Override
    public void locationCallBack(boolean isSuccess, String errorMsg, SortModel object) {
        if (isSuccess){
            app.shopModel=object;
        }
    }
};
    //可以通过类implement方式实现AMapLocationListener接口，也可以通过创造接口类对象的方法实现
//以下为后者的举例：
    AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    //获取纬度
                    //获取经度
                    app.latitude = amapLocation.getLatitude()+"";
                    app.longitude ="" +amapLocation.getLongitude();
                    //开始请求商店信息
                    defaultRequstLocation.startLocation( app.longitude , app.latitude);
                    mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                    mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + amapLocation.getErrorCode() + ", errInfo:"
                            + amapLocation.getErrorInfo());
                }
            }
        }
    };

    @Override
    protected void loadData() {

    }

    // 设置状态栏颜色
    @Override
    protected void setStatusBar() {
//        StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.red));
//        StatusBarUtil.setTransparent(this);
    }


    //设置底部导航栏
    private void initTabHost() {

        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        MenuTab[] tabs = MenuTab.values();
        final int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MenuTab tab = tabs[i];
            TabHost.TabSpec spec = mTabHost.newTabSpec(tab.getResName());
            spec.setIndicator(getTabItemView(tab));
            spec.setContent(new TabHost.TabContentFactory() {
                @Override
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });
            mTabHost.addTab(spec, tab.getClz(), null);

        }
        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(new TDOnTabChangeListener());


    }

    //切换底部导航监听
    class TDOnTabChangeListener implements TabHost.OnTabChangeListener {

        @Override
        public void onTabChanged(String tabId) {
            final int size = mTabHost.getTabWidget().getTabCount();
            for (int i = 0; i < size; i++) {
                View view = mTabHost.getTabWidget().getChildAt(i);
                if (i == mTabHost.getCurrentTab()) {
                    view.setSelected(true);
                } else {
                    view.setSelected(false);
                }
            }
            supportInvalidateOptionsMenu();
        }
    }


    /**
     * 给Tab按钮设置图片和文字
     *
     * @param tab
     * @return
     */
    private View getTabItemView(MenuTab tab) {
        View indicator = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab_indicator, null);
        TextView titleText = (TextView) indicator.findViewById(R.id.tab_title);
        Drawable drawable = mContext.getResources().getDrawable(tab.getResIcon());
        titleText.setText(tab.getResName());
        titleText.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        return indicator;
    }

    public enum MenuTab {

        TAB_ONE(0, "大仓库", R.drawable.tab_icon_one, HomeFragment.class),

        TAB_TWO(1, "大讲堂", R.drawable.tab_icon_two, BigLectureHallFragment.class),

        TAB_THREE(2, "情怀链", R.drawable.tab_icon_three, FeelingFragment.class),

        TAB_FOUR(3, "社员中心", R.drawable.tab_icon_four, SocialCentreFragment.class);

        private int idx;
        private String resName;
        private int resIcon;
        private Class<?> clz;

        private MenuTab(int idx, String resName, int resIcon, Class<?> clz) {
            this.idx = idx;
            this.resName = resName;
            this.resIcon = resIcon;
            this.clz = clz;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public String getResName() {
            return resName;
        }

        public void setResName(String resName) {
            this.resName = resName;
        }

        public int getResIcon() {
            return resIcon;
        }

        public void setResIcon(int resIcon) {
            this.resIcon = resIcon;
        }

        public Class<?> getClz() {
            return clz;
        }

        public void setClz(Class<?> clz) {
            this.clz = clz;
        }
    }


    @Override
    public void onBackPressed() {
        if (ActivityUtil.exitTwice()) {
            super.onBackPressed();
        } else {
            ToastUtil.showToast("再按一次退出程序");
        }

    }


}
