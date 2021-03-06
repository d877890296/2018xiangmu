package com.hyf.tdlibrary.rx.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hyf.tdlibrary.R;
import com.hyf.tdlibrary.base.BaseActivity;
import com.hyf.tdlibrary.rx.rx.ActivityLifeCycleEvent;
import com.hyf.tdlibrary.utils.StatusBarUtil;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by LK on 2017/4/18 18:04.
 */

public abstract class RxActivity extends BaseActivity {
  public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
    setStatusBar();
    try {
      RenZhen_SP.getBoolean(RxActivity.this,"key",false);
    } catch (Exception e) {
      RenZhen_SP.putBoolean(RxActivity.this,"key",false);
    }

  }

  /**
   * 设置状态栏颜色
   */
  protected void setStatusBar() {
    StatusBarUtil.setColor(this, getResources().getColor(R.color.color_theme));
  }
  @Override
  protected void onDestroy() {
    lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);

    super.onDestroy();
  }

  @Override
  protected void onStop() {
    lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);

    super.onStop();
  }

  public   <T> Observable.Transformer<Response<T>, Response<T>> bindUntilEvent(@NonNull final ActivityLifeCycleEvent event) {
    return new Observable.Transformer<Response<T>, Response<T>>() {
      @Override
      public Observable<Response<T>> call(Observable<Response<T>> sourceObservable) {
        Observable<ActivityLifeCycleEvent> o = lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {
          @Override
          public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
            return activityLifeCycleEvent.equals(event);
          }
        });
        return sourceObservable.takeUntil(o);
      }
    };
  }

  public <T> Observable.Transformer<Response<T>, Response<T>> bindUntilEvent() {
    return new Observable.Transformer<Response<T>, Response<T>>() {
      @Override
      public Observable<Response<T>> call(Observable<Response<T>> sourceObservable) {
        Observable<ActivityLifeCycleEvent> o = lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {
          @Override
          public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
            return activityLifeCycleEvent.equals(ActivityLifeCycleEvent.DESTROY);
          }
        });
        return sourceObservable.takeUntil(o);
      }
    };
  }

//  public void post(Observable ob, Subscriber subscriber) {
//    ob.compose(bindUntilEvent())
//            .subscribeOn(Schedulers.io())
//            .unsubscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(subscriber);
//  }
}
