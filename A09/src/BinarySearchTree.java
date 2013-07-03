import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A binary search tree (BST) is a sorted ADT that uses a binary
 * tree to keep all elements in sorted order.  If the tree is
 * balanced, performance is very good: O(lg n) for most operations.
 * If unbalanced, it performs more like a linked list: O(n).
 *
 * @author Zach Tomaszewski
 * @author Jonathan Waltz
 */
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

    private TreeNode<E> root = null;
    private int size = 0;

    /**
     * Creates an empty tree.
     */
    public BinarySearchTree() {
    }

    public BinarySearchTree(Collection<E> collection) {
        List<E> colAsList = new ArrayList<E>(collection);
        Collections.shuffle(colAsList);
        for (E item : colAsList) {
            this.add(item);
        }
    }

    /**
     * Returns an iterator over a set of elements of type T.
     *
     * @return an Iterator.
     */
    public BinarySearchTreeIterator iterator() {
        return new BinarySearchTreeIterator();
    }


    /**
     * Returns whether this BST contains the given item.
     */
    public boolean contains(E item) {
        TreeNode<E> curr = this.root;
        while (curr != null) {
            if (item.compareTo(curr.getData()) < 0) {
                curr = curr.getLeft();
            } else if (item.compareTo(curr.getData()) > 0) {
                curr = curr.getRight();
            } else {
                //found it
                return true;
            }
        }
        return false;  //did not find it
    }


    /**
     * Adds the given item to this BST.
     */
    public void add(E item) {
        this.root = add(item, root);
        this.size++;
    }

    private TreeNode<E> add(E item, TreeNode<E> subtree) {
        if (subtree == null) {
            return new TreeNode<E>(item);
        } else {
            if (item.compareTo(subtree.getData()) <= 0) {
                subtree.setLeft(add(item, subtree.getLeft()));
            } else {
                subtree.setRight(add(item, subtree.getRight()));
            }
            return subtree;
        }
    }

    /**
     * Returns the greatest (earliest right-most node) of the given tree.
     */
    private E findMax(TreeNode<E> n) {
        if (n == null) {
            return null;
        } else if (n.getRight() == null) {
            //can't go right any more, so this is max value
            return n.getData();
        } else {
            return findMax(n.getRight());
        }
    }

    /**
     * Returns item from tree that is equivalent (according to compareTo)
     * to the given item.  If item is not in tree, returns null.
     */
    public E get(E item) {
        return get(item, this.root);
    }

    /**
     * Finds it in the subtree rooted at the given node.
     */
    private E get(E item, TreeNode<E> node) {
        if (node == null) {
            return null;
        } else if (item.compareTo(node.getData()) < 0) {
            return get(item, node.getLeft());
        } else if (item.compareTo(node.getData()) > 0) {
            return get(item, node.getRight());
        } else {
            //found it!
            return node.getData();
        }
    }

    /**
     * Removes the first equivalent item found in the tree.
     * If item does not exist to be removed, throws IllegalArgumentException().
     */
    public void remove(E item) {
        this.root = remove(item, this.root);
    }

    private TreeNode<E> remove(E item, TreeNode<E> node) {
        if (node == null) {
            //didn't find item
            throw new IllegalArgumentException(item + " not found in tree.");
        } else if (item.compareTo(node.getData()) < 0) {
            //go to left, saving resulting changes made to left tree
            node.setLeft(remove(item, node.getLeft()));
            return node;
        } else if (item.compareTo(node.getData()) > 0) {
            //go to right, saving any resulting changes
            node.setRight(remove(item, node.getRight()));
            return node;
        } else {
            //found node to be removed!
            if (node.getLeft() == null && node.getRight() == null) {
                //leaf node
                return null;
            } else if (node.getRight() == null) {
                //has only a left child
                return node.getLeft();
            } else if (node.getLeft() == null) {
                //has only a right child
                return node.getRight();
            } else {
                //two children, so replace the contents of this node with max of left tree
                E max = findMax(node.getLeft());  //get max value
                node.setLeft(remove(max, node.getLeft())); //and remove its node from tree
                node.setData(max);
                return node;
            }
        }
    }

    /**
     * Returns the number of elements currently in this BST.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns a single-line representation of this BST's contents.
     * Specifically, this is a comma-separated list of all elements in their
     * natural Comparable ordering.  The list is surrounded by [] characters.
     */
    @Override
    public String toString() {
        return "[" + toString(this.root) + "]";
    }

    private String toString(TreeNode<E> n) {
        //would have been simpler to use Iterator... but not implemented yet.
        if (n == null) {
            return "";
        } else {
            String str = "";
            str += toString(n.getLeft());
            if (!str.isEmpty()) {
                str += ", ";
            }
            str += n.getData();
            if (n.getRight() != null) {
                str += ", ";
                str += toString(n.getRight());
            }
            return str;
        }
    }

    /**
     * Produces a more graphical string representation of the BST. Uses '<' to indicate left child and
     * '>' to indicate right child.
     *
     * @return Graphical representation of the BST
     */
    public String toFullString() {
        if (this.root == null) {
            return "";
        }
        List<String> dirList = new ArrayList<String>();
        toFullString(root, "|", dirList);
        String fullString = "";
        for (String str : dirList) {
            fullString += str + "\n";
        }
        return fullString;
    }

    private void toFullString(TreeNode<E> n, String dirs, List<String> dirList) {
        dirList.add(dirs + n.getData());
        if (n.getLeft() != null) {
            toFullString(n.getLeft(), dirs + "<", dirList);
        }
        if (n.getRight() != null) {
            toFullString(n.getRight(), dirs + ">", dirList);
        }
    }

    /**
     * Iterator associated with the BST.
     */
    public class BinarySearchTreeIterator implements Iterator<E> {
        /**
         * Used to process the BST in-order.
         */
        private Deque<TreeNode<E>> stack;
        /**
         * The most recent node the method next has iterated over.
         */
        private TreeNode<E> last = null;

        /**
         * Constructs an iterator.
         */
        private BinarySearchTreeIterator() {
            this.stack = new ArrayDeque<TreeNode<E>>();
            if (BinarySearchTree.this.size() > 0) {
                populateStack(root);
            }
        }

        private void populateStack(TreeNode<E> n) {
            stack.push(n);
            if (n.getLeft() != null) {
                populateStack(n.getLeft());
            }
        }

        /**
         * Returns true if the iteration has more elements.
         * (In other words, returns true if next() would return an element rather than throwing an exception.)
         *
         * @return true if the iteration has more elements
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("Next element does not exist");
            }
            last = stack.peek();
            stack.pop();
            if (last.getRight() != null) {
                populateStack(last.getRight());
            }
            return last.getData();
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator (optional operation).
         * This method can be called only once per call to next(). The behavior of an iterator is unspecified if the
         * underlying collection is modified while the iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws IllegalStateException if the next method has not yet been called, or the remove method has already
         *                               been called after the last call to the next method
         */
        public void remove() {
            if (last == null) {
                throw new IllegalStateException("Cannot remove at this time");
            }
            BinarySearchTree.this.remove(last.getData());
            last = null;
        }
    }
}