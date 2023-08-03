package com.workflow.pro.modules.oss.domain;

import java.util.Date;

public class CommonToken {
    private String token;
    private String access_host;

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    private Date expiration;



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccess_host() {
        return access_host;
    }

    public void setAccess_host(String access_host) {
        this.access_host = access_host;
    }
}
