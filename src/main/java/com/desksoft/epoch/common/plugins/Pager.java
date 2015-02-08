package com.desksoft.epoch.common.plugins;

import java.util.List;

/**
 * 
 * Copyright © 2014 desksoft. All rights reserved
 * @author fanghc
 * @date Jun 29, 2014 12:31:45 PM 
 * @param <E>
 */
public class Pager<E> {  
	
	public static final String PAGE_NUM_KEY = "pageNum";
	public static final String PAGE_SIZE_KEY = "pageSize";
	
	public static final int PAGE_NUM_DEFAULT_VALUE = 1;
	public static final int PAGE_SIZE_DEFAULT_VALUE = 20;
	
    private int pageNum;  
    private int pageSize;  
    private int offset;
    private int endRow;  
    private long totalCounts;  
	private int totalPages;  
    private List<E> result;  
    public Pager(int pageNum, int pageSize) {  
        this.pageNum = pageNum;  
        this.pageSize = pageSize;  
        this.offset = pageNum > 0 ? (pageNum - 1) * pageSize : 0;  
        this.endRow = pageNum * pageSize;  
    }  
    
    public List<E> getResult() {  
        return result;  
    }  

    public void setResult(List<E> result) {  
        this.result = result;  
    }  

    public int getEndRow() {  
        return endRow;  
    }  

    public void setEndRow(int endRow) {  
        this.endRow = endRow;  
    }  

    public int getPageNum() {  
        return pageNum;  
    }  

    public void setPageNum(int pageNum) {  
        this.pageNum = pageNum;  
    }  

    public int getPageSize() {  
        return pageSize;  
    }  

    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  


    public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotalCounts() {
		return totalCounts;
	}

	public void setTotalCounts(long totalCounts) {
		this.totalCounts = totalCounts;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


    @Override  
    public String toString() {  
        return "Page{" +  
                "pageNum=" + pageNum +  
                ", pageSize=" + pageSize +  
                ", offset=" + offset +  
                ", endRow=" + endRow +  
                ", totalCounts=" + totalCounts +  
                ", totalPages=" + totalPages +  
                '}';  
    }  
}  