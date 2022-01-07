// invariants:
// The amount of memory that your program uses at any given time
// must be proportional to the number of items.
// For example, if you add 10,000 items to the deque,
// and then remove 9,999 items, you shouldn’t still be using an array
// of length 10,000ish. For arrays of length 16 or more, your usage
// factor should always be at least 25%.
// For smaller arrays, your usage factor can be arbitrarily low.
public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;
    // Creates an empty ArrayDeque.
    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T []) new Object[8];
    }
    // helper method: how to move in a circular array.
    private static class Move {
        static int front(int arrl, int p) {
            if (p == 0) {
                return (arrl - 1);
            }
            return (p - 1);
        }
        static int back(int arrl, int p) {
            if (p == (arrl - 1)) {
                return 0;
            }
            return (p + 1);
        }
    }
    // helper method: resizes the array. puts the Arraydeque in the front.
    private void resize(int objsize) {
        // try to improve performance.
        T[] newitems = (T[]) new Object[objsize];
        int len = items.length;
        int first = Move.back(len, nextFirst);
        int last = Move.front(len, nextLast);
        System.arraycopy(items, first, newitems, 0, len - first);
        System.arraycopy(items, 0, newitems, len - first, last + 1);
//        int p = Move.back(items.length, nextFirst);
//        for (int i = 0; i < size; i++) {
//            newitems[i] = items[p];
//            p = Move.back(items.length, p);
//        }
        items = newitems;
        nextFirst = objsize - 1;
        nextLast = size;
    }
    // Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = Move.front(items.length, nextFirst);
        size++;
    }
    // Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = Move.back(items.length, nextLast);
        size++;
    }
    // Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    // Returns the number of items in the deque.
    public int size() {
        return size;
    }
    // Prints the items in the deque from first to last, separated by a space.
    // Once all the items have been printed, print out a new line.
    public void printDeque() {
        int p = Move.back(items.length, nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p] + " ");
            p = Move.back(items.length, p);
        }
        System.out.println();
    }
    // Removes and returns the item at the front of the deque.
    // If no such item exists, returns null.
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((items.length > 8) && ((size / (items.length)) <= 0.25)) {
            resize(size * 2);
        }
        int first = Move.back(items.length, nextFirst);
        T remove = items[first];
        nextFirst = first;
        size--;
        return remove;
    }
    // Removes and returns the item at the back of the deque.
    // If no such item exists, returns null.
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((items.length > 8) && ((size / (items.length)) <= 0.25)) {
            resize(size * 2);
        }
        int last = Move.front(items.length, nextLast);
        T remove = items[last];
        nextLast = last;
        size--;
        return remove;
    }
    // Gets the item at the given index, where 0 is the front, 1 is the next item,
    // and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int first = Move.back(items.length, nextFirst);
        if (index < (items.length - first)) {
            return items[first + index];
        }
        return items[index - (items.length - first) ];
    }
}
