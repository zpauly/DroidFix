package com.zpauly.droidfix;

/**
 * patch操作的代理类
 * Created by zpauly on 2017/3/8.
 */

public class PatchProxy {
    /**
     * 代理执行patch操作
     * @param fullMethodName
     * @param codeChange
     * @param patchedObject
     * @param args
     * @return
     */
    public static Object accessPatch(String fullMethodName, CodeChange codeChange, Object patchedObject, Object... args) {
        return codeChange.accessDispatch(fullMethodName, patchedObject, args);
    }
}
