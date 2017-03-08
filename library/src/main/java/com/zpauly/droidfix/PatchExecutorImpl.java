package com.zpauly.droidfix;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import dalvik.system.DexClassLoader;

/**
 * patch代码的实际操作类
 * Created by zpauly on 2017/3/8.
 */

public class PatchExecutorImpl implements PatchExecutor {
    private static final String TAG = PatchExecutorImpl.class.getName();

    /**
     * 获取所有patch类的信息
     * @return
     */
    public List<PatchedClassInfo> getPatchedClassesInfo(DexClassLoader classLoader) {
        try {
            //通过反射获取patch包中的PatchesInfoImpl类并执行getPatchedClassesInfo方法
            Class<?> patchedInfoImplClass = Class.forName("com.zpauly.minirobust.PatchesInfoImpl", true, classLoader);
            Object patchedInfoImplInstance = patchedInfoImplClass.newInstance();
            Method getPatchClassesInfoMethod = patchedInfoImplClass.getDeclaredMethod("getPatchedClassesInfo");
            getPatchClassesInfoMethod.setAccessible(true);
            return (List<PatchedClassInfo>) getPatchClassesInfoMethod.invoke(patchedInfoImplInstance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 为所有需patch的类执行patch操作
     */
    @Override
    public void patch(DexClassLoader classLoader) {
        for (PatchedClassInfo info : getPatchedClassesInfo(classLoader)) {
            load(info);
        }
    }

    /**
     * 根据某一个patch类的信息对其进行patch操作
     * @param info
     */
    private void load(PatchedClassInfo info) {
        try {
            //获取到patch类之后，通过反射将其赋值给bad类的静态成员变量codeChange中
            ClassLoader classLoader = getClass().getClassLoader();
            Class<?> codeChangeClass = classLoader.loadClass(info.getFullClassName() + "$patch");
            Object codeChangeInstance = codeChangeClass.newInstance();
            Class<?> patchedClass = classLoader.loadClass(info.getFullClassName());
            Field fieldCodeChange = patchedClass.getDeclaredField("changeQueckRedirect");
            fieldCodeChange.setAccessible(true);
            if (fieldCodeChange.get(null) == null) {
                fieldCodeChange.set(null, codeChangeInstance);
            } else {
                Log.i(TAG, "The field changeQuickRedirect in " + info.getFullClassName() + "  has been initalized.");
            }
        } catch (Exception e) {
            Log.e(TAG, "The class which needs to be patched is no found.");
            e.printStackTrace();
        }
    }
}
