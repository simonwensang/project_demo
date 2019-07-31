package com.project.mc.common;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -7710168010225904920L;

    /**
     * 数据列表
     */
    private List<T> rows;
    /**
     * 总记录数
     */
    private long records;
    /**
     * 总页数
     */
    private int total;
    /**
     * 当前页
     */
    private int page;
    
    /**
     * 前一页
     */
    private int pre;
    
    /**
     * 下一页
     */
    private int next;

    public PageResult() {
    }

    public PageResult(List<T> rows, long records, int total, int page, int pre, int next) {
        this.rows = rows;
        this.records = records;
        this.total = total;
        this.page = page;
        this.pre=pre;
        this.next=next;
    }

    public static <T> PageResult<T> newPage(List<T> rows, long records, int total, int page, int pre, int next) {
        return new PageResult<T>(rows, records, total, page, pre,  next);
    }

    public static <T> PageResult<T> newPageResult(List<T> rows, long records, int pageNo, int pageSize) {
    	int pre = pageNo<3?1:(pageNo-1);
    	int totalPages = (int) (records % pageSize == 0 ? records / pageSize : records / pageSize + 1);
    	int next = pageNo<totalPages?(pageNo+1):1;
    	return new PageResult<T>(rows, records, totalPages==0?1:totalPages, pageNo, pre,  next);
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getRecords() {
        return records;
    }

    public void setRecords(long records) {
        this.records = records;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

	public int getPre() {
		return pre;
	}

	public void setPre(int pre) {
		this.pre = pre;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}
    
}
