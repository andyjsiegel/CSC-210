public class MyLinkedList<T> {
    private Node<T> root;
    
    public MyLinkedList() {
        root = null;
    }
    
    public Node<T> getRoot() {
        return root;
        
    }
    
    public void insert(Node<T> newNode) {
        if (root == null) {
            root = newNode;
        } else {
            newNode.setNext(root);
            root = newNode;
        }
    }
    
    public String toString() {
        String message = "";
        Node<T> currentNode = root;
        while (currentNode != null) {
            message += currentNode.getData() + " ";
            currentNode = currentNode.getNext();
        }
        return message.trim();
    }

    public boolean search(T value) {
        if (root == null) return false;
        else {
            Node<T> currentNode = root;
            while (currentNode != null) {
                if (currentNode.getData().equals(value)) return true;
                currentNode = currentNode.getNext();
            }
        }
        return false;
    }
    public void delete(T value) {
        if (root.getData().equals(value)) {
            root = root.getNext();
        } else {
            Node<T> currentNode = root;
            Node<T> previousNode = null;
            Boolean found = false;
            while (currentNode != null && !found) {
                if (currentNode.getData().equals(value) ) {
                    found = true;
                    if (previousNode != null) {
                        previousNode.setNext(currentNode.getNext());
                    }
                } // if found node to delete
                previousNode = currentNode; // for next iteration make current previous
                currentNode = currentNode.getNext(); // get next node for current
            } // while 
        }
        
    }
}