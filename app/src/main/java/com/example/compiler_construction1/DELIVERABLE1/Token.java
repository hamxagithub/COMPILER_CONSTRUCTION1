package com.example.compiler_construction1.DELIVERABLE1;

public class Token {
    private String tokenValue;
    private String tokenType;
    private int lineNumber;

    public Token(String tokenValue, String tokenType, int lineNumber) {
        this.tokenValue = tokenValue;
        this.tokenType = tokenType;
        this.lineNumber = lineNumber;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public String getTokenType() {
        return tokenType;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
