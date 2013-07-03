
/**
 * A doubly-linked Node. Each node contains a data element and pointers to the
 * node before and after it in the linked list.
 *
 * @author Zach Tomaszewski and Jonathan Waltz
 */
public class DLNode<E> {

    private E data;
    private DLNode<E> next;
    private DLNode<E> prev;

    /**
     * Constructs a new node with the given data and links.
     */
    public DLNode(DLNode<E> prev, E data, DLNode<E> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

    /**
     * Construct a new node with the given data. Next and previous links will be
     * null.
     */
    public DLNode(E data) {
        this(null, data, null);
    }

    /**
     * Returns the item currently stored in this node.
     */
    public E getData() {
        return data;
    }

    /**
     * Overwrites the item stored in this Node with the given data item.
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * Returns the Node after this one in the linked list structure. If there is
     * no next Node, returns null.
     */
    public DLNode<E> getNext() {
        return next;
    }

    /**
     * Causes this Node to point to the given next Node.
     */
    public void setNext(DLNode<E> next) {
        this.next = next;
    }

    /**
     * Return the Node before this one in the linked list, or null if there is
     * no such node.
     */
    public DLNode<E> getPrevious() {
        return prev;
    }

    /**
     * Sets the given prev node as the node before this on in the linked list.
     */
    public void setPrevious(DLNode<E> prev) {
        this.prev = prev;
    }
}