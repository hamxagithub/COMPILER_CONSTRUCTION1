package com.example.compiler_construction1.DELIVERABLE1;

public class SemanticError {
    private String name;
    private String message;
    private int line;
    private String type;
    private int size;
    private int dimension;
    private int declarationLine;
    private int usageLine;

    // ✅ Constructor with only essential parameters
    public SemanticError(String name, String message, int line) {
        this(name, message, line, null, 0, 0, -1, -1);
    }

    // ✅ Full Constructor with all parameters
    public SemanticError(String name, String message, int line, String type, int size, int dimension, int declarationLine, int usageLine) {
        this.name = name;
        this.message = message;
        this.line = line;
        this.type = type;
        this.size = size;
        this.dimension = dimension;
        this.declarationLine = declarationLine;
        this.usageLine = usageLine;
    }

    // ✅ Getters
    public String getName() { return name; }
    public String getMessage() { return message; }
    public int getLine() { return line; }
    public String getType() { return type; }
    public int getSize() { return size; }
    public int getDimension() { return dimension; }
    public int getDeclarationLine() { return declarationLine; }
    public int getUsageLine() { return usageLine; }
}
