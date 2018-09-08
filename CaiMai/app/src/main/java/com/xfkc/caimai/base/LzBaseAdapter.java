package com.xfkc.caimai.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xfkc.caimai.application.MyApplication;

/**
 * Created by 10835 on 2018/8/30.
 */

public abstract class LzBaseAdapter extends BaseAdapter {
    public Context context;
    public LayoutInflater inflater;
    public MyApplication app;
    public LzBaseAdapter(Context context) {
        this.context=context;
        inflater=	LayoutInflater.from(context);
        app=MyApplication.getInstance();
        // TODO Auto-generated constructor stub
    }

}
