package com.github.zhenwei.wegoo.common.util;

import com.github.zhenwei.core.util.encoders.Hex;

public class HexUtil {

    public static String toHexString(byte[] data) {
        return Hex.toHexString(data);
    }

    public static byte[] decode(String data){
        return Hex.decode(data);
    }

}
