package com.springcloud.common.model.dto;

import java.io.Serializable;

import com.demo.springcloud.common.core.constant.ResultEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 带数据的返回结果
 *
 * @author Ke Wang
 */
@NoArgsConstructor
@Getter
@Setter
public class DataResult<E> extends Result implements Serializable {

	private static final long serialVersionUID = -617587952857303259L;

    /**
     * 结果数据
     */
    private E data;

    /**
     * 构造函数
     * @param resultEnum
     * @param e
     */
    public DataResult(ResultEnum resultEnum, E e) {
        super(resultEnum);
        this.data = e;
    }

    /**
     * 返回成功数据的方法
     * @param o
     * @return DataResult
     */
    public static DataResult ofSuccess(Object o) {
        return new DataResult<>(ResultEnum.SUCCESS, o);
    }

}
