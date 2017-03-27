public class FrequencyBoard
{
    // Instance variables
    private int numEntries;     // number of actual entries
    private BTNode[] board;     // array of BTNode entries (words and frequencies)

    // Constructor
    /**
     * Constructs an empty frequency board with the given capacity for storing entries
     */
    public FrequencyBoard(int capacity)
    {
        this.numEntries = 0;
        this.board = new BTNode[capacity];
    }

    // Methods
    /**
     * Adds a new BTNode to the frequency board if it's high enough
     */
    public void add(BTNode node)
    {
        int newFrequency = node.getWordCounter();
        // Is the new word count a high frequency?
        if (this.numEntries < this.board.length || newFrequency > this.board[numEntries - 1].getWordCounter())
            {
                if (this.numEntries < this.board.length) // no word drops from the board
                    {
                        this.numEntries++;
                    }
                // Shift any lower words righward to make room for the new entry
                int j = numEntries - 1;
                while (j > 0 && this.board[j - 1].getWordCounter() < newFrequency)
                    {
                        this.board[j] = this.board[j - 1];
                        j--;
                    }
                board[j] = node;
            }
    }

    /**
     * Returns a string representation of this FrequencyBoard
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");
        for (int j = 0; j < numEntries; j++)
            {
                if (j > 0)
                    {
                        sb.append(", ");
                    }
                sb.append(board[j]);
            }
        sb.append("]");
        return sb.toString();
    }
}
