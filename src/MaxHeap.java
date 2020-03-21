import java.util.Arrays;

// other students helped me
// https://www.geeksforgeeks.org/max-heap-in-java/

public class MaxHeap implements Heap {
    int size;

    Integer[] data;

    public MaxHeap(int capacity) {
        data = new Integer[capacity];
        size = 0;
    }

    public void MaxHeapNLogN(Integer[] data) {
        // homework
        MaxHeap heap = new MaxHeap(data.length);
        for (int i = 0; i < data.length; i++){
            heap.add(data[i]);
        }
    }

    // build a heap based on data
    // to be implemented in O(n)
    public void MaxHeapN(Integer[] data) {

        this.data = Arrays.copyOf(data, data.length);
        this.size = data.length;
        int last = (size - 2) / 2;
        for (int i = last; i >= 0; i--){
            heapDown(i);
        }
    }

    // add an item to the heap
    public boolean add(Integer item) {
        if (size >= data.length){
            return false;
        }
        data[size] = item;
        size++;
        heapUp(size - 1);
        return true;
    }
    public Integer get() {
        if (size <= 0){
            return null;
        }
        return data[0];
    }
    // remove the root item
    public Integer pop() {
        if (size <= 0){
            return null;
        }
        int num = data[0];
        data[0] = data[size - 1];
        heapDown(0);
        size--;
        return num;
    }

    private void swap(int current, int future){
        int temp = data[current];
        data[current] = data[future];
        data[future] = temp;
    }
    private int getParent(int current) {
        return (current - 1) / 2;
    }
    private int getRight(int current) {
        return 2 * current + 2;
    }
    private int getLeft(int current) {
        return 2 * current + 1;
    }
    private boolean hasLeft(int current) {
        return getLeft(current) < size;
    }
    private boolean hasRight(int current) {
        return getRight(current) < size;
    }
    private boolean hasParent(int current) {
        return getParent(current) >= 0;
    }
    private int left(int current) {
        return data[getLeft(current)];
    }
    private int right(int current) {
        return data[getRight(current)];
    }
    private int parent(int current){
        return data[getParent(current)];
    }
    private void heapDown(){
        int current = 0;
        while(hasLeft(current)){
            int smallest = getLeft(current);
            if (hasLeft(current) && right(current) < left(current)){
                smallest = getRight(current);
            } if (data[current] < data[smallest]){
                swap(current, smallest);
            } else {
                break;
            }
            current = smallest;
        }
    }
    private void heapUp(int num){
        if (num == 0){
            return;
        }
        Integer parent = (num - 1) / 2;
        if (data[num] > data[parent]){
            swap(num, parent);
            heapUp(parent);
        }
    }
    private void heapDown(int a) {
        if (a == size) {
            return;
        }
        Integer left = 2 * a + 1;
        Integer right = 2 * a + 2;
        if (right <= size - 1) {
            if (data[a] < data[left]) {                // compare parent with left child
                if (data[left] >= data[right]) {       // Compare left and right child.
                    swap(a, left);
                    heapDown(left);

                } else {                                // right larger than left
                    swap(a, right);
                    heapDown(right);

                }
            } else if (data[a] < data[right]) {         // if node is only less than right
                swap(a, right);
                heapDown(right);
            }
        }
    }

    private void heap(Integer array[], int a, int b){
        int largest = b;
        int left = 2 * b + 1;
        int right = 2 * b + 2;
        if (left < a && array[left] > array[largest]){
            largest = left;
        } if (right < a && array[right] > array[largest]){
            largest = right;
        } if (largest != b){
            int temp = array[b];
            array[b] = array[largest];
            array[largest] = temp;
        } heap(array, a, largest);
    }

    public boolean equals(Integer[] nums) {
        for (int i = 0; i < this.size; i++){
            if (this.data[i] != data[i]){
                return false;
            }
        }
        return true;
    }
}