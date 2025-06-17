public class DataFrame<V> {

    private HashTable<SeriesV2<Object>> tabularData;
    private int numRows;
    private int numCols;

    public DataFrame() {
        tabularData = new HashTable<>();
        numRows = 0;
        numCols = 0;

    }

    public DataFrame(String _k, SeriesV2<Object> _series) {
        tabularData = new HashTable<>();
        tabularData.insert(_k, _series);
        numRows = _series.getLength();
        numCols = 1;

    }

    public SeriesV2<Object> colLoc(String k) {
        return tabularData.lookup(k);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("printing the dataframe ...\n");
        sb.append("==================\n");
        String []columnnames = tabularData.getValidKeys();

        for (int i = 0; i < columnnames.length; i++) {
            sb.append("[colName:\t").append(columnnames[i]).append("]\n");
            SeriesV2<Object> series = tabularData.lookup(columnnames[i]);
            sb.append(series.toString()).append("\n");
        }

        return sb.toString();

    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public String[] getColNames() {
        return tabularData.getValidKeys();
    }

    public void addColumn(String k, SeriesV2<Object> s) throws IllegalArgumentException {
        if (s.getLength() != numRows) {
            throw new IllegalArgumentException("addColumn(String k, SeriesV2<Object> s): the length of s does not match the dataframe's # of rows");

        }

        tabularData.insert(k, s);
        numCols++;

    }

    public void removeColumn(String k) {
        tabularData.delete(k);
        numCols--;
    }

}
