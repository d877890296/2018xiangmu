package com.xfkc.caimai;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by huangrui on 2017/11/5.
 */

public class PdfAndWorldUtils {


    private static String TAG = "huangrui";
    public static void downLoadFromNet(String mUrl,final String fileName, final String fileType,final Context context) {
        //下载文件
        File cacheFile = getCacheFile(fileName);
        if (cacheFile.exists()) {
            if (cacheFile.length() <= 0) {
                cacheFile.delete();
            }
            if ("pdf".equals(fileType)) {
                try {
                    Intent intent = PdfAndWorldUtils.getPdfFileIntent(fileName,context);
                    context.startActivity(intent);
                }catch (Exception e){
                    ToastUtil.showToast("没有支持PDF文件打开的应用");
                }
            }else{
                try {
                    Intent intent = PdfAndWorldUtils.getWordFileIntent(fileName,context);
                    context.startActivity(intent);
                }catch (Exception e){
                    ToastUtil.showToast("没有支持World文件打开的应用");
                }
            }
        }else{
            final ToastDialog mToastDialog = new ToastDialog(context);
            mToastDialog.setMsg("正在下载文件,请稍后...");
            mToastDialog.show();

            ApiManager.getInstance().loadPdfFile(mUrl, new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    L.d(TAG, "下载文件-->onResponse");
                    boolean flag;
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try {
                        ResponseBody responseBody = response.body();
                        is = responseBody.byteStream();
                        long total = responseBody.contentLength();
                        File file1 = getCacheDir();
                        if (!file1.exists()) {
                            file1.mkdirs();
                            Log.d(TAG, "创建缓存目录： " + file1.toString());
                        }
                        File      fileN = getCacheFile(fileName);
                        Log.d(TAG, "创建缓存文件： " + fileN.toString());
                        if (!fileN.exists()) {
                            boolean mkdir = fileN.createNewFile();
                        }
                        fos = new FileOutputStream(fileN);
                        long sum = 0;
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                            sum += len;
                            int progress = (int) (sum * 1.0f / total * 100);
                            Log.d(TAG, "写入缓存文件" + fileN.getName() + "进度: " + progress);
                        }
                        fos.flush();
                        mToastDialog.setMsg("文件下载成功");
                        mToastDialog.dismiss();
                        Log.d(TAG, "文件下载成功,准备展示文件。");

                        //调用pdfhttp://blog.csdn.net/lizhenmingdirk/article/details/53691407
                        //7.0以上 http://blog.csdn.net/yy1300326388/article/details/52787853
                        //路径设置:http://www.jianshu.com/p/55b817530fa3

                      if ("pdf".equals(fileType)) {
                          try {
                              Intent intent = PdfAndWorldUtils.getPdfFileIntent(fileName,context);
                              context.startActivity(intent);
                          }catch (Exception e){
                              ToastUtil.showToast("没有支持PDF文件打开的应用");
                          }
                      }else{
                          try {
                              Intent intent = PdfAndWorldUtils.getWordFileIntent(fileName,context);
                              context.startActivity(intent);
                          }catch (Exception e){
                              ToastUtil.showToast("没有支持World文件打开的应用");
                          }
                      }
                    } catch (Exception e) {
                        mToastDialog.setMsg("文件下载异常，请检查网络");
                        mToastDialog.dismiss();
                        Log.d(TAG, "文件下载异常 = " + e.toString());
                    } finally {
                        try {
                            if (is != null)
                                is.close();
                        } catch (IOException e) {
                        }
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    mToastDialog.dismiss();
                    mToastDialog.setMsg("文件下载失败，请检查网络");
                    File file = getCacheFile(fileName);
                    if (!file.exists()) {
                        file.delete();
                    }
                }
            });
        }
    }


    /***
     * 获取缓存目录
     */
    private static File getCacheDir() {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xfkc/");
    }



    /***
     * 绝对路径获取缓存文件
     */
    private  static File getCacheFile(String fileName) {
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xfkc/"
                + fileName);
        Log.d(TAG, "缓存文件 = " + cacheFile.toString());
        return cacheFile;
    }



    public static Intent getPdfFileIntent( String fileName,Context context )
    {
        File photoOutputFile = new File("/storage/emulated/0/xfkc/"+fileName);
        Intent intent = new Intent(Intent.ACTION_VIEW);//Intent.ACTION_VIEW = "android.intent.action.VIEW"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addCategory(Intent.CATEGORY_DEFAULT);//Intent.CATEGORY_DEFAULT = "android.intent.category.DEFAULT"
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.xfkc.camai" + ".fileProvider", photoOutputFile);
            intent.setDataAndType(contentUri, "application/pdf");
        } else {
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setDataAndType(Uri.fromFile(photoOutputFile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        return intent;
    }

    public static Intent getWordFileIntent(String fileName,Context context)
    {
        File photoOutputFile = new File("/storage/emulated/0/xfkc/"+fileName);
        Intent intent = new Intent(Intent.ACTION_VIEW);//Intent.ACTION_VIEW = "android.intent.action.VIEW"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addCategory(Intent.CATEGORY_DEFAULT);//Intent.CATEGORY_DEFAULT = "android.intent.category.DEFAULT"
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(context, "com.xfkc.camai" + ".fileProvider", photoOutputFile);
            intent.setDataAndType(contentUri, "application/msword");
        } else {
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setDataAndType(Uri.fromFile(photoOutputFile), "application/msword");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        return intent;
    }
}
