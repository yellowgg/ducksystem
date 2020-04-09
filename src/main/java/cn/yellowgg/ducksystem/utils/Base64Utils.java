package cn.yellowgg.ducksystem.utils;

import cn.hutool.core.codec.Base64;

/**
 * @Description: 这里再封装hutool的base64util
 * @Author: yellowgg
 * @Date: Created in 2020/4/8 12:13
 */
public class Base64Utils {

    /**
     * 解密多次
     *
     * @param str   密文
     * @param count 次数
     * @return 解密过后的明文
     */
    public static String decodeStrofCount(String str, int count) {
        for (int i = 0; i < count; i++) {
            str = Base64.decodeStr(str);
        }
        return str;
    }

    /**
     * 加密多次
     *
     * @param str   密文
     * @param count 次数
     * @return 加密之后过后的明文
     */
    public static String encodeStrofCount(String str, int count) {
        for (int i = 0; i < count; i++) {
            str = Base64.encode(str);
        }
        return str;
    }
}
