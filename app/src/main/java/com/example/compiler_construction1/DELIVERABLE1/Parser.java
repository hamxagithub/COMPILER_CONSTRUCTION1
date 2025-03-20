/* Parser.java */
package com.example.compiler_construction1.DELIVERABLE1;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<String> errors;

    public Parser() {
        errors = new ArrayList<>();
    }

    public ASTNode parse(String code) {
        errors.clear();
        code = code.trim();

        if (!code.endsWith(";")) {
            errors.add("Syntax Error: Expected format 'int a = 10;'");
            return null;
        }

        String[] parts = code.replace(";", "").split("=");
        if (parts.length != 2 || !parts[0].trim().startsWith("int ")) {
            errors.add("Syntax Error: Incorrect variable declaration");
            return null;
        }

        String varName = parts[0].trim().split(" ")[1];
        String value = parts[1].trim();

        ASTNode root = new ASTNode("=");
        root.setLeft(new ASTNode(varName));
        root.setRight(new ASTNode(value));
        return root;
    }

    public List<String> getErrors() {
        return errors;
    }
}
