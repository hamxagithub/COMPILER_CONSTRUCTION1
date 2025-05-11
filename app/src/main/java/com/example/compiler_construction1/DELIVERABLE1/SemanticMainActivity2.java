package com.example.compiler_construction1.DELIVERABLE1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.compiler_construction1.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SemanticMainActivity2 extends AppCompatActivity {
    private EditText inputCode;
    private SemanticAdapter semanticAdapter;
    private SymbolTableAdapter symbolTableAdapter;
    private List<SemanticItem> semanticItems;
    private List<SymbolTableEntry> symbolTableEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semantic_main2);

        inputCode = findViewById(R.id.inputCode);
        Button analyzeButton = findViewById(R.id.analyzeButton);
        RecyclerView errorRecyclerView = findViewById(R.id.recyclerView);
        //RecyclerView symbolRecyclerView = findViewById(R.id.recycler_view1);

        semanticItems = new ArrayList<>();
        symbolTableEntries = new ArrayList<>();

        semanticAdapter = new SemanticAdapter(semanticItems);
        symbolTableAdapter = new SymbolTableAdapter(symbolTableEntries);

        errorRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        errorRecyclerView.setAdapter(semanticAdapter);

        //symbolRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // symbolRecyclerView.setAdapter(symbolTableAdapter);

        analyzeButton.setOnClickListener(v -> analyzeCode());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void analyzeCode() {
        String code = inputCode.getText().toString();
        List<SemanticError> errors = SemanticAnalyzer.analyze(code);
        Map<String, SymbolTableEntry> symbolTable = SemanticAnalyzer.getSymbolTable();

        semanticItems.clear();
        for (SemanticError error : errors) {
            semanticItems.add(new SemanticItem(error.getName(), error.getType(), error.getSize(),
                    error.getDimension(), error.getDeclarationLine(), error.getUsageLine(), error.getMessage()));
        }
        semanticAdapter.notifyDataSetChanged();

        symbolTableEntries.clear();
        symbolTableEntries.addAll(symbolTable.values());
        symbolTableAdapter.notifyDataSetChanged();
    }
}
