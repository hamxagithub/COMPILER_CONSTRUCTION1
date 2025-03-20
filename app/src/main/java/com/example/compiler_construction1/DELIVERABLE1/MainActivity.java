package com.example.compiler_construction1.DELIVERABLE1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.compiler_construction1.R;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        Button lexicalButton = findViewById(R.id.lexical_button);
        Button syntaxButton = findViewById(R.id.syntax_button);
        Button semanticButton = findViewById(R.id.semantic_button);

        lexicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LEXICALMainActivity2.class);
                startActivity(intent);
            }
        });
        syntaxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent=new Intent(MainActivity.this, SEMANTICMainActivity2.class);
                //startActivity(intent);
            }
        });
        semanticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, SyntaxMainActivity2.class);
                startActivity(intent);
            }
        });

    }
}