package com.alisls.demo.springcloud.common.dto;

import java.util.List;

public class DataOutDTO<T> extends OutDTO {

    public List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
