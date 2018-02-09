package com.qzdsoft.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 前段datatable数据返回对象
 *
 * Created by Yang
 */
public class DataTablePo implements Serializable {

    private Integer iTotalRecords;

    private Integer iTotalDisplayRecords;

    private List data;

    private String sEcho;

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public DataTablePo() {
    }

    public DataTablePo(Integer iTotalRecords, List data, String sEcho) {
        this.iTotalRecords = iTotalRecords;
        this.iTotalDisplayRecords = iTotalRecords;
        this.data = null != data ? data : new ArrayList();
        this.sEcho = sEcho;
    }
}
