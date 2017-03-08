package com.zpauly.droidfix;

import android.content.Context;
import android.os.Environment;

import dalvik.system.DexClassLoader;

/**
 * 操作
 * Created by zpauly on 2017/3/8.
 */

public class MiniRobust {
    private static MiniRobust miniRobustInstance = null;

    private Context mContext;

    private MiniRobust(Context context) {
        mContext = context;
    }

    public static void install(Context context) {
        if (miniRobustInstance == null) {
            synchronized (MiniRobust.class) {
                if (miniRobustInstance == null) {
                    miniRobustInstance = new MiniRobust(context);
                }
            }
        }
    }

    public void patch(String patchPackageName) {
        String nativeLibraryPath = FileManager.getNativeLibraryFolder(mContext.getPackageName()).getPath();
        DexClassLoader dexClassLoader = new DexClassLoader(patchPackageName, Environment.getExternalStorageState(), nativeLibraryPath, getClass().getClassLoader());
        PatchExecutorImpl executor = new PatchExecutorImpl();
        executor.patch(dexClassLoader);
    }
}
