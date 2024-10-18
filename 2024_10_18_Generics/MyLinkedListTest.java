import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {

    @Test
    void testNodeNull() {
        Node<Integer> myNode = new Node<>(10);
        Assertions.assertNull(myNode.getNext());
    }
    
    @Test
    void testNodeValue() {
        Node<Integer> myNode = new Node<>(10);
        Assertions.assertEquals(10, myNode.getData());
    }
    
    @Test
    void testListNull() {
        MyLinkedList<String> myList = new MyLinkedList<>();
        Assertions.assertNull(myList.getRoot());
    }
    
    @Test
    void testListInsert() {
        MyLinkedList<Object> myList = new MyLinkedList<>();
        myList.insert(new Node<>(10));
        Assertions.assertEquals(10, myList.getRoot().getData());
    }
    
    @Test
    void testListToString() {
        MyLinkedList<Object> myList = new MyLinkedList<>();
        myList.insert(new Node<>(10));
        myList.insert(new Node<>(5));
        myList.insert(new Node<>(8));
        String expected = "8 5 10";
        Assertions.assertEquals(expected, myList.toString());
    }
    @Test
    void testSearch() {
        MyLinkedList<Object> myList = new MyLinkedList<>();
        myList.insert(new Node<>(10));
        myList.insert(new Node<>(5));
        myList.insert(new Node<>("something"));
        Assertions.assertTrue(myList.search("something"));
        Assertions.assertFalse(myList.search(8));
    }
    @Test
    void testDeletion() {
        MyLinkedList<Object> myList = new MyLinkedList<>();
        myList.insert(new Node<>(10));
        myList.insert(new Node<>(5.5));
        myList.insert(new Node<>("abc"));
        myList.insert(new Node<>("hello"));
        myList.delete(5.5);
        String expected = "hello abc 10";
        Assertions.assertEquals(expected, myList.toString());   
    }
}