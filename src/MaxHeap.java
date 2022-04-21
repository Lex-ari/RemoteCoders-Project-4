import java.util.Arrays;

public final class MaxHeap<T extends Comparable<? super T>> implements HeapInterface<T>{

    private T[] heap; //Array of heap entry
    private int lastIndex; //Index of last entry
    private int swaps;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    /**
     * Default constructor, creates an empty heap with defaults.
     */
    public  MaxHeap(){
        this(DEFAULT_CAPACITY); //call next constructor
    }

    /**
     * Default constructor, creates an empty heap with an initial capacity.
     * @param initialCapacity array size allocated for the heap array.
     */
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

    /**
     * Optimal method constructor. Takes in an array and calls reheap().
     * @param entries array to create a heap of.
     */
    public MaxHeap(T[] entries){
        this(entries.length);
        checkCapacity(entries.length);
        lastIndex = entries.length;
        for(int i = 0; i < lastIndex; i++){
            heap[i + 1] = entries[i];
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

    /**
     * Ensures that there is always space in the heap array. If array gets filled, simply doubles the array.
     */
    private void ensureCapacity() {
        if(lastIndex >= heap.length - 1){
            int newCapacity = 2 * heap.length;
            checkCapacity(newCapacity);
            heap = Arrays.copyOf(heap, newCapacity);
        }
    }

    /**
     * Ensures that heap has been initialized properly w/o errors.
     */
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

    /**
     * Uses a root node and compares to it's "largest" child. Swaps when necessary, and repeats for the swapped node.
     * @param rootIndex root node to start comparing children from
     */
    private void reheap(int rootIndex){
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done  && (leftChildIndex <= lastIndex)){
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0){ // when the rightChild is bigger than largerChild (rightChild > largerChild)
                largerChildIndex = rightChildIndex;
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
    /**
     * method which returns the number of swaps performed during the instantion of a maxheap
     * @return the integer value of swaps
     */
    public int getSwaps(){
        return swaps;
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

    /**
     * Ensures that current capacity does not exceed maximum capacity.
     * Default Capacity is minimum capacity.
     * @param capacity
     */
    private void checkCapacity(int capacity) {
        if (capacity < DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
        } else if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("the capacity is larger than: " + MAX_CAPACITY);
        }
    }

    /**
     * Returns a copy of the heap array,
     * @return
     */
    public T[] toArray(){
        T[] returnArray = Arrays.copyOf(heap, heap.length - 1);
        return returnArray;
    }
}
