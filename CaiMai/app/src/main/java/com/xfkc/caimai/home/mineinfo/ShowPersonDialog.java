package com.xfkc.caimai.home.mineinfo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.xfkc.caimai.R;


/**
 * 1.个人信息 弹框
 * 3.@2018/5/10.
 */

public class ShowPersonDialog  {

    private final Context context;
    private MineInfoActivity personActivity;

    public ShowPersonDialog(Context context) {
        this.context = context;
    }

    public void setActivity(MineInfoActivity personInfoActivity) {
        this.personActivity = personInfoActivity;
    }

    /*添加头像展示框*/
    public void showAddImageDialog() {
        final Dialog dialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.choose_camera_dialog, null);
        TextView horizontal = (TextView) contentView.findViewById(R.id.choose_camera_horizontal);
        TextView vertical = (TextView) contentView.findViewById(R.id.choose_camera_vertical);
        TextView finish = (TextView) contentView.findViewById(R.id.choose_camera_finish);
        horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personActivity.chooseImages();
                dialog.dismiss();
            }
        });
        vertical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personActivity.takeCarema();
                dialog.dismiss();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        dialog.show();
    }


    /**
     * 选择性别
     */
    private AlertDialog sex_dialog = null;

//    public void ShowChooseSexDialog() {
//        View view = View.inflate(context, R.layout.person_choosesex_dialog, null);
//        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setCancelable(false);
//        // 设置Title的内容
////        builder.setTitle("请选择取消订单的原因");
//        // 设置Content来显示一个信息
//        builder.setView(view);
//
//        sex_dialog = builder.create();
//
//        SuperTextView boy_bt = (SuperTextView) view.findViewById(R.id.boy_bt);
//        SuperTextView gril_bt = (SuperTextView) view.findViewById(R.id.gril_bt);
//        SuperTextView baomi_tv = (SuperTextView) view.findViewById(R.id.baomi_tv);
//
//        boy_bt.setOnClickListener(this);
//        gril_bt.setOnClickListener(this);
//        baomi_tv.setOnClickListener(this);
//
//        // 显示出该对话框
////        builder.show();
//        sex_dialog.show();
//    }


    /***
     * 修改昵称
     */
    public void updateName(String rightString) {
        View view = View.inflate(context, R.layout.update_name_dialog, null);
        // 通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        // 设置Title的内容
//        builder.setTitle("请选择取消订单的原因");
        // 设置Content来显示一个信息
        builder.setView(view);

        final AlertDialog update_name_dialog = builder.create();

        final EditText name= (EditText) view.findViewById(R.id.name);
        TextView commit= (TextView) view.findViewById(R.id.commit);

        name.setText(rightString);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String update_name=name.getText().toString();
                if (Tools.IsEmpty(update_name)){
                    ToastUtil.showToast("请输入昵称");
                    return;
                }
                personActivity.setUpdate(update_name);
                update_name_dialog.dismiss();
            }
        });

        // 显示出该对话框
//        builder.show();
        update_name_dialog.show();
    }


//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case boy_bt:
//                personActivity.setSex(0);
//                sex_dialog.dismiss();
//                break;
//            case gril_bt:
//                personActivity.setSex(1);
//                sex_dialog.dismiss();
//                break;
//            case baomi_tv:
//                personActivity.setSex(2);
//                sex_dialog.dismiss();
//                break;
//        }
//    }


}
