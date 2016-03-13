package com.example.vladimirkarassouloff.projetter.network;

/**
 * Created by Vladimir on 12/03/2016.
 */
public class NetworkInfo {

    private String ip;
    private int port;

    public NetworkInfo(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

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
