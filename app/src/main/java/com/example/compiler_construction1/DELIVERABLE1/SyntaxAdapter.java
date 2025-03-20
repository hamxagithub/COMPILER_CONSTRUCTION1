/* SyntaxAdapter.java */
package com.example.compiler_construction1.DELIVERABLE1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.compiler_construction1.R;

import java.util.ArrayList;
import java.util.List;

public class SyntaxAdapter extends RecyclerView.Adapter<SyntaxAdapter.ViewHolder> {
    private List<String> syntaxErrors;

    public SyntaxAdapter() {
        this.syntaxErrors = new ArrayList<>();
    }

    public void updateData(List<String> errors) {
        this.syntaxErrors.clear();
        this.syntaxErrors.addAll(errors);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_syntax, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.syntaxText.setText(syntaxErrors.get(position));
    }

    @Override
    public int getItemCount() {
        return syntaxErrors.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView syntaxText;

        ViewHolder(View itemView) {
            super(itemView);
            syntaxText = itemView.findViewById(R.id.syntax_text);
        }
    }
}
