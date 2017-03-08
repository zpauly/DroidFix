package com.zpauly.droidfix;

import dalvik.system.DexClassLoader;

/**
 * Created by zpauly on 2017/3/8.
 */

public interface PatchExecutor {
    void patch(DexClassLoader classLoader);
}
