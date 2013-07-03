/**
 * A single node in a linked list data structure.
 * <p>
 * Contains a data element and a reference to the next node in the structure.
 * If a Node does not point to another Node, its next reference will be null.
 *
 * @author Zach Tomaszewski
 */
public class Node<E> {

  private E data;
  private Node<E> next;

  /** 
   * Constructs a new node with the given data and a reference to the 
   * given next node in the linked list structure.
   */
  public Node(E data, Node<E> next) {
    this.data = data;
    this.next = next;
  }

  /** 
   * Constructs a new node containing the given data.  
   * Its next reference will be set to null. 
   */
  public Node(E data) {
    this(data, null);
  }

  /** Returns the item currently stored in this node. */
  public E getData() {
    return data;
  }

  /** Overwrites the item stored in this Node with the given data item. */
  public void setData(E data) {
    this.data = data;
  }

  /** 
   * Returns the Node after this one in the linked list structure.
   * If there is no next Node, returns null. 
   */
  public Node<E> getNext() {
    return next;
  }

  /** Causes this Node to point to the given next Node. */
  public void setNext(Node<E> next) {
    this.next = next;
  }
}
