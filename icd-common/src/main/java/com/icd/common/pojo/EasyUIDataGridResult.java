package com.icd.common.pojo;

import java.io.Serializable;
import java.util.List;

public class EasyUIDataGridResult implements Serializable{

    private long total;
    private List<?> rows;

    public void setTotal(long l) {
        this.total = l;
    }

    public long getTotal() {
		return total;
	}

	public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}