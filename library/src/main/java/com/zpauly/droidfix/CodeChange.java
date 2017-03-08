package com.zpauly.droidfix;

/**
 * 既作为代码逻辑替换的标识，同时他的实现类也是代码替换类。通过实现该接口，在accessDispatch中编写替换代码。
 * Created by zpauly on 2017/3/8.
 */

public interface CodeChange {
    Object accessDispatch(String methodId, Object patchObject, Object... args);
}
