package com.github.zhenwei.wegoo.common.util;

import com.github.zhenwei.sdk.util.BytesUtil;

public class ArraysUtil {


    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static boolean isEmpty(byte[] data) {
        return BytesUtil.isEmpty(data);
    }

    public static boolean notEmpty(byte[] data) {
        return !isEmpty(data);
    }

    public static boolean notEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    public static byte[] merge(byte[]... datas) {
        return BytesUtil.mergeBytes(datas);
    }

}