import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A class to store all distinct words from a text file
 */

public class BinarySearchTree
{
    // Instance variables
    private BTNode root; // reference to the root of this BST
    private int size;    // number of nodes in this BST
    private FrequencyBoard board; // a board containing the ten most frequent words

    // Constructor
    /**
     * Constructs an empty BST
     */
    public BinarySearchTree()
    {
        this.root = null;
        this.size = 0;
    }

    // Accessor methods
    /**
     * Returns the size (i.e., number of nodes) of this BST
     */
    public int size()
    {
        return this.size;
    }

    /**
     * Returns the reference to the root of this BST (or null if this BST is empty)
     */
    public BTNode root()
    {
        return this.root;
    }

    /**
     * Returns the reference to the parent of the given node (or null if the
     * given node is the root)
     */
    public BTNode parent(BTNode node)
    {
        return node.getParent();
    }

    /**
     * Returns the reference to the left child of the given node (or null if no
     * child exists)
     */
    public BTNode left(BTNode node)
    {
        return node.getLeft();
    }

    /**
     * Returns the reference to the right child of the given node (or null if no
     * child exists)
     */
    public BTNode right(BTNode node)
    {
        return node.getRight();
    }
    // Query methods
    /**
     * Tests whether this BST is empty
     */
    public boolean isEmpty()
    {
        return this.size() == 0;
    }
    
    /**
     * Returns true if the given node represents the root of the tree
     */
    public boolean isRoot(BTNode node)
    {
        return node == this.root();
    }

    /** Returns true if the given node has one or more children, false
     * otherwise.
     */
    /*boolean isInternal(BTNode node)
    {
    }*/

    /**
     * Returns true if the given node does not have any children.
     */
    boolean isExternal(BTNode node)
    {
        return this.numChildren(node) == 0;
    }

    /**
     * Returns the number of children of the given node
     */
    public int numChildren(BTNode node)
    {
        int count = 0;
        if (this.left(node) != null)
            {
                count++;
            }
        if (this.right(node) != null)
            {
                count++;
            }
        return count;
    }
    
    // Update methods
    /**
     * Places the given word at the root of this tree and returns its reference
     */
    public BTNode addRoot(String word)
    {
        this.root = new BTNode(word, null, null, null);
        this.size = 1;
        return root;
    }

    /**
     * Creates a new left child (which stores the given word) to the given
     * node. Returns the reference to the new child
     */
    public BTNode addLeft(BTNode node, String word)
    {
        BTNode parent = node;
        BTNode child = new BTNode(word, null, null, parent);
        parent.setLeft(child);
        this.size++;
        return child;
    }

    /**
     * Creates a new right child (which stores the given word) to the given
     * node. Returns the reference to the new child
     */
    public BTNode addRight(BTNode node, String word)
    {
        BTNode parent = node;
        BTNode child = new BTNode(word, null, null, parent);
        parent.setRight(child);
        this.size++;
        return child;
    }

    /**
     * Returns the height of the subtree rooted at the given node
     */
    public int height(BTNode node)
    {
        if (node == null)
            {
                return 0;
            }
        else
            {
                return 1 + Math.max(this.height(node.getLeft()), this.height(node.getRight()));
            }
    }
    
    /**
     * Performs the following operations:
     *
     * 1) Reads from &lt;fileName&gt; word by word
     *
     * 2) For each new word found in &lt;fileName&gt;, a new BTNode is created
     * and added to T according to the rules of BST insertion
     *
     * 3) For each duplicate word, the wordCounter of the corresponding
     * BTNode is incremented
     */

    public void readIn(String fileName) throws FileNotFoundException
    {
        // Create a Scanner to read the words in the file
        Scanner sc = new Scanner(new File("WisdomForRoad.txt"));
        
        while(sc.hasNext())
            {
                String s = sc.next();
                // If the current word ends in punctuation
                if (s.endsWith(".") || s.endsWith(",") || s.endsWith(";"))
                    {
                        int len = s.length();
                        s = s.substring(0, len - 1);
                    }

                if (this.isEmpty())
                    {
                        this.addRoot(s.toLowerCase());
                    }
                else 
                    {
                        BTNode tmpNode = this.search(this.root, s.toLowerCase());
                        // The search resulted in a match (the node does exist
                        // in this BST; just increase the count)
                        if (tmpNode.element().equals(s.toLowerCase()))
                            {
                                tmpNode.increaseWordCounter();
                            }
                        // The search did not result in a match. Insert the word
                        // into the BST
                        else
                            {
                                this.insert(this.root, s.toLowerCase());
                            }
                    }
            }
    }

    /**
     * Finds and returns the length of the longest search path in T
     */
    public int maxSearchPath()
    {
        return this.height(this.root);
    }

    /**
     * Prints all the distinct words found in &lt;fileName&gt;, in sorted order
     */
    public void printWordsSorted()
    {
        printWordsSortedRec(this.root);
        System.out.println(""); // empty line so that terminal prompt is not on
                                // same line
    }

    // Auxillary method for printWordsSorted()
    private void printWordsSortedRec(BTNode node)
    {
        if (node == null)
            {
                return;
            }
        printWordsSortedRec(node.getLeft());
        System.out.print(node.element() + " ");
        printWordsSortedRec(node.getRight());
    }

    /**
     * Finds and prints the ten most common words from &lt;fileName&gt;,
     * together with their respective wordCounter-s
     */
    public void printTenMostCommonWords()
    {
        FrequencyBoard board = new FrequencyBoard(10);
        printTenMostCommonWordsRecur(this.root, board);
        System.out.println(this.board);
    }

    // Auxillary method for printTenMostCommonWords()
    private void printTenMostCommonWordsRecur(BTNode node, FrequencyBoard board)
    {
        if (node == null)
            {
                return;
            }
        printTenMostCommonWordsRecur(node.getLeft(), board);
        board.add(node);
        this.board = board;
        printTenMostCommonWordsRecur(node.getRight(), board);
    }

    /**
     * Returns a reference to the node containing the given word if that word
     * occurs in this BST. Otherwise returns a leaf node, which is the last node
     * that was checked (and which doesn't match).
     */
    public BTNode search(BTNode node, String word)
    {
        // Case one: The given word occurs in this BST
        // Case two: We've reached an external node; no more nodes to search
        // Case three: We need to search to the left, but the left reference is null
        // Case four: We need to search to the right, but the right reference is null
        // Note: In cases two, three, and four, the given word does not in this BST
        if ( (word.compareTo(node.element()) == 0) ||
             (this.isExternal(node)) ||
             (word.compareTo(node.element()) < 0 && this.left(node) == null) ||
             (word.compareTo(node.element()) > 0 && this.right(node) == null) )
            {
                return node;
            }
        // We need to search to the left, and there exists a left node to search
        else if (word.compareTo(node.element()) < 0 && this.left(node) != null)
            {
                return search(this.left(node), word); // recur on left subtree
            }
        // We need to search to the right, and there exists a right node to search
        else
            {
                return search(this.right(node), word); // recur on right subtree
            }
    }

    /**
     * Inserts the given word into this BST if and only if the word does not
     * already occur in the BST
     */
    public void insert(BTNode node, String word)
    {
        // If the given word is less than current node's word
        if (word.compareTo(node.element()) < 0)
            {
                if (this.left(node) == null)
                    {
                        BTNode tmpNode = this.addLeft(node, word);
                        tmpNode.increaseWordCounter();
                        return;
                    }
                // recur on left subtree
                else
                    {
                        insert(this.left(node), word);
                    }
            }
        else                    // we know that word > node.element()
            {
                if (this.right(node) == null)
                    {
                        BTNode tmpNode = this.addRight(node, word);
                        tmpNode.increaseWordCounter();
                        return;
                    }
                // recur on right subtree
                else
                    {
                        insert(this.right(node), word);
                    }
            }
    }
}
