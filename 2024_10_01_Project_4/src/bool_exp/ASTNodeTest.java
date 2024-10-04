package bool_exp;
// package com.gradescope.bool_exp;
// this file runs test cases for the ASTNode class to ensure it works correctly

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ASTNodeTest {
    @Test
    void testNullChildren() {
        ASTNode nodeTwoNullChildren = ASTNode.createNandNode(null, null);
        assertNull(nodeTwoNullChildren.child1);
        assertNull(nodeTwoNullChildren.child2);
    }
    
    @Test
    void testAddChildren() {
        ASTNode leftNode = ASTNode.createIdNode("left");
        ASTNode rightNode = ASTNode.createIdNode("right");
        ASTNode nodeWithChildren = ASTNode.createNandNode(leftNode, rightNode);
        assertNotNull(nodeWithChildren.child1);
        assertNotNull(nodeWithChildren.child2);
        assertEquals("ASTNode(NAND,,ASTNode(ID,left,null,null),ASTNode(ID,right,null,null))", nodeWithChildren.toString());
    }
    @Test
    void testIsIdCorrectType() {
        ASTNode idNode = ASTNode.createIdNode("id123");
        assertTrue(idNode.isId());
    }
    @Test
    void testIsIdIncorrectType() {
        ASTNode nandNode = ASTNode.createNandNode(null, null);
        assertFalse(nandNode.isId());
    }
    @Test
    void testIsNandCorrectType() {
        ASTNode nandNode = ASTNode.createNandNode(null, null);
        assertTrue(nandNode.isNand());
    }
    @Test
    void testIsNandIncorrectType() {
        ASTNode idNode = ASTNode.createIdNode("id456");
        assertFalse(idNode.isNand());
    }
    @Test
    void testGetId() {
        String id = "id789";
        ASTNode idNode = ASTNode.createIdNode(id);
        assertEquals(id, idNode.getId());
    }
}
