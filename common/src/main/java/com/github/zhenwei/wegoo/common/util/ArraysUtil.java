package com.github.zhenwei.wegoo.common.util;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Stream;

public class ArraysUtil {


    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static boolean notEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    public static byte[] merge(byte[]... datas) {
        if (datas == null) {
            return new byte[0];
        }
        LongAdder longAdder = new LongAdder();
        Stream.of(datas).forEach(data -> longAdder.add(data.length));
        ByteBuffer buffer = ByteBuffer.allocate(longAdder.intValue());
        Stream.of(datas).forEach(buffer::put);
        return buffer.array();
    }


}