package com.xfkc.caimai.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by 10835 on 2018/8/30.
 */

public abstract class LzBaseAdapter extends BaseAdapter {
    public Context context;
    public LayoutInflater inflater;
    public LzBaseAdapter(Context context) {
        this.context=context;
        inflater=	LayoutInflater.from(context);
        // TODO Auto-generated constructor stub
    }

}
