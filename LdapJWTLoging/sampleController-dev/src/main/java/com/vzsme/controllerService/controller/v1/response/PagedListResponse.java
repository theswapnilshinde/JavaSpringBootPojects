package com.vzsme.controllerService.controller.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL) // Null items are not sent in response
public class PagedListResponse<T> {
    private int offset;
    private int limit;
    private long totalCount;
    private long pageCount;
    private ArrayList<T> list;

    public PagedListResponse(int offset, int limit)
    {
        this.offset = offset;
        this.limit = limit;
        this.totalCount = 0;
        this.pageCount = 0;
        this.list = new ArrayList<T>();
    }

    public int getLimit() { return limit; }
    public int getOffset() { return offset; }

    public long getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.pageCount = (totalCount / limit) + (totalCount % limit > 0 ? 1 : 0);
    }

    public ArrayList<T> getList() {
        return list;
    }
    public void setList(ArrayList<T> list) {
        this.list = list;
    }

}
