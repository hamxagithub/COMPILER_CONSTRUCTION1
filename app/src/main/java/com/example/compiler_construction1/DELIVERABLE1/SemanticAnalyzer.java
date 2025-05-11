package com.example.compiler_construction1.DELIVERABLE1;

import java.util.*;

public class SemanticAnalyzer {
    private static final Map<String, SymbolTableEntry> symbolTable = new HashMap<>();

    public static List<SemanticError> analyze(String code) {
        List<SemanticError> errors = new ArrayList<>();
        symbolTable.clear();

        String[] lines = code.split(";\\s*");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("int") || line.startsWith("char")) {
                errors.addAll(handleDeclaration(line, i + 1));
            } else {
                errors.addAll(handleUsage(line, i + 1));
            }
        }
        return errors;
    }

    private static List<SemanticError> handleDeclaration(String line, int lineNumber) {
        List<SemanticError> errors = new ArrayList<>();
        String[] parts = line.split("\\s+|=");
        if (parts.length < 2) return errors;

        String type = parts[0];
        String name = parts[1];
        int size = type.equals("int") ? 2 : 1;
        int dimension = line.contains("[") ? 1 : 0;

        if (symbolTable.containsKey(name)) {
            errors.add(new SemanticError(name, "Multiple declaration", lineNumber));
        } else {
            symbolTable.put(name, new SymbolTableEntry(name, type, size, dimension));
        }
        return errors;
    }

    private static List<SemanticError> handleUsage(String line, int lineNumber) {
        List<SemanticError> errors = new ArrayList<>();
        String[] parts = line.split("=");

        if (parts.length < 2) return errors;

        String name = parts[0].trim();
        if (!symbolTable.containsKey(name)) {
            errors.add(new SemanticError(name, "Undeclared variable", lineNumber));
        }
        return errors;
    }

    public static Map<String, SymbolTableEntry> getSymbolTable() {
        return symbolTable;
    }
}
