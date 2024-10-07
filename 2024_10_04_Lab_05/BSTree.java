package com.gradescope.bst;
import java.util.ArrayList;

public class BSTree implements BTNode {
    private BTNode left, right;
    private int value;

    public BSTree(int value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public BTNode getLeft() {
        return this.left;
    }

    public BTNode getRight() {
        return this.right;
    }

    public void setRight(BTNode node) {
        this.right = node;
    }

    public void setLeft(BTNode node) {
        this.left = node;
    }

    public void addNode(BTNode node, int value) {
        if (value < node.getValue()) {
            if (node.getLeft() == null) node.setLeft(new BSTree(value));
            else addNode(node.getLeft(), value);
        } else {
            if (node.getRight() == null) node.setRight(new BSTree(value));
            else addNode(node.getRight(), value);
        }
    }

    public boolean search(BTNode node, int value) {
        // end of tree, base case, value not in BST
        if (node == null) return false; 
        
        // found value
        if (node.getValue() == value) return true;
        
        // keep looking
        if (value < node.getValue()) return search(node.getLeft(), value);
        else return search(node.getRight(), value);
        
    }
    
    public void toList(BTNode root, ArrayList<Integer> result) {
        if(root == null) {
            return;
        }
        toList(root.getLeft(), result);
        result.add(root.getValue());
        toList(root.getRight(), result);
    }
}