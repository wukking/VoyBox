package com.wuyson.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wuyson.common.base.BaseApplication;


/**
 * @author: Wuyson
 * @date: 2017/12/2 - 12:02
 * @description: 屏幕工具类
 */

public class DisplayUtils {
    public DisplayUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    /**
     * dp 转 px
     */
    public static int dp2px(float dpValue) {
        float density = BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * px 转 dp
     */
    public static int px2dp(float pxValue) {
        float density = BaseApplication.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5f);
    }

    /**
     * sp 转 px
     */
    public static int sp2px(float spValue) {
        final float fontScale = BaseApplication.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px 转  sp
     */
    public static int px2sp(float pxValue) {
        float scaledDensity = BaseApplication.getAppContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scaledDensity + 0.5f);
    }

    /**
     * 获取屏幕的宽度
     * 注意：不包含虚拟按键
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     * 注意：不包含虚拟按键
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     * 注意：包含虚拟按键 （和下面高度配对的）
     *
     * @param context
     * @return
     */
    public static int getRealScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getRealMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     * 注意：包含虚拟按键
     *
     * @param context
     * @return
     */
    public static int getRealScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getRealMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 获得状态栏的高度
     * 注意：该方法只能在Activity类中使用，在测试模式下失败
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 直接获取控件的宽、高
     *
     * @param view
     * @return int[]
     */
    public static int[] getWidgetWH(final View view) {
        ViewTreeObserver vto2 = view.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        return new int[]{view.getWidth(), view.getHeight()};
    }

    /**
     * 直接获取View的宽度
     *
     * @param view
     * @return
     */
    public static int getViewWidth(final View view) {
        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //这个移除监听已经过时，替代的是最低版本16的removeOnGlobalLayoutListener
                observer.removeGlobalOnLayoutListener(this);
            }
        });
        return view.getWidth();
    }

    /**
     * 直接获取View的高度
     *
     * @param view
     * @return
     */
    public static int getViewHeight(final View view) {
        final ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //这个移除监听已经过时，替代的是最低版本16的removeOnGlobalLayoutListener
                observer.removeGlobalOnLayoutListener(this);
            }
        });
        return view.getHeight();
    }

    /**
     * 获取控件的宽
     *
     * @param view
     * @return
     */
    public static int getWidgetWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        return width;
    }

    /**
     * 获取控件的高
     *
     * @param view
     * @return
     */
    public static int getWidgetHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        return height;
    }

    /**
     * 设置控件宽
     *
     * @param view
     * @param width
     */
    public static void setWidgetWidth(View view, int width) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

    /**
     * 设置控件高
     *
     * @param view
     * @param height
     */
    public static void setWidgetHeight(View view, int height) {
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
        //或者用view.re.requestLayout()
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity
     * @return Bitmap
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity
     * @return Bitmap
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 禁止截屏
     *
     * @param activity
     */
    public static void BanScreenshots(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    public static void ScreenAlwaysOn(Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 不准确获取状态栏高度
     *
     * @param activity
     * @return attention: http://blog.csdn.net/ccpat/article/details/55224475
     * 1. 当 activity 设置全屏，即 getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)时为0.
     * 2. 窗口的LayoutParams的height设置为WindowManager.LayoutParams.WRAP_CONTENT或者某个具体的值，
     * 则outRect中的top值会等于系统状态栏和窗口重叠区域的高度
     * 3. 屏幕1920，窗口高度1900 居中显示，StatusBar为60，top的值为：60-（1920-（1900+10））= 50
     */
    public static int getStatusBarHeight2(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        return statusBarHeight;
    }

    /** ====================      使用系统自带转换，  没有加0.5f   ======================*/
    /**
     * dp转px
     */
    public static int dp2pxBySystem(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int sp2pxBySystem(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float px2dpBySystem(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float px2spBySystem(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
