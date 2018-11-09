package es.source.code.model;

import java.util.List;

public  class Xml extends Result {
    // Food列表
    private List<Food> dataList;
    public Xml() {
    }
    public Xml(int RESULTCODE, String msg, List<Food> dataList) {
        super(RESULTCODE,msg);
        this.dataList = dataList;
    }
    public List<Food> getDataList() {
        return dataList;
    }
    public void setDataList(List<Food> dataList) {
        this.dataList = dataList;
    }
}