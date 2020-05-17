package com.springcloud.common.model.dto;

import java.io.Serializable;

import com.demo.springcloud.common.core.constant.ResultEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询结果
 *
 * @author Ke Wang
 */
@ApiModel(description = "操作结果")
@Data
@NoArgsConstructor
public class Result implements Serializable {

	private static final long serialVersionUID = 8377646792793477213L;

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "操作结果状态", required = true, example = "true")
    private boolean success;

    /**
     * 返回码
     */
    @ApiModelProperty(value = "操作结果编码", required = true, example = "10001")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "操作结果信息", example = "成功")
    private String message;

    public Result(ResultEnum resultEnum) {
        this.success = resultEnum.isSuccess();
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMessage();
    }

    public Result(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    public static Result ofSuccess() {
        return new Result(ResultEnum.SUCCESS);
    }

    public static Result ofFail() {
        return new Result(ResultEnum.FAIL);
    }

    public static Result ofTimeout() {
        return new Result(ResultEnum.TIMEOUT);
    }

}
