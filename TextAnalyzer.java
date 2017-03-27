import java.io.FileNotFoundException;

/**
 * Tester class for the Text Analyzer application
 */

public class TextAnalyzer
{
    public static void main(String[] args) throws FileNotFoundException
    {
        BinarySearchTree BST = new BinarySearchTree();
        BST.readIn("WisdomForRoad.txt");
        System.out.println(BST.maxSearchPath());
        BST.printWordsSorted();
        BST.printTenMostCommonWords();
    }
}
