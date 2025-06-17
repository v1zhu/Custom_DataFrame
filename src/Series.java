public interface Series<T>{

    public String toString();

    public int getLength();

    public String[] getRowNames();

    public String[] getData();

    public void append(String rn, T d);

    public T loc(String rn);

    public T[] loc(String[] rn);

    public T iloc(int ind);

    public boolean drop(String rn);

    public void fillNull(T value);

}
