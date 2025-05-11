package com.example.compiler_construction1.DELIVERABLE1;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Parser {
    private List<String> errors;
    private List<String> tokens;
    private int index;

    public Parser() {
        errors = new ArrayList<>();
        tokens = new ArrayList<>();
    }

    public ASTNode parse(String code) {
        errors.clear();
        tokenize(code);

        if (tokens.isEmpty()) {
            errors.add("Syntax Error: Empty input");
            return null;
        }

        return parseStatement();
    }

    private void tokenize(String code) {
        StringTokenizer tokenizer = new StringTokenizer(code, " ;{}()=+-*/<>\n", true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (!token.isEmpty()) {
                tokens.add(token);
            }
        }
        index = 0;
    }

    private ASTNode parseStatement() {
        if (index >= tokens.size()) return null;

        String token = tokens.get(index);

        // Handling Function Definition
        if (token.equals("int") || token.equals("void") || token.equals("bool")) {
            return parseFunction();
        }

        // Handling Variable Declaration
        if (token.equals("int") || token.equals("float") || token.equals("double") || token.equals("bool")) {
            return parseVariableDeclaration();
        }

        errors.add("Syntax Error: Unexpected token '" + token + "'");
        return null;
    }

    private ASTNode parseFunction() {
        if (index + 3 >= tokens.size()) {
            errors.add("Syntax Error: Incomplete function declaration.");
            return null;
        }

        String returnType = tokens.get(index++);
        String functionName = tokens.get(index++);

        if (!tokens.get(index).equals("(")) {
            errors.add("Syntax Error: Expected '(' after function name.");
            return null;
        }
        index++;

        List<ASTNode> parameters = new ArrayList<>();
        while (!tokens.get(index).equals(")")) {
            parameters.add(new ASTNode(tokens.get(index++)));
        }
        index++;

        ASTNode functionNode = new ASTNode("Function: " + functionName);
        functionNode.setLeft(new ASTNode(returnType));
        for (ASTNode param : parameters) {
            functionNode.addChild(param);
        }


        return functionNode;
    }

    private ASTNode parseVariableDeclaration() {
        String type = tokens.get(index++);
        if (index >= tokens.size()) {
            errors.add("Syntax Error: Expected variable name.");
            return null;
        }

        String varName = tokens.get(index++);
        if (index >= tokens.size() || !tokens.get(index).equals("=")) {
            errors.add("Syntax Error: Expected '=' in variable assignment.");
            return null;
        }
        index++;

        if (index >= tokens.size()) {
            errors.add("Syntax Error: Expected value after '='.");
            return null;
        }

        String value = tokens.get(index++);
        ASTNode varNode = new ASTNode("=");
        varNode.setLeft(new ASTNode(varName));
        varNode.setRight(new ASTNode(value));

        return varNode;
    }

    public List<String> getErrors() {
        return errors;
    }
}
