package org.wz.config.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @project: userManager
 * @package: com.wonders.bean
 * @author: Administrator
 * @crDate: 2019/3/20 10:33
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    private int total;
    private List<?> rows;

    public Page(List<?> list, int total) {
        this.rows = list;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
