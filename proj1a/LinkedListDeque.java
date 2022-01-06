/** Circular sentinel version. */
public class LinkedListDeque<T> {
    public int size;
    private TNode sentinel;
    /** nested class TNode. */
    private class TNode {
        public TNode prev;
        public T item;
        public TNode next;
        public TNode(TNode p, T i, TNode n) {
            prev = p;
            item = i;
            next = n;
        }
        /** create a special TNode to help create the null sentinel. */
        /** not sure. */
        public TNode() {
            prev = this;
            next = this;
        }
    }
    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        size = 0;
        /** we must have a T i in TNode so the sentinel can not be certain. */
        sentinel = new TNode();
    }
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        size++;
        TNode p = new TNode(sentinel, item, sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
    }
    /** Adds an item of type T to the end of the deque. */
    public  void addLast(T item) {
        size++;
        TNode p = new TNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
    }
    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        TNode p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }
    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null. */
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        TNode p = sentinel.next;
        p.next.prev = sentinel;
        sentinel.next = p.next;
        return p.item;
    }
    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null. */
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        TNode p = sentinel.prev;
        p.prev.next = sentinel;
        sentinel.prev = p.prev;
        return p.item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        TNode p = sentinel;
        if (index >= size | index < 0) {
            return null;
        }
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.next.item;
    }
    /** Same as get, but uses recursion. */
    /** not sure. */
    public T getRecursive(int index) {
        if (index >= size | index < 0) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        }
        LinkedListDeque<T> l = new LinkedListDeque<>();
        l.size = size;
        l.sentinel = sentinel;
        l.removeFirst();
        return l.getRecursive(index - 1);
    }
}
