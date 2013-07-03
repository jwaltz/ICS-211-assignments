
import java.util.AbstractSequentialList;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * A custom implementation of a circular doubly-linked list with sentinel node.
 * Relies on its iterator to manage most of the implementation details.
 *
 * @author Zach Tomaszewski and Jonathan Waltz
 * @param <E>
 */
public class LinkedList<E> extends AbstractSequentialList<E>
        implements java.util.List<E> {

    private DLNode<E> sentinel;
    private int size;

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList() {
        this.sentinel = new DLNode<E>(null);
        //circular list
        this.sentinel.setNext(this.sentinel);
        this.sentinel.setPrevious(this.sentinel);
        this.size = 0;
    }

    /**
     * Constructs a LinkedList containing the same element as the given
     * Collection.
     */
    public LinkedList(Collection<E> c) {
        this();
        ListIterator<E> iter = this.listIterator(0);
        for (E item : c) {
            iter.add(item);
        }
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator<E>(index);
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * The ListIterator associated with LinkedList.
     */
    public class LinkedListIterator<T> implements ListIterator<E> {

        private DLNode<E> next;
        private DLNode<E> previous;
        private int nextIndex;
        private DLNode<E> recentlyVisited;

        /**
         * Constructs a LinkedListIterator at index 0.
         */
        private LinkedListIterator() {
            this.previous = sentinel;
            this.next = sentinel.getNext();
            this.nextIndex = 0;
        }

        /**
         * Builds a new iterator at the given index. <p> Specifically, the
         * returned iterator's nextIndex() will == index, and so the item
         * returned by the first call to the new iterator's next() will be the
         * element at the given index in the associated list. <p> If the index
         * is out of range, 0 < size <= size(), throws an
         * IndexOutOfBoundsException.
         */
        private LinkedListIterator(int index) {
            this(); //a iterator at head
            if (index < 0 || index > size()) {
                throw new IndexOutOfBoundsException("index: " + index
                        + ", size: " + size());
            } else {
                //we know we can safely advance the iterator to where it needs to be
                while (this.nextIndex < index) {
                    this.next();
                }
            }
        }

        @Override
        public boolean hasNext() {
            return this.next != sentinel;
        }

        @Override
        public E next() {
            if (this.next == sentinel) {
                //reached the end of the circular list, but should not loop around
                throw new NoSuchElementException();
            }
            E item = this.next.getData();
            this.recentlyVisited = this.next;

            //advance all 3 instance variables through the list
            this.next = this.next.getNext();
            this.nextIndex++;
            this.previous = this.previous.getNext();

            return item;
        }

        @Override
        public boolean hasPrevious() {
            return this.previous != sentinel;
        }

        @Override
        public E previous() {
            if (this.previous == sentinel) {
                //at the beginning of the list so no more previous
                throw new NoSuchElementException();
            }
            //store the data and move instance variables back through the list
            E item = this.previous.getData();
            this.recentlyVisited = this.previous;

            this.next = this.next.getPrevious();
            this.nextIndex--;
            this.previous = this.previous.getPrevious();

            return item;
        }

        @Override
        public int nextIndex() {
            if (!this.hasNext()) {
                return size;
            }
            return this.nextIndex;
        }

        @Override
        public int previousIndex() {
            if (!this.hasPrevious()) {
                return -1;
            }
            return this.nextIndex - 1;
        }

        @Override
        public void remove() {
            if (this.recentlyVisited == null) {
                throw new IllegalStateException("Cannot remove at this time");
            }
            if (this.recentlyVisited == this.previous) {
                this.nextIndex--;
            }
            this.recentlyVisited.getNext().setPrevious(this.recentlyVisited.getPrevious());
            this.recentlyVisited.getPrevious().setNext(this.recentlyVisited.getNext());
            this.next = this.recentlyVisited.getNext();
            this.previous = this.recentlyVisited.getPrevious();
            this.recentlyVisited = null;
            size--;
        }

        @Override
        public void set(E e) {
            if (this.recentlyVisited == null) {
                throw new IllegalStateException("Cannot set at this time");
            }
            this.recentlyVisited.setData(e);
        }

        @Override
        public void add(E e) {
            //insert new node at this spot between previous and next
            // (works even when adding to an empty list with only sentinel node)
            DLNode<E> toAdd = new DLNode<E>(this.previous, e, this.next);
            this.next.setPrevious(toAdd);
            this.previous.setNext(toAdd);

            //keep previous pointer just behind next
            this.previous = this.previous.getNext();
            this.nextIndex++;  //since one new element behind us now
            size++;  //list itself just got bigger

            this.recentlyVisited = null;
        }
    }
}
