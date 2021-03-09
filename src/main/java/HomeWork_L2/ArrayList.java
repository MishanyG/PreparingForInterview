package HomeWork_L2;

public class ArrayList <T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] data;
    private int size;
    private int index;

    public ArrayList() {
        data = new Object[DEFAULT_SIZE];
    }

    public ArrayList(int capacity) {
        data = new Object[capacity];
    }

    public static void main(String[] args) {
        ArrayList <String> arrayList = new ArrayList <>();
        arrayList.add("A");
        arrayList.add("a");
        arrayList.add("B");
        arrayList.add("b");
        System.out.println(arrayList.get(0) + ", " + arrayList.get(1) + ", " + arrayList.get(2) + ", " + arrayList.get(3));
        System.out.println(arrayList.remove(2));
        arrayList.set(1, "C");
        System.out.println(arrayList.get(1));
        ArrayList <Integer> arrayList1 = new ArrayList <>(3);
        System.out.println(arrayList1.add(0, 5) + ", " + arrayList1.add(1, 3) + ", " + arrayList1.add(2, 1));
        System.out.println(arrayList1.get(0) + ", " + arrayList1.get(1) + ", " + arrayList1.get(2));
        System.out.println(arrayList1.indexOf(1));
        System.out.println(arrayList1.size());
    }

    public void add(T item) {
        if(index == data.length)
            enlargeArray();
        data[index] = item;
        index++;
        size++;
    }

    public boolean add(int index, T item) {
        if(index == data.length)
            enlargeArray();
        System.arraycopy(data, index, data, index + 1, this.index - index);
        data[index] = item;
        this.index++;
        size++;
        return true;
    }

    public void set(int index, T item) {
        checkIndex(index);
        data[index] = item;
    }

    public T get(int index) {
        checkIndex(index);
        return (T)data[index];
    }

    public boolean remove(int index) {
        checkIndex(index);
        System.arraycopy(data, index + 1, data, index, this.index - index);
        size--;
        this.index--;
        return true;
    }

    private void enlargeArray() {
        Object[] newArray = new Object[data.length * 2];
        System.arraycopy(data, 0, newArray, 0, index);
        data = newArray;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= this.index) throw new IllegalArgumentException();
    }

    public int size() {
        return size;
    }

    public int indexOf(T item) {
        int result = - 1;
        for(int i = 0; i < index; i++) {
            if(data[i].equals(item)) {
                result = i;
                break;
            }
        }
        return result;
    }
}