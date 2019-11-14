package com.alisls.demo.springcloud.common.response;

import java.io.Serializable;

public class DataResult<E> extends Result implements Serializable {

	private static final long serialVersionUID = 7344479493273949029L;
	
	private E data;

    public DataResult(ResultCode resultCode, E data) {
        super(resultCode);
        this.data = data;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static DataResult ofSuccess(Object data) {
        return new DataResult(ResultCode.SUCCESS, data);
    }

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
   
}
