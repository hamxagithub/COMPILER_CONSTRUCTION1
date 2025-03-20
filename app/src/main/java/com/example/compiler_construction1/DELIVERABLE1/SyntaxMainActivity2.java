/* SyntaxMainActivity2.java */
package com.example.compiler_construction1.DELIVERABLE1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.compiler_construction1.R;

import java.util.List;

public class SyntaxMainActivity2 extends AppCompatActivity {
    private EditText codeInput;
    private Button analyzeButton;
    private RecyclerView recyclerView;
    private SyntaxAdapter syntaxAdapter;
    private GraphicView astView;
    private Parser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syntax_main2);

        codeInput = findViewById(R.id.code_input);
        analyzeButton = findViewById(R.id.analyze_button);
        recyclerView = findViewById(R.id.recycler_view);
        astView = findViewById(R.id.ast_view);

        syntaxAdapter = new SyntaxAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(syntaxAdapter);

        parser = new Parser();

        analyzeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                analyzeCode();
            }
        });
    }

    private void analyzeCode() {
        String code = codeInput.getText().toString();
        ASTNode ast = parser.parse(code);
        List<String> errors = parser.getErrors();

        if (!errors.isEmpty()) {
            syntaxAdapter.updateData(errors);
            astView.setAST(null);
        } else {
            syntaxAdapter.updateData(errors);
            astView.setAST(ast);
        }
    }
}
