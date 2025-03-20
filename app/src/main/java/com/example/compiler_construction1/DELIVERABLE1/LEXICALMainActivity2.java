package com.example.compiler_construction1.DELIVERABLE1;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.compiler_construction1.R;
import java.util.*;
import java.util.regex.*;

public class LEXICALMainActivity2 extends AppCompatActivity {
    private EditText codeInput;
    private TokenAdapter tokenAdapter;

    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
            "int", "return", "if", "else", "while", "for", "void", "float", "char"
    ));
    private static final Set<String> OPERATORS = new HashSet<>(Arrays.asList(
            "+", "-", "*", "/", "=", "!=", "<", ">", "==", "&&", "||", "%"
    ));
    private static final Set<String> SYMBOLS = new HashSet<>(Arrays.asList(
            "(", ")", "{", "}", "[", "]", ",", ";"
    ));

    private static final Pattern IDENTIFIER_PATTERN = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lexicalmain2);

        codeInput = findViewById(R.id.code_input);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Button analyzeButton = findViewById(R.id.analyze_button);

        tokenAdapter = new TokenAdapter(new ArrayList<>());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tokenAdapter);

        analyzeButton.setOnClickListener(v -> {
            String code = codeInput.getText().toString().trim();
            if (code.isEmpty()) {
                Toast.makeText(this, "Please enter some code", Toast.LENGTH_SHORT).show();
                return;
            }
            Log.d("LexicalAnalyzer", "Analyzing input: " + code);
            analyzeLexically(code);
        });
    }

    private void analyzeLexically(String code) {
        List<Token> tokenList = tokenize(code);
        tokenAdapter.updateTokens(tokenList);
    }

    private List<Token> tokenize(String code) {
        List<Token> tokens = new ArrayList<>();
        if (code == null || code.isEmpty()) return tokens;

        String[] lines = code.split("\n");
        int lineNumber = 1;

        for (String line : lines) {
            line = removeComments(line).trim();
            if (line.isEmpty()) {
                lineNumber++;
                continue;
            }

            String[] splitCode = line.split("(?<=\\W)|(?=\\W)");
            for (String token : splitCode) {
                token = token.trim();
                if (token.isEmpty()) continue;

                if (KEYWORDS.contains(token)) {
                    tokens.add(new Token(token, "Keyword", lineNumber));
                } else if (OPERATORS.contains(token)) {
                    tokens.add(new Token(token, "Operator", lineNumber));
                } else if (SYMBOLS.contains(token)) {
                    tokens.add(new Token(token, "Symbol", lineNumber));
                } else if (IDENTIFIER_PATTERN.matcher(token).matches()) {
                    tokens.add(new Token(token, "Identifier", lineNumber));
                } else if (NUMBER_PATTERN.matcher(token).matches()) {
                    tokens.add(new Token(token, "Number", lineNumber));
                } else {
                    tokens.add(new Token(token, "Error: Invalid Token", lineNumber));
                }
            }
            lineNumber++;
        }
        return tokens;
    }

    private String removeComments(String line) {
        // Remove single-line comments (//)
        line = line.replaceAll("//.*", "");
        // Remove multi-line comments (/* ... */)
        line = line.replaceAll("/\\*.*?\\*/", "");
        return line;
    }
}
