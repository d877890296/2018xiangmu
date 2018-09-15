package com.xfkc.caimai.pdf;

import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hyf.tdlibrary.utils.ToastUtil;
import com.hyf.tdlibrary.utils.Tools;
import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.xfkc.caimai.R;
import com.xfkc.caimai.base.BaseActivity;

import java.io.File;
import java.util.HashMap;

/**
 * @author liyuanyou E-mail:aidiyuanyuan@qq.com
 * @version 创建时间：2017年7月12日 下午5:21:20 类说明
 */
public class PDFActivity extends BaseActivity implements OnPageChangeListener, OnDrawListener, OnLoadCompleteListener {

	String pdfPath = "", pdfName = "";

	private ImageButton back_img_btn;
	private TextView text_title_content, curPage_textView, totalPage_textView;

	private PDFView pdfView;
	// pdf当前播放页数
	Integer pageNumber = 1;
	// pdf总页数
	private int pageCount;
	private String CWLOCATION = "";// 记录当前pdf文档看到的最大播放页数
	private Button previouspage_btn, nextpage_btn;
	public static final String SAMPLE_FILE = "sample.pdf";
	public static final String ABOUT_FILE = "about.pdf";
	// 记录已读页
	private HashMap<String, Integer> map;
	// 已接口返回的格式记录已读页
	private String page = "";




	@Override
	protected int getLayoutResource() {
		defaultDataInit();
		return R.layout.pdf_activity_layout;
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		viewInit();

	}

	@Override
	protected void loadData() {

	}

	// 默认数据的初始化
	public void defaultDataInit() {

		pdfPath = getIntent().getStringExtra("pdfPath");
		pdfName = getIntent().getStringExtra("pdfName");
//		CWLOCATION = getIntent().getStringExtra("CWLOCATION");// 当前文档显示页
//		String PAGENUMBER = getIntent().getStringExtra("PAGENUMBER");// 当前文档已读页
		// 如果CWOLOCATION为空或者0就默认显示第一页。
		//if (Tools.IsEmpty(CWLOCATION) || CWLOCATION.equals("0")) {
			pageNumber = 1;
//		} else {
//			pageNumber = Integer.parseInt(CWLOCATION);
//		}
		if (Tools.IsEmpty(pdfPath)) {
			ToastUtil.showToast( "找不到文件");
		}
//		CWLOCATION = pageNumber + "";
//		map = new HashMap<String, Integer>();
//		String[] pageNumber = PAGENUMBER.split(",");
//		for (int i = 0; i < pageNumber.length; i++) {
//			map.put(pageNumber[i], 1);
//		}
	}

	public void viewInit() {
		back_img_btn = (ImageButton) findViewById(R.id.back_img_btn);
		back_img_btn.setOnClickListener(onClickListener);
		text_title_content = (TextView) findViewById(R.id.text_title_content);
		nodataview_textview = (TextView) findViewById(R.id.nodataview_textview);
		pdfView = (PDFView) findViewById(R.id.pdfview);
		curPage_textView = (TextView) findViewById(R.id.curPage_textView);
		totalPage_textView = (TextView) findViewById(R.id.totalPage_textView);
		text_title_content.setText(pdfName);
		previouspage_btn = (Button) findViewById(R.id.previouspage_btn);
		nextpage_btn = (Button) findViewById(R.id.nextpage_btn);

		previouspage_btn.setOnClickListener(onClickListener);
		nextpage_btn.setOnClickListener(onClickListener);


		requstPdf();



	}

	DownResouces downResouces;
	public  void requstPdf(){
		//nodataview_textview.setVisibility(View.VISIBLE);
		downResouces=	new DownResouces(pdfPath, handler, mContext
				);
		downResouces.execute(pdfPath);
		downResouces.setOnDownCallBback(new DownResouces.OnDownCallBback() {
			@Override
			public void backResult(final String path) {

				PDFActivity.this.runOnUiThread(new Runnable() {
					@Override
					public void run() {

					//	nodataview_textview.setVisibility(View.GONE);
						setPdfData(path);
					}
				});

			}
		});
	}

	public void setPdfData(	String result) {
		File file = new File(result);
		if (file != null) {
			pdfView.fromFile(file)
					// .pages(0, 2, 1, 3, 3, 3)
					.defaultPage(pageNumber).showMinimap(false)
					//.onLoad(this)
					// .onDraw(this)
					.swipeVertical(true)
					.enableSwipe(true).onPageChange(PDFActivity.this).load();
		} else {
			nodataview_textview.setText("文档下载失败。");
		}
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what){
				case  1081:
					 result=msg.obj.toString();


					break;
			}
		}
	};
	String result;
	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.back_img_btn:
				back();
				break;
			case R.id.previouspage_btn:// 上一页
				if ((pageNumber - 1) > 0 && (pageNumber - 1) < pageCount) {
					pageNumber = pageNumber - 1;
					pdfView.jumpTo(pageNumber);
				} else {
					ToastUtil.showToast("已经是第一页了");
				}
				break;
			case R.id.nextpage_btn:// 下一页
				if ((pageNumber + 1) > 0 && (pageNumber + 1) <= pageCount) {
					pageNumber = pageNumber + 1;
					pdfView.jumpTo(pageNumber);
				} else {
					ToastUtil.showToast("已经是最后一页了");
				}
				break;
			default:
				break;
			}
		}
	};

	/**
	 * 显示文件名
	 */
	void afterViews() {
		display(pdfName, false);
	}

	/**
	 * 显示文件名
	 */
	public void about() {
		if (!displaying(ABOUT_FILE))
			display(ABOUT_FILE, true);
	}

	private void display(String assetFileName, boolean jumpToFirstPage) {
		if (jumpToFirstPage)
			pageNumber = 1;
		setTitle(pdfName = assetFileName);
		pdfView.fromAsset(assetFileName).defaultPage(pageNumber).onPageChange(this).load();
	}

	@Override
	public void onPageChanged(int page, int pageCount) {
		// 记录已读页
//		map.put(page + "", 1);

		this.pageNumber = page;
		this.pageCount = pageCount;
		curPage_textView.setText(page + "");
		totalPage_textView.setText("/ " + pageCount);
		// 记录当前已读最大页
		CWLOCATION = pageNumber + "";
	}

	@Override
	public void onBackPressed() {
		if (ABOUT_FILE.equals(pdfName)) {
			display(SAMPLE_FILE, true);
		} else {
			super.onBackPressed();
		}
	}

	private boolean displaying(String fileName) {
		return fileName.equals(pdfName);
	}

	@Override
	public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
	}

	/**
	 * pdf加载完毕，有内容后的回调
	 */
	@Override
	public void loadComplete(int nbPages) {
	}



	@Override
	protected void onPause() {
		super.onPause();

	}


	/**
	 * 重写系统按钮
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:// 返回键事件
			back();
			break;
		}
		return true;
	}

	private void back() {

		backHistory(1081, true, false, extraMap);
	}
}
