package pl.harpi.samples.j2ee.demo.model.base;

import java.util.Collections;
import java.util.List;

public class DataResult {
    private List<Object> data;
    private long start;
    private long total;

    public DataResult(long start, long total, List<Object> data) {
        setStart(start);
        setTotal(total);
        setData(data);
    }

    private void setData(List<Object> data) {
        this.data = (data == null) ? Collections.EMPTY_LIST : data;
    }

    private void setStart(long start) {
        this.start = start;
    }

    private void setTotal(long total) {
        this.total = total;
    }

    public List<Object> getData() {
        return data;
    }

    public long getStart() {
        return start;
    }

    public long getTotal() {
        return total;
    }
}
