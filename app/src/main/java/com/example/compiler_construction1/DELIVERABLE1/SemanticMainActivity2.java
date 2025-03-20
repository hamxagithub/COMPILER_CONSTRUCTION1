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

public class SemanticMainActivity2 extends AppCompatActivity {
    private EditText inputCode;
    private SemanticAdapter adapter;
    private List<SemanticItem> semanticItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semantic_main2);

        inputCode = findViewById(R.id.inputCode);
        Button analyzeButton = findViewById(R.id.analyzeButton);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        semanticItems = new ArrayList<>();
        adapter = new SemanticAdapter(semanticItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        analyzeButton.setOnClickListener(v -> analyzeCode());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void analyzeCode() {
        String code = inputCode.getText().toString();
        List<SemanticError> errors = SemanticAnalyzer.analyze(code);

        semanticItems.clear();
        for (SemanticError error : errors) {
            semanticItems.add(new SemanticItem(error.getName(), error.getType(), error.getSize(),
                    error.getDimension(), error.getDeclarationLine(), error.getUsageLine(), error.getMessage()));
        }
        adapter.notifyDataSetChanged();
    }
}
