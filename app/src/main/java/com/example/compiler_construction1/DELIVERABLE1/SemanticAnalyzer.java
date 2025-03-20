package com.example.compiler_construction1.DELIVERABLE1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemanticAnalyzer {

    private static Map<String, SymbolTableEntry> symbolTable = new HashMap<>();

    public static List<SemanticError> analyze(String code) {
        List<SemanticError> errors = new ArrayList<>();

        if (symbolTable == null) {
            symbolTable = new HashMap<>();
        }
        symbolTable.clear();

        String[] lines = code.split(";\\s*");
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("int") || line.startsWith("char")) {
                errors.addAll(handleDeclaration(line));
            } else {
                errors.addAll(handleUsage(line));
            }
        }
        return errors;
    }

    private static List<SemanticError> handleDeclaration(String line) {
        List<SemanticError> errors = new ArrayList<>();
        String[] parts = line.split("\\s+|=");
        if (parts.length < 2) return errors;

        String type = parts[0];
        String name = parts[1];
        int size = type.equals("int") ? 2 : 1;
        int dimension = line.contains("[") ? 1 : 0;

        if (symbolTable.containsKey(name)) {
            errors.add(new SemanticError(name, "Multiple declaration", -1));
        } else {
            symbolTable.put(name, new SymbolTableEntry(name, type, size, dimension));
        }
        return errors;
    }

    private static List<SemanticError> handleUsage(String line) {
        List<SemanticError> errors = new ArrayList<>();
        String[] parts = line.split("=");
        if (parts.length < 2) return errors;

        String name = parts[0].trim();
        if (!symbolTable.containsKey(name)) {
            errors.add(new SemanticError(name, "Undeclared variable", -1));
        }
        return errors;
    }
}
