package com.example.compiler_construction1.DELIVERABLE1;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {
    private String value;
    private ASTNode left;
    private ASTNode right;
    private List<ASTNode> children; // New list for multiple children

    public ASTNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.children = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }

    public List<ASTNode> getChildren() {
        return children;
    }

    public void addChild(ASTNode child) {
        this.children.add(child);
    }

    /**
     * Generates a hierarchical representation of the AST as a list of strings.
     */
    public List<String> getNodeRepresentation() {
        List<String> representation = new ArrayList<>();
        buildRepresentation(this, representation, 0);
        return representation;
    }

    /**
     * Recursively builds the hierarchical representation of the AST.
     */
    private void buildRepresentation(ASTNode node, List<String> representation, int level) {
        if (node == null) return;

        // Create indentation for hierarchy representation
        StringBuilder nodeText = new StringBuilder();
        for (int i = 0; i < level; i++) {
            nodeText.append("  ");
        }

        nodeText.append(node.getValue());
        representation.add(nodeText.toString());

        buildRepresentation(node.getLeft(), representation, level + 1);
        buildRepresentation(node.getRight(), representation, level + 1);

        for (ASTNode child : node.children) {
            buildRepresentation(child, representation, level + 1);
        }
    }

    /**
     * Test method to print AST representation.
     */
    public void printTree() {
        List<String> representation = getNodeRepresentation();
        for (String line : representation) {
            System.out.println(line);
        }
    }
}
