package com.springcloud.common.model.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页对象
 *
 * @author Ke Wang
 */
@Getter
@Setter
public class PageResult<E> implements Serializable {

	private static final long serialVersionUID = -809660885012040887L;

	// 页码
    private int page;

    // 每页数据量
    private int size;

    // 总页数
    private int totalPages;

    // 记录数
    private long totalElements;

    // 记录数据
    private List<E> rows;

    /**
     * 无参构造函数
     */
    public PageResult() {
    }

    /**
     * 构造函数
     */
    public PageResult(int page, int size, int totalPages, long totalElements, List<E> rows) {
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.rows = rows;
    }

}
