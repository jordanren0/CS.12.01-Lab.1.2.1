import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {

    private T[] array;
    private int size;


    public DynamicArray(Class<T> type){
        this.array = (T[]) Array.newInstance(type, 50);
        this.size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public T get(int i){
        if(i < 0 || i >= size){
            return null;
        }
        return array[i];
    }

    public boolean contains(T element){
        return indexOf(element) != -1;
    }

    public void add(T element){
        if(size == array.length) changeSize();
        array[size++] = element;
    }

    public void add(int i, T element){
        if(i < 0 || i > size) throw new IllegalArgumentException("Array Index out of Bounds!");
        if(size == array.length) changeSize();
        System.arraycopy(array, i, array, i +1, size - i);
        array[i] = element;
        size++;
    }

    public void set(int i, T element){
        if(i < 0 || i >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        array[i] = element;
    }

    public T remove(int i){
        if(i < 0 || i >= size) throw new IllegalArgumentException("Array Index out of Bounds!");
        T remove = array[i];
        System.arraycopy(array, i +1, array, i, size - i - 1);
        array[size--] = null;
        return remove;
    }

    public T remove(T element){
        int i = indexOf(element);
        if(i != -1) remove(i);
        return element;
    }

    public T removeAll(T element){
        for(int i = 0; i < size; i++){
            if(element.equals(array[i])){
                remove(i);
            }
        }
        return element;
    }

    public void clear(){
        Arrays.fill(array, null);
        size = 0;
    }

    private int indexOf(T element){
        for(int i = 0; i < size; i++){
            if(element.equals(array[i])) return i;
        }
        return -1;
    }

    private void changeSize(){
        int newSize = array.length + 1;
        T[] arr = (T[]) Array.newInstance(array.getClass().getComponentType(), newSize);
        System.arraycopy(array, 0, arr, 0, size);
        array = arr;
    }

}
