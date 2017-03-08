package com.zpauly.droidfix;

import android.app.Application;
import android.content.Context;

/**
 * Created by zpauly on 2017/3/8.
 */

public class MiniRobustApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        MiniRobust.install(base);
        super.attachBaseContext(base);
    }
}
