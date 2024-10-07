import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BSTreeTest {
    @Test
    void testToList() {
        BSTree myBST = new BSTree(10);
        myBST.addNode(myBST, 3);
        myBST.addNode(myBST, 11);
        myBST.addNode(myBST, 4);
        myBST.addNode(myBST, 20);
        myBST.addNode(myBST, 24);
        myBST.addNode(myBST, 14);
        
        ArrayList<Integer> result = new ArrayList<>();
        myBST.toList(myBST, result);
        Integer[] expected = {3, 4, 10, 11, 14, 20, 24};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.toArray()[i]);
        }
    }
}

