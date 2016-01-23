package kp.edwd.nekst.model;


public class ResultItem implements Comparable<ResultItem> {
    private final int position;
    private final double value;

    public ResultItem(int position, double value) {
        this.position = position;
        this.value = value;
    }

    @Override
    public int compareTo(ResultItem o) {
        return (o.value < value) ? -1 : 1;
    }

    @Override
    public String toString() {
        return Integer.toString(position) + " ";
    }
}
