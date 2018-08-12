package com.xfkc.caimai.welcmoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.hyf.tdlibrary.utils.AppUtil;
import com.hyf.tdlibrary.utils.SharedPrefUtil;
import com.xfkc.caimai.R;
import com.xfkc.caimai.config.SharedPref;
import com.xfkc.caimai.loading.LoadingActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.button)
    Button button;

    private List<ImageView> imageViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
//        StatusBarUtil.setTransparent(this);
        initData();
        GuideAdapter adapter = new GuideAdapter();
        viewPager.setAdapter(adapter);

        setViewpagerClick();
        //隐藏状态栏
//        StatusBarUtil.hideStatus(this);
    }

    /**
     * 设置Viewpager  轮播监听
     */
    private void setViewpagerClick() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==(imageViewList.size()-1)){
                    button.setVisibility(View.VISIBLE);
                }else {
                    button.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化ViewPager数据
     */
    private void initData() {
        int[] imageResIDs = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        imageViewList = new ArrayList<>();

        ImageView iv;// 图片

        for (int i = 0; i < imageResIDs.length; i++) {
            iv = new ImageView(this);
            iv.setBackgroundResource(imageResIDs[i]);
            imageViewList.add(iv);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        int code = AppUtil.vercode();
        SharedPrefUtil.put(this, SharedPref.GUIDE_GLAG, (code + 1));
        startActivity(new Intent(this, LoadingActivity.class));
        finish();
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        /*
         * 删除元素
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = imageViewList.get(position);
            container.addView(iv);// 1. 向ViewPager中添加一个view对象
            return iv; // 2. 返回当前添加的view对象
        }
    }

}
