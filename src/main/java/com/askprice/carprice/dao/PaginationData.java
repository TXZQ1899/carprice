package com.askprice.carprice.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Common class for storing paging parameter and response paging info
 * @author Vian Tang
 *
 */
public class PaginationData<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private int record_count;
	private boolean lastpage;
	private int total_page;
	private int page_no;
	private int page_size;
	private List<T> list;
	private Object data;

	public PaginationData(){
	}
	
	public <K> PaginationData(PaginationData<K> page){
		this.setLastpage(page.isLastpage());
		this.setPage_no(page.getPage_no());
		this.setPage_size(page.getPage_size());
		this.setRecord_count(page.getRecord_count());
		this.setTotal_page(page.getTotal_page());
	}

	public PaginationData(List<T> list){
		this.list = list;
	}

	public PaginationData(int recordCount, boolean isLastPage, List<T> list){
		this.record_count = recordCount;
		this.lastpage = isLastPage;
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
		this.data = null;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
		this.list = null;
	}

	public int getRecord_count()
	{
		return record_count;
	}

	public void setRecord_count(int record_count)
	{
		this.record_count = record_count;
	}

	public boolean isLastpage()
	{
		return lastpage;
	}

	public void setLastpage(boolean lastpage)
	{
		this.lastpage = lastpage;
	}

	public int getTotal_page()
	{
		return total_page;
	}

	public void setTotal_page(int total_page)
	{
		this.total_page = total_page;
	}

	public int getPage_no()
	{
		return page_no;
	}

	public void setPage_no(int page_no)
	{
		this.page_no = page_no;
	}

	public int getPage_size()
	{
		return page_size;
	}

	public void setPage_size(int page_size)
	{
		this.page_size = page_size;
	}
	
}
