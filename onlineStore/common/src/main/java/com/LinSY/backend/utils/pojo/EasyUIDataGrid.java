package com.LinSY.backend.utils.pojo;

import java.util.List;

/**
 * @ClassName EasyUIDataGrid
 * @Author LinSY
 * @Date 2018/9/27 14:40
 * @Version 1.0
 * @Description 适用于 EayiUI 列表的 POJI 类
 */

public class EasyUIDataGrid {

    private long total;
    private List<?> rows;
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }


}
