package com.alisls.demo.springcloud.common.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class DataResult extends Result implements Serializable {

	private static final long serialVersionUID = 7344479493273949029L;
	
	private Object data;

    public DataResult(ResultCode resultCode, Object data) {
        super(resultCode);
        this.data = data;
    }

    public static DataResult ofSuccess(Object data) {
        return new DataResult(ResultCode.SUCCESS, data);
    }
}
