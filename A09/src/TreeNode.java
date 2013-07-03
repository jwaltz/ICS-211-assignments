/**
 * A single binary tree node.
 * <p/>
 * Each node has both a left or right child, which can be null.
 *
 * @author Zach Tomaszewski
 */
public class TreeNode<E> {

    private E data;
    private TreeNode<E> left;
    private TreeNode<E> right;

    /**
     * Constructs a new node with the given data and references to the
     * given left and right nodes.
     */
    public TreeNode(E data, TreeNode<E> left, TreeNode<E> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Constructs a new node containing the given data.
     * Its left and right references will be set to null.
     */
    public TreeNode(E data) {
        this(data, null, null);
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
     * Returns this Node's left child.
     * If there is no left child, returns null.
     */
    public TreeNode<E> getLeft() {
        return left;
    }

    /**
     * Causes this Node to point to the given left child Node.
     */
    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    /**
     * Returns this nodes right child.
     * If there is no right child, returns null.
     */
    public TreeNode<E> getRight() {
        return right;
    }

    /**
     * Causes this Node to point to the given right child Node.
     */
    public void setRight(TreeNode<E> right) {
        this.right = right;
    }
}
