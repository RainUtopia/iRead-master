package com.yang.iread.util;

import com.socks.library.KLog;

import java.lang.reflect.ParameterizedType;


public class ClassUtil {
    public static <T> T getNewClass(Object o, int i) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[i])
                    .newInstance();
        } catch (InstantiationException e) {
            KLog.e(e);
        } catch (IllegalAccessException e) {
            KLog.e(e);
        } catch (ClassCastException e) {
            KLog.e(e);
        }
        return null;
    }

    public static Class<?> getClassForName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            KLog.e(e);
        }
        return null;
    }
}
