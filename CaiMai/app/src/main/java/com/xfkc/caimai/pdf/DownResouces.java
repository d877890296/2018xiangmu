package com.xfkc.caimai.pdf;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/******
 *
 * 下载资源文件
 *
 * 资源下载完成后
 *
 *
 * 第一次就想安装wpkapp
 *
 * 第二次就直接进入
 *
 *
 *
 * @author Lyy
 *
 */
public class DownResouces extends AsyncTask<String, Intent, String> {
    public String imgUrl;
    Context context;
    Handler handler;
    int byteSize, progress;
    // 下载资源的后缀
    String extension = "pdf";


    private int errorState;
    OnDownCallBback onDownCallBback;

    public void setOnDownCallBback(OnDownCallBback onDownCallBback) {
        this.onDownCallBback = onDownCallBback;
    }

    public DownResouces(String imgUrl, Handler handler, Context context
    ) {
        this.imgUrl = imgUrl;
        this.handler = handler;
        this.context = context;


    }


    @Override
    protected String doInBackground(String... params) {
        String path = "";
        File file = null;
        path = Environment.getExternalStorageDirectory().getPath().toString() + "/gjdw/res"
                + File.separator;
        file = new File(path +params[0] );

        // Log.e("-----file-------", file.getPath().toString() + "----");
        if (file.exists() && file.length() > 100) {
            return file.getPath().toString();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.delete();
                file.createNewFile();
                URL url = new URL(params[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                // httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                long total = httpURLConnection.getContentLength();
                // progressBar.setMax((int) total);
                InputStream is = httpURLConnection.getInputStream();
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                int index = -1;
                byte data[] = new byte[1024];
                while ((index = is.read(data)) != -1) {
                    randomAccessFile.write(data, 0, index);
                    byteSize += index;
                    progress = (int) ((byteSize / (float) (total)) * 100);
                    // 调用publishProgress公布进度,最后onProgressUpdate方法将被执行
                    publishProgress();
                }
                is.close();
                randomAccessFile.close();
                return file.getPath().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();


            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Intent... values) {
        // 更新进度条

        if (progress == 100) {

        } else {

        }
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {


//        Message msg = new Message();
//        msg.what = 1081;
//        msg.obj = result;
//        handler.sendMessage(msg);

        onDownCallBback.backResult(
                result);
        super.onPostExecute(result);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }




    public interface  OnDownCallBback{
        public void backResult(String path);
    }

}
