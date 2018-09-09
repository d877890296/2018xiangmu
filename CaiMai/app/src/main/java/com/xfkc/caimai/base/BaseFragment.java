package com.xfkc.caimai.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.mbprogress.MbProgress;
import com.xfkc.caimai.MainActivity;
import com.xfkc.caimai.R;
import com.xfkc.caimai.application.MyApplication;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;


/**
 * Created by LK on 2016/3/7 15:08
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;
    public MyApplication app;
    public String userId, userCode, userName;
    public View rootView;
      public MainActivity mainActivity;
    //传值
    public Map<String, Object> extraMap;
    public Map<String, String> sExtraMap;
    //    public String token;
    public int start = 0, pageSize = 20;
    public int baseType;
    public boolean  isMoreData;

    public LinearLayout progress_liner;
    public TextView nodataview_textview;
    public MbProgress mbProgress;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultDataInit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(rootView==null) {
           rootView = inflater.inflate(getLayoutResource(), container, false);
       }
        ButterKnife.bind(this, rootView);
        initData();
        return rootView;
    }

    private void defaultDataInit() {
        mContext = getActivity();
    if (getActivity().getClass().equals(MainActivity.class)) {
      mainActivity = (MainActivity) getActivity();
    }

        //获取用户登录 token值
//        token = SharedPrefUtil.get(menuActivity, SharedPref.TOKEN);
        app = MyApplication.getInstance();
        extraMap = new HashMap<String, Object>();
        sExtraMap = new HashMap<String, String>();


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected abstract int getLayoutResource();

    protected abstract void initData();

    /***
     *
     * 类的跳转
     *
     * @param
     * @param who
     * @param extraMap
     * @param isFinish
     */
    public void skip_classView(Class<?> who, Map<String, Object> extraMap, boolean isFinish, boolean animation) {
        Intent intent = new Intent();
        intent.setClass(mContext, who);
        putExtra(intent, extraMap);
        startActivity(intent);
        if (isFinish == true) {
            getActivity().finish();
        }
        if (animation == true) {
            getActivity().overridePendingTransition(R.anim.alpha_have_no, R.anim.alpha_no_have);
        }
        // overridePendingTransition(R.anim.translate_top,
        // R.anim.translate_top_exit);
    }

    /***
     *
     * 类的跳转
     *
     * @param who
     */
    public void skip_classView(Class<?> who, Map<String, Object> extraMap, boolean isFinish, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), who);
        putExtra(intent, extraMap);
        startActivityForResult(intent, requestCode);
        if (isFinish == true) {
            getActivity().finish();
        }
        getActivity().overridePendingTransition(R.anim.alpha_have_no, R.anim.alpha_no_have);
        // overridePendingTransition(R.anim.translate_top,
        // R.anim.translate_top_exit);
    }

    /***
     * 压值
     *
     * @param intent
     * @param extraMap
     */
    public void putExtra(Intent intent, Map<String, Object> extraMap) {
        for (Map.Entry<String, Object> entry : extraMap.entrySet()) {
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            intent.putExtra(key, value);

        }
    }


    public void showMbProgress(String msg) {
        if (mbProgress == null) {
            mbProgress = new MbProgress(getContext(), false);
        }
        mbProgress.showMbDialog(msg);
    }

    public void dissMbProgress() {
        if (mbProgress != null) {
            mbProgress.dismiss();
        }

    }

    /** list刷新 **/
    public static final int LIST_REFUSH_WHAT = 0;
    /** list加载更多数据 **/
    public static final int LIST_LOADMORE_WHAT = 1;
    /** 显示底部bar **/
    public static final int SHOW_BOTTOM_BAR = 200;
    /** 隐藏底部bar **/
    public static final int HIDE_BOTTOM_BAR = 201;
    public static final int PHOTOZOOM = 0; // 相册/拍照

}
