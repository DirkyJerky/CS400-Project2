package src.backend;

import java.util.NoSuchElementException;


public class RedBlackBST <Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private Object Value;

    //Helper Node data type
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;
    }

public RedBlackBST() {
    //Node Helper Method
}

private boolean isRed(Node x) {
    if (x == null) return false;
    return x.color == RED;
}

// number of node in subtree rooted at x
public int size(Node x) {
    if (x == null)
        return 0;
    return x.size;
}

public int size() {
    return size(root);
}

public boolean isEmpty() {
    return root == null;
}

/***************************************************************************
 *  Standard BST search.
 ***************************************************************************/

public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
        }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }
    /***************************************************************************
     *  Red-black tree insertion.
     ***************************************************************************/
    public void put(Key key, Value val) throws IllegalAccessException {
        if (key == null)
            throw new IllegalAccessException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if(h == null)
            return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, val);
        else if (cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;

        //right-leaning links
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }
    /***************************************************************************
     *  Red-black tree deletion.
     ***************************************************************************/
    private void deleteMin() { if (isEmpty()) throw new NoSuchElementException("BST underflow")
        private void delete(Key key) {
            if (h.left == null)
                return null;

            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);

            h.left = deleteMin(h.left);
            return balance;
        }

        private Node deleteMax(Node h) {
            if (isRed(h.left))
                h = rotateRight(h);

            if (h.right == null)
                return null;

            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);

            h.right = deleteMax(h.right);

            return balance(h);
        }

        private void flipColors(Node h) {
            h.color = !h.color;
            h.left.color = !h.left.color;
            h.right.color = !h.right.color;
        }
        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = x.right.color;
            x.right.color = RED;
            x.size = h.size;
            h.size = size(h.left) + size(h.right) + 1;
            return x;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = x.left.color;
            x.left.color = RED;
            x.size = h.size;
            h.size = size(h.left) + size(h.right) + 1;
            return x;
        }
}
