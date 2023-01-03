package com.vzsme.controllerService.model;

import javax.validation.constraints.NotEmpty;

public class DbInfo {
    @NotEmpty//(message = "is required")
    public String dbUrl;

    @NotEmpty//(message = "is required")
    public String dbUsr;

    @NotEmpty//(message = "is required")
    public String dbPwd;

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUsr() {
        return dbUsr;
    }

    public void setDbUsr(String dbUsr) {
        this.dbUsr = dbUsr;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }
}
