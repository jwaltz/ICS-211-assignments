
/**
 * PyramidStack is a special kind of stack that only allows pushing of new
 * elements that are smaller than the current top of the stack.
 *
 * @author Jonathan Waltz
 * @version 1.0
 */
public class PyramidStack<E extends Comparable<E>> extends Stack<E> {

    /**
     * Push method that will only push new argument onto the stack if the
     * argument is "smaller" than the top of the stack.
     *
     * @param item Generic-type object
     * @throws IllegalArgumentException
     */
    @Override
    public void push(E item) throws IllegalArgumentException {
        if (this.size() != 0) {
            if (item.compareTo(this.peek()) > 0) {
                throw new IllegalArgumentException();
            } else {
                super.push(item);
            }
        } else {
            super.push(item);
        }
    }

    /**
     * Returns the number of items in the stack that come before (ie, are
     * "smaller") than the given item in that item's natural ordering.
     *
     * @param item Generic-type object
     * @return
     */
    public int countBefore(E item) {
        if (this.size == 0) {
                return 0;
        } else {
            return countBefore(item, this.top);
        }
    }

    /**
     * Recursive helper method.
     *
     * @param item Generic-type object
     * @param node Current node to compare item with
     * @return
     */
    private int countBefore(E item, Node<E> node) {
        int count = 0;
        
        if (item.compareTo(node.getData()) <= 0) {
            return 0;
        } else if (item.compareTo(node.getData()) > 0) {
            count++;
            if (node.getNext() != null) {
                count += countBefore(item, node.getNext());
            }
        }
        return count;
    }

    /**
     * String representation of the contents of the stack in bottom-to-top
     * order.
     *
     * @return
     */
    @Override
    public String toString() {
        if (this.size == 0) {
            return "";
        } else {
            String stackContents = getContents(this.top, this.size);
            //gets rid of the final comma for aesthetics
            return stackContents.substring(0, stackContents.length() - 2);
        }
    }

    /**
     * Helper method for toString that recursively inventories the contents of
     * the stack.
     *
     * @param node Node we want the data from
     * @param size the size of the stack
     * @return
     */
    private String getContents(Node<E> node, int size) {
        if (size == 0) {
            return "";
        } else {
            String contents = getContents(node.getNext(), size - 1);
            contents += node.getData().toString() + ", ";
            return contents;
        }
    }

    /**
     * Recursively builds a string containing the lengths of each node's data.
     *
     * @return
     */
    public String getLengths() {
        if (this.size == 0) {
            return "";
        } else {
            return getLengths(this.top);
        }
    }

    /**
     * Private helper method to recursively build a string containing the
     * lengths of each node's data.
     *
     * @param node Node holding data we want the length of
     * @return
     */
    private String getLengths(Node<E> node) {
        String lengths = "";
        if (node.getNext() == null) {
            lengths += "" + node.getData().toString().length();
        } else {
            lengths += getLengths(node.getNext()) + ", " + node.getData().toString().length();
            return lengths;
        }
        return lengths;
    }
}
