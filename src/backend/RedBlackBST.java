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
}
