package com.vzsme.controllerService.controller.v1.request;

import org.json.JSONArray;

class BaseRequestBody {
    JSONArray filters;
    int offset;
    int limit;
    String orderBy;
    String direction;
}

