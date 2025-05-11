/*SemanticItem*/

package com.example.compiler_construction1.DELIVERABLE1;

public class SemanticItem {
    private String name;
    private String type;
    private int size;
    private int dimension;
    private int declarationLine;
    private int usageLine;
    private String message;

    public SemanticItem(String name, String type, int size, int dimension, int declarationLine, int usageLine, String message) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.dimension = dimension;
        this.declarationLine = declarationLine;
        this.usageLine = usageLine;
        this.message = message;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getSize() { return size; }
    public int getDimension() { return dimension; }
    public int getDeclarationLine() { return declarationLine; }
    public int getUsageLine() { return usageLine; }
    public String getMessage() { return message; }
}
