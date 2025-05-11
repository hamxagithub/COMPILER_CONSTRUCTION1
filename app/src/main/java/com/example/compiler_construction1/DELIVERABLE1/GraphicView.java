package com.example.compiler_construction1.DELIVERABLE1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class GraphicView extends View {
    private ASTNode ast;
    private Paint paint;
    private float nodeRadius = 40; // Size of each node
    private float verticalSpacing = 150; // Space between tree levels
    private float horizontalSpacing = 100; // Space between nodes at the same level

    public GraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        paint.setStrokeWidth(5);
    }

    public void setAST(ASTNode ast) {
        this.ast = ast;
        invalidate(); // Refresh view
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (ast != null) {
            drawAST(canvas, ast, getWidth() / 2, 100, getWidth() / 4);
        }
    }

    private void drawAST(Canvas canvas, ASTNode node, float x, float y, float offset) {
        if (node == null) return;

        // Draw the node (circle)
        paint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, nodeRadius, paint);

        // Draw node text inside the circle
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(node.getValue(), x, y + 10, paint);

        // Draw children dynamically
        List<ASTNode> children = node.getChildren();
        if (!children.isEmpty()) {
            float childX = x - ((children.size() - 1) * horizontalSpacing) / 2; // Center children

            for (ASTNode child : children) {
                canvas.drawLine(x, y + nodeRadius, childX, y + verticalSpacing - nodeRadius, paint);
                drawAST(canvas, child, childX, y + verticalSpacing, offset / 1.5f);
                childX += horizontalSpacing; // Spread children
            }
        }
    }
}
