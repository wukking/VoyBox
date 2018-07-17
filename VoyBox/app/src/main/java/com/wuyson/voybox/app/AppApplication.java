package com.wuyson.voybox.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.wuyson.common.base.BaseApplication;
import com.wuyson.voybox.BuildConfig;

public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);

        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
