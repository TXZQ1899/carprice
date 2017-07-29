package com.askprice.carprice.dao;

public abstract interface Pagination
{
  public abstract int getTotal();

  public abstract int getSize();

  public abstract int getTotalPage();

  public abstract int getNumber();

  public abstract boolean isFirst();

  public abstract boolean isLast();

  public abstract int getNextNumber();

  public abstract int getPreviousNumber();
}
