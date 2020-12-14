package com.icis.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Enjoy yourself
 * @author: ReycoLL
 * @date: 2020/10/13 10:29
 */
public class PageBean<T> {
private int totalCount; //总记录数
private int totalPage; //总页数
private ArrayList<T> list ;//煤业的数据list集合
private int currentPage; //当前页码
private int rows ; // 每页显示的条数

    public PageBean() {

    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
