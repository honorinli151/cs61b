public class LinkedListDeque<T> {
    public class Node {
        private T item;
        private Node pre;
        private Node nxt;

        public Node(T nn, Node ppre, Node nnxt) {
            this.item = nn;
            this.pre = ppre;
            this.nxt = nnxt;
        }

        public Node(Node ppre, Node nnxt) {
            this.pre = ppre;
            this.nxt = nnxt;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        this.sentinel = new Node(null, null);
        this.sentinel.pre = sentinel;
        this.sentinel.nxt = sentinel;
        this.size = 0;
    }
    public void addFirst(T item) {
        Node newList = new Node(item, sentinel, sentinel.nxt);
        this.sentinel.nxt.pre = newList;
        this.sentinel.nxt = newList;
        this.size++;
    }
    public void addLast(T item) {
        Node newList = new Node(item, sentinel.pre, sentinel);
        this.sentinel.pre.nxt = newList;
        this.sentinel.pre = newList;
        this.size++;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public int size() {
        return this.size;
    }
    public void printDeque() {
        Node ptr = sentinel.nxt;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.nxt;
        }
    }
    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        Node ptr = sentinel;
        for (int i = 0; i<index && ptr.nxt != sentinel; i++) {
            ptr = ptr.nxt;
        }
        return ptr.item;
    }
    public T removeFirst() {
        if (this.size == 0) {
            return null;
        }
        Node moved = this.sentinel.nxt;
        this.sentinel.nxt = this.sentinel.nxt.nxt;
        size--;
        return moved.item;
    }
    public T removeLast() {
        if (this.size == 0) {
            return null;
        }
        Node moved = this.sentinel.pre;
        this.sentinel.pre = this.sentinel.pre.pre;
        size--;
        return moved.item;
    }
    private T getRecursiveHelp(Node start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelp(start.nxt, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursiveHelp(sentinel.nxt, index);
    }

}
