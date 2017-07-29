package com.askprice.carprice.dao;

public abstract class AbstractPage implements Pagination {
	protected int total; //total count
	protected int size;    //page size
	protected int number;  //page number

	public AbstractPage(int total, int size, int number) {
		this.total = 0;
		this.size = 0;
		this.number = 1;

		this.total = total;
		this.size = size;
		this.number = number;
	}

	public AbstractPage(int size, int number) {
		this(0, size, number);
	}

	public AbstractPage(int size) {
		this(0, size, 1);
	}

	public AbstractPage() {
		this.total = 0;
		this.size = 0;
		this.number = 1;
	}

	public abstract int getDefaultSize();

	public int getTotalPage() {
		if (this.size < 0)
			return ((Math.max(0, this.total) + getDefaultSize() - 1) / getDefaultSize());
		if (this.size == 0)
			return this.total;
		return Math.max(1, (Math.max(0, this.total) + this.size - 1)
				/ this.size);
	}

	public int getNextNumber() {
		return Math.min(Math.abs(this.number) + 1, getTotalPage());
	}

	public int getPreviousNumber() {
		return Math.max(1, Math.abs(this.number) - 1);
	}

	public boolean isFirst() {
		return (this.number <= 1);
	}

	public boolean isLast() {
		return (this.number >= getTotalPage());
	}

	public int getStart() {
		int start = (getNumber() - 1) * getSize() + 1;
		return Math.max(1, start)-1;
	}

	public int getEnd() {
		return (getStart() + getSize());
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
