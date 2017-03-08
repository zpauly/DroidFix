package com.zpauly.droidfix;

/**
 * Created by zpauly on 2017/3/8.
 */

public class PatchedClassInfo {
    private String packageName;

    private String canonicalName;

    public PatchedClassInfo(String packageName, String canonicalName) {
        this.packageName = packageName;
        this.canonicalName = canonicalName;
    }

    /**
     * 获取patch类的包名
     * @return
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 获取patch的类名
     * @return
     */
    public String getCanonicalName() {
        return canonicalName;
    }

    /**
     * 获取patch类的完整类名
     * @return
     */
    public String getFullClassName() {
        return packageName + "." + canonicalName;
    }
}
