package com.askprice.carprice.common.util.excel;

public class ExcelHeader implements Comparable<ExcelHeader>{
    private String title;
    private int order;
    private String methodName;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getOrder() {
        return order;
    }
    public void setOrder(int order) {
        this.order = order;
    }
    public String getMethodName() {
        return methodName;
    }
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int compareTo(ExcelHeader o) {
        return order>o.order?1:(order<o.order?-1:0);
    }
    public ExcelHeader(String title, int order, String methodName) {
        super();
        this.title = title;
        this.order = order;
        this.methodName = methodName;
    }
    @Override
    public String toString() {
        return "ExcelHeader [title=" + title + ", order=" + order
                + ", methodName=" + methodName + "]";
    }
}