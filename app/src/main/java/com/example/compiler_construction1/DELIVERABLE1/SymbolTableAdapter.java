package com.example.compiler_construction1.DELIVERABLE1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.compiler_construction1.R;
import java.util.List;

public class SymbolTableAdapter extends RecyclerView.Adapter<SymbolTableAdapter.ViewHolder> {
    private final List<SymbolTableEntry> symbolTableEntries;

    public SymbolTableAdapter(List<SymbolTableEntry> symbolTableEntries) {
        this.symbolTableEntries = symbolTableEntries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_symbol, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SymbolTableEntry entry = symbolTableEntries.get(position);
        holder.name.setText("Name: " + entry.getName());
        holder.type.setText("Type: " + entry.getType());
        holder.size.setText("Size: " + entry.getSize());
        holder.dimension.setText("Dimension: " + (entry.getDimension() > 0 ? "Array" : "Single"));
    }

    @Override
    public int getItemCount() {
        return symbolTableEntries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, size, dimension;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.symbolName);
            type = itemView.findViewById(R.id.symbolType);
            size = itemView.findViewById(R.id.symbolSize);
            dimension = itemView.findViewById(R.id.symbolDimension);
        }
    }
}
