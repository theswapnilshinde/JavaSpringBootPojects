package com.vzsme.controllerService.controller.v1.response;

import javax.validation.constraints.NotNull;

public class MessageResponse {
    public String message;

    public MessageResponse(@NotNull String message) {
        this.message = message;
    }
}
