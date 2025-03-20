/* GraphicView.java */
package com.example.compiler_construction1.DELIVERABLE1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GraphicView extends View {
    private ASTNode ast;
    private Paint paint;

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

        paint.setColor(Color.BLUE);
        canvas.drawText(node.getValue(), x, y, paint);

        if (node.getLeft() != null) {
            canvas.drawLine(x, y + 10, x - offset, y + 100, paint);
            drawAST(canvas, node.getLeft(), x - offset, y + 100, offset / 2);
        }
        if (node.getRight() != null) {
            canvas.drawLine(x, y + 10, x + offset, y + 100, paint);
            drawAST(canvas, node.getRight(), x + offset, y + 100, offset / 2);
        }
    }
}
