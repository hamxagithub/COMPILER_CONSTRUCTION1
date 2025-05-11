/*SemanticAdapter.java*/

package com.example.compiler_construction1.DELIVERABLE1;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.compiler_construction1.R;

import java.util.List;

public class SemanticAdapter extends RecyclerView.Adapter<SemanticAdapter.ViewHolder> {
    private List<SemanticItem> semanticItems;

    public SemanticAdapter(List<SemanticItem> semanticItems) {
        this.semanticItems = semanticItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_semantic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SemanticItem item = semanticItems.get(position);
        holder.name.setText("Variable: " + item.getName());
        holder.type.setText("Type: " + item.getType());
        holder.message.setText("Error: " + item.getMessage());
    }

    @Override
    public int getItemCount() {
        return semanticItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, message;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.varName);
            type = itemView.findViewById(R.id.varType);
            message = itemView.findViewById(R.id.errorMessage);
        }
    }
}
