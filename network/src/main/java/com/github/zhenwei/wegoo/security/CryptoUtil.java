package com.github.zhenwei.wegoo.security;

import com.github.zhenwei.core.enums.CipherAlgEnum;
import com.github.zhenwei.core.exception.WeGooCipherException;
import com.github.zhenwei.sdk.builder.CipherBuilder;
import com.github.zhenwei.wegoo.properties.DefaultProperties;

import java.security.Key;

public class CryptoUtil {

    public static byte[] sm4CbcEncryptData(byte[] data, Key key, DefaultProperties properties) throws WeGooCipherException {
        return CipherBuilder.cipher(CipherAlgEnum.SM4_CBC_PKCS7Padding, key, data, properties.getIv(), true);
    }


    public static byte[] sm4CbcDecryptData(byte[] data, Key key, DefaultProperties properties) throws WeGooCipherException {
        return CipherBuilder.cipher(CipherAlgEnum.SM4_CBC_PKCS7Padding, key, data, properties.getIv(), false);
    }


}