package com.example.dynamic.Utils;

/**
 * Created by Danni on 2018/5/11.
 */

public class Config {
    public static String ADDRESS;// = "47.98.45.54";//118.178.186.148 10.21.11.26
    public static int PORT; //= 5500;
    public static final int REFRESH_TIME = 5000;
    public static final byte[] REQUEST_IP_TEMP = {0x41,0x50,0x56,0x56,0x00,0x64,(byte)0xa1};
    public static final byte[] REQUEST_IP_ERROR = {0x41,0x50,0x56,0x57,0x00,0x64,0x00,(byte)0xa2};
    public static final String SomethingWrong = "请求超时";
    public static final String TOO_MANY_REFRESH = "刷新过于频繁，请稍后再试";
}
