import java.util.ArrayList;

public interface BTNode {
    public boolean search(int value, BTNode node);
    public void setRight(BTNode node);
    public void setLeft(BTNode node);
    public void addNode(BTNode node, int value);
    public int getValue();
    public BTNode getLeft();
    public BTNode getRight();
    public void toList(BTNode node, ArrayList<Integer> result);
}

