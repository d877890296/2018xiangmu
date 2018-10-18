/**
 * 功能：工具类
 * 类名：Utils.java
 * 日期：2013-11-26
 * 作者：lukejun
 */
package com.xfkc.caimai.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.AppOpsManagerCompat;
import android.view.View;

import com.xfkc.caimai.dialog.CommonDialog;

import java.lang.reflect.Method;

/**
 * @author lukejun
 * @ClassName: Utils
 * @Description: 工具类
 * @date 2013-11-26 下午4:36:05
 */
public class LocationUtils {
    /**
     * 手机是否开启位置服务，如果没有开启那么所有app将不能使用定位功能
     */
    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    /**
     * 检查权限列表
     *
     * @param context
     * @param op       这个值被hide了，去AppOpsManager类源码找，如位置权限  AppOpsManager.OP_GPS==2
     * @param opString 如判断定位权限 AppOpsManager.OPSTR_FINE_LOCATION
     * @return @see 如果返回值 AppOpsManagerCompat.MODE_IGNORED 表示被禁用了
     */
    public static int checkOp(Context context, int op, String opString) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            Object object = context.getSystemService(Context.APP_OPS_SERVICE);
//            Object object = context.getSystemService("appops");
            Class c = object.getClass();
            try {
                Class[] cArg = new Class[3];
                cArg[0] = int.class;
                cArg[1] = int.class;
                cArg[2] = String.class;
                Method lMethod = c.getDeclaredMethod("checkOp", cArg);
                return (Integer) lMethod.invoke(object, op, Binder.getCallingUid(), context.getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
                if (Build.VERSION.SDK_INT >= 23) {
                    return AppOpsManagerCompat.noteOp(context, opString, context.getApplicationInfo().uid,
                            context.getPackageName());
                }

            }
        }
        return -1;
    }

    /**
     * 无法定位对话框
     *
     * @param
     * @param state    权限状态0，未开启服务 1，未开启权限
     * @return 对话框
     */
    public static CommonDialog showLocErrorDialog(final Activity mContext, final int state) {

        CommonDialog commonDialog = new CommonDialog(mContext);
        commonDialog.builder().setTitle("提示")
                .setContentMsg("定位失败，请检查!")
                .setCanceledOnTouchOutside(false)
                .setPositiveBtn("检查网络", new CommonDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view) {

                    }
                })
                .setNegativeBtn("打开定位", new CommonDialog.OnNegativeListener() {
                    @Override
                    public void onNegative(View view) {
                            Intent intent = new Intent();
                            if (state == 0) {
                                //定位服务页面
                                intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            } else {
                                //应用详情页面
                                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                intent.setData(Uri.parse("package:" + mContext.getPackageName()));
                            }
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            try {
                                mContext.startActivity(intent);
                            } catch (ActivityNotFoundException ex) {
                                //如果页面无法打开，进入设置页面
                                intent.setAction(Settings.ACTION_SETTINGS);
                                try {
                                    mContext.startActivity(intent);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        mContext.finish();
                    }
                })
                .show();
        return commonDialog;
    }

    /**
     * 检查定位服务、权限
     */
//    private void checkLocationPermission() {
//        if (!LocationUtils.isLocServiceEnable(mContext)) {//检测是否开启定位服务
//            LocationUtils.showLocErrorDialog(mContext, 0);
//        } else {//检测用户是否将当前应用的定位权限拒绝
//            int checkResult = LocationUtils.checkOp(mContext,2, AppOpsManager.OPSTR_FINE_LOCATION);//其中2代表AppOpsManager.OP_GPS，如果要判断悬浮框权限，第二个参数需换成24即AppOpsManager。OP_SYSTEM_ALERT_WINDOW及，第三个参数需要换成AppOpsManager.OPSTR_SYSTEM_ALERT_WINDOW
//            int checkResult2 = LocationUtils.checkOp(mContext,1, AppOpsManager.OPSTR_FINE_LOCATION);
//            if (AppOpsManagerCompat.MODE_IGNORED == checkResult || AppOpsManagerCompat.MODE_IGNORED == checkResult2) {
//                LocationUtils.showLocErrorDialog(mContext, 1);
//            }else {
//                skip_classView(GoodsCityActivity.class, extraMap, false, false);
//            }
//        }
    }

