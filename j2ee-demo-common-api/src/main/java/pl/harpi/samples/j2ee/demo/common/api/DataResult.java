package pl.harpi.samples.j2ee.demo.common.api;

public class DataResult {
    private long start;
    private int size;
    private QueryProperty sort;
    private OrderType order;
    private long total;
    private Object data;

    public DataResult(long start, int size, QueryProperty sort, OrderType order, long total, Object data) {
        setStart(start);
        setSize(size);
        setSort(sort);
        setOrder(order);
        setTotal(total);
        setData(data);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private void setStart(long start) {
        this.start = start;
    }

    private void setTotal(long total) {
        this.total = total;
    }

    public long getStart() {
        return start;
    }

    public long getTotal() {
        return total;
    }

    public QueryProperty getSort() {
        return sort;
    }

    public void setSort(QueryProperty sort) {
        this.sort = sort;
    }

    public OrderType getOrder() {
        return order;
    }

    public void setOrder(OrderType order) {
        this.order = order;
    }
}
