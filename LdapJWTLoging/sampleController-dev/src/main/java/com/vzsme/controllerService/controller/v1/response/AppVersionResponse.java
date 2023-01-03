package com.vzsme.controllerService.controller.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL) // Null items are not sent in response
public class AppVersionResponse {
    public String version;
    public String name;
    public String description;

    public AppVersionResponse(@NotNull String version, @NotNull String name, @NotNull String desc) {
        this.version = version;
        this.name = name;
        this.description = desc;
    }
}
