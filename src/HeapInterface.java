public interface HeapInterface<T extends Comparable<? super T>> {
    public int add(T newEntry);
    public T removeMax();
    public T getMax();
    public boolean isEmpty();
    public int getSize();
    public void clear();

}
