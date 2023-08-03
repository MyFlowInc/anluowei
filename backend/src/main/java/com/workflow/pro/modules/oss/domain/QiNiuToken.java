package com.workflow.pro.modules.oss.domain;

public class QiNiuToken extends CommonToken {
    private String token;
    private String access_host;
    private String key;
    private String srv;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSrv() {
        return srv;
    }

    public void setSrv(String srv) {
        this.srv = srv;
    }

    public String getUpload_host() {
        return upload_host;
    }

    public void setUpload_host(String upload_host) {
        this.upload_host = upload_host;
    }

    private String upload_host;

}
