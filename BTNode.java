/**
 * A class represents a binary tree node used to store each distinct word,
 * together with its occurrence-counter.
 */

public class BTNode
{
    // Attributes
    private String word;                // A word in the file
    private int wordCounter;            // Number of occurrences of this word in
                                        // the file
    private BTNode left, right, parent; // This node's parent, and left and
                                        // right children
    // Constructor
    /**
     * Constructs a new BTNode that stores the specified word
     */
    public BTNode(String s, BTNode u, BTNode v, BTNode w)
    {
        this.wordCounter = 0;
        this.word = s;
        this.left = u;
        this.right = v;
        this.parent = w;
    }

    // Methods
    /**
     * Returns the word stored in this node
     */
    public String element()
    {
       return this.word; 
    }

    /**
     * Returns the number of occurrences of this word
     */
    public int getWordCounter()
    {
        return this.wordCounter;
    }

    /**
     * Increments the counter of this word
     */
    public void increaseWordCounter()
    {
        this.wordCounter++;
    }

    /**
     * Returns a reference to this node's left subtree
     */
    public BTNode getLeft()
    {
        return this.left;
    }

    /**
     * Sets this node's left subtree to the given node
     */
    public void setLeft(BTNode v)
    {
        this.left = v;
    }

    /**
     * Returns a reference to this node's right subtree
     */
    public BTNode getRight()
    {
        return this.right;
    }

    /**
     * Sets this node's right subtree to the given node
     */
    public void setRight(BTNode v)
    {
        this.right = v;
    }

    /**
     * Returns a reference to this node's parent
     */
    public BTNode getParent()
    {
        return this.parent;
    }

    /**
     * Sets this node's parent node to the given node
     */
    public void setParent(BTNode v)
    {
        this.parent = v;
    }

    /**
     * Returns a string representation of this node
     */
    public String toString()
    {
        return "(" + this.element() + ", " + this.getWordCounter() + ")";
    }
}
