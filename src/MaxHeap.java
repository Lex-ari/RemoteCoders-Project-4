import java.util.Arrays;

public final class MaxHeap<T extends Comparable<? super T>> implements HeapInterface<T>{

    private T[] heap; //Array of heap entry
    private int lastIndex; //Index of last entry
    private int swaps;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public  MaxHeap(){
        this(DEFAULT_CAPACITY); //call next constructor
    }
    public  MaxHeap(int initialCapacity){
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
    public MaxHeap(T[] entries){
        checkCapacity(entries.length);

        lastIndex = entries.length;

        for(int i = 0; i < lastIndex; i++){
            this.add(entries[i]);
        }
        for(int i = lastIndex / 2; i > 0; i--){
            reheap(i);
        }

        initialized = true;

    }



    @Override
    public int add(T newEntry)
    {
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while ( (parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
            swaps++;
        }

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
        return swaps;
    }

    private void ensureCapacity() {
        if(lastIndex >= heap.length){
            int newCapacity = 2 * heap.length;
            checkCapacity(newCapacity);
            heap = Arrays.copyOf(heap, newCapacity);
        }
    }


    private void checkInitialization() {
            if(!initialized){
                throw new SecurityException("Maxheap is not initialized property");
            }
    }

    @Override
    public T removeMax() {
        checkInitialization();
        T root = null;
        if(!isEmpty()){
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }

    private void reheap(int rootIndex){
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done  && (leftChildIndex <= lastIndex)){
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0){ // when the rightChild is bigger than largerChild (rightChild > largerChild)
                largerChildIndex = rightChildIndex;
                swaps++;
            }if(orphan.compareTo(heap[largerChildIndex]) < 0){ //when rootIndex is larger than largerChild (rootIndex > largerChild)
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
                swaps++;
            }
            else
                done = true;

        }
        heap[rootIndex] = orphan;
    }
    public int getSwaps(){
        int temp = swaps;
        swaps = 0;
        return temp;
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
    private void checkCapacity(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        } else if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("the capacity is larger than: " + MAX_CAPACITY);
        }
    }

    public T[] toArray(){
        T[] returnArray = Arrays.copyOf(heap, heap.length);
        return returnArray;
    }
}
