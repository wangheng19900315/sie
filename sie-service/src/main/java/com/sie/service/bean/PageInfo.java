package com.sie.service.bean;

import java.util.List;

/**
 * Created by wangheng on 2017/8/12.
 */
public class PageInfo<T> {

    /**
     * 总页数
     */
    private int total;

    /**
     * 当前页
     */
    private int page;

    /**
     * 查询返回记录数
     */
    private int records;

    /**
     * 信息
     */
    private List<T> rows;

    /**
     * 构造
     *
     * @param count     总记录数
     * @param pageCount 页数
     */
    public PageInfo(int count, int pageCount) {
        total = pageCount;
        records=count;
    }

    /**
     * main函数.
     *
     * @param args 启动参数
     * @throws Exception Exception
     */
    public static void main(String... args) throws Exception {
        int count = 30;
        int pageCount = 10;

        System.out.println(count % pageCount == 0 ? count / pageCount : count / pageCount + 1);
        System.out.println(Math.floor((long) 12 % (long) 10));

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

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }



}

