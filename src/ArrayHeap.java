import java.util.Arrays;

public final class ArrayHeap<T extends Comparable<? super T>> implements HeapInterface {

    private T[] heap; //Array of heap entry
    private int lastIndex; //Index of last entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap(){
        this(DEFAULT_CAPACITY); //call next constructor
    }
    public MaxHeap(int initialCapacity){
        if(initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);



        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    }

    private void checkCapacity(int initialCapacity) {
        if(initialCapacity > MAX_CAPACITY){
            throw new IllegalStateException("The capacity is in the Heap is too big");
        }
    }


    @Override
    public void add(Comparable newEntry) {
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex])> 0){
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        heap[newIndex] = (T) newEntry;
        lastIndex++;
        ensureCapacity();
    }

    private void ensureCapacity() {
        if(!isEmpty()){
            int newLength = 2 * heap.length;
            checkCapacity(newLength);
            heap = Arrays.copyOf(heap, newLength);

        }
    }

    private void checkInitialization() {
        if(initialized){
            throw new SecurityException("Uninitialized object used");
        }
    }

    @Override
    public T removeMax() {
        return null;
    }

    @Override
    public T getMax() {
        checkInitialization();
        T root = null;
        if(isEmpty())
            root = heap[1];
        return root;

    }

    @Override
    public boolean isEmpty() {
        return lastIndex < 1;
    }

    @Override
    public int getSize() {
        return lastIndex;
    }

    @Override
    public void clear() {
        checkInitialization();
        while(lastIndex > -1){
            heap[lastIndex] = null;
            lastIndex--;
        }
        lastIndex = 0;
    }
}
