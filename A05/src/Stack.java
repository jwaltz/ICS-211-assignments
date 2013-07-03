/**
 * A Stack ADT.  Items can be added, examined, and removed only from
 * the top of the stack.  The last item inserted is the first item
 * removed (ie, LIFO).
 *
 * @author Zach Tomaszewski
 * @param <E>  The data type of elements to be stored in this stack
 */
public class Stack<E> {

  protected Node<E> top;
  protected int size;

  /**
   * Constructs a new empty stack.
   */
  public Stack() {
    this.top = null;
    this.size = 0;
  }

  /**
   * Adds the given item to the top of this stack.
   */
  public void push(E item) {
    this.top = new Node<E>(item, top);
    this.size++;
  }

  /**
   * Returns the item currently on the top of this stack.
   *
   * @throws IllegalStateException  if the stack is currently empty.
   */
  public E peek() {
    if (this.top == null) {
      throw new IllegalStateException("Can't peek() into empty stack.");
    }
    return this.top.getData();
  }

  /**
   * Removes and returns the item currently on the top of this stack.
   *
   * @throws IllegalStateException  if the stack is currently empty.
   */
  public E pop() {
    if (this.top == null) {
      throw new IllegalStateException("Can't pop() from empty stack.");
    }
    E data = this.top.getData();
    this.top = this.top.getNext();
    this.size--;
    return data;
  }

  /**
   * Returns the number of items currently stored in this stack.
   */
  public int size() {
    return this.size;
  }
}
