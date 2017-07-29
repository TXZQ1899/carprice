package com.askprice.carprice.dao;

import java.util.ArrayList;
import java.util.List;

public class ListPage<T> extends AbstractPage {
	public static final int DEFAULT_LIST_SIZE = 3;
	private List<T> list;
	private List<ListPage<T>.OrderBy> orderByList;
	private List<?> dtoList = new ArrayList<>();
	private String condition = "";
	
	public ListPage() {
		super(DEFAULT_LIST_SIZE);
		this.list = new ArrayList<T>();
	}

	public ListPage(List<T> list, int allSize, int size, int number) {
		super(allSize, size, number);
		this.list = list;
	}

	public ListPage(int allSize, int size, int number) {
		this(new ArrayList<T>(), allSize, size, number);
	}

	public ListPage(int size, int number) {
		this(0, size, number);
	}

	public ListPage(int size, int number, String property, Boolean isAscending) {
		this(0, size, number);
		if(isAscending!=null){
			this.addOrder(property, isAscending?OrderType.ASCENDING:OrderType.DESCENDING);
		}
	}
	
	public ListPage(int size) {
		this(0, size, 1);
	}

	@Override
	public int getDefaultSize() {
		return DEFAULT_LIST_SIZE;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public List<ListPage<T>.OrderBy> getOrderByList() {
		return orderByList;
	}

	public void setOrderByList(List<ListPage<T>.OrderBy> orderByList) {
		this.orderByList = orderByList;
	}

	public <K> List<K> getDtoList() {
		return (List<K>) dtoList;
	}

	public void setDtoList(List<? extends Object> dtoList) {
		this.dtoList = dtoList;
	}

	private void addOrder(String property, OrderType type)
	  {
	    if ((property == null) || ("".equals(property)))
	      return;

	    if (this.orderByList == null)
	      this.orderByList = new ArrayList<OrderBy>();

	    this.orderByList.add(new OrderBy(property, type));
	  }
	
	public void addAscending(String property)
	  {
	    addOrder(property, OrderType.ASCENDING);
	  }

	  public void addDescending(String property)
	  {
	    addOrder(property, OrderType.DESCENDING);
	  }
	  
	 public class OrderBy
	  {
	    private String property;
	    private ListPage.OrderType type;

	    public OrderBy(String paramString, ListPage.OrderType paramOrderType)
	    {
	      this.property = paramString;
	      this.type = paramOrderType;
	    }

	    public String getProperty() {
	      return this.property;
	    }

	    public ListPage.OrderType getType() {
	      return this.type;
	    }
	  }

	  public static enum OrderType
	  {
	    ASCENDING, DESCENDING;
	  }
	
}