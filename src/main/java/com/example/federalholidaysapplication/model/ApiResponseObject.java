package com.example.federalholidaysapplication.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseObject<T> {
    private T data;

    private List<T> dataList;
    private String errorMessage;

    public ApiResponseObject(T data, String errorMessage, List<T> dataList) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.dataList = dataList;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
