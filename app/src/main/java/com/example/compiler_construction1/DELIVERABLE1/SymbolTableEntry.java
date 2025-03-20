package com.example.compiler_construction1.DELIVERABLE1;

public class SymbolTableEntry {
    private String name;
    private String type;
    private int size;
    private int dimension;

    public SymbolTableEntry(String name, String type, int size, int dimension) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.dimension = dimension;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getSize() { return size; }
    public int getDimension() { return dimension; }
}
