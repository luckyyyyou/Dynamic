package com.example.dynamic.Database;

import org.litepal.crud.DataSupport;

/**
 * Created by Danni on 2018/5/11.
 */

public class Ip extends DataSupport {

    private String ip;
    private int port;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
