package com.example.compiler_construction1.DELIVERABLE1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.compiler_construction1.R;
import java.util.List;

public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.TokenViewHolder> {
    private List<Token> tokens;

    public TokenAdapter(List<Token> tokens) {
        this.tokens = tokens;
    }

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_token, parent, false);
        return new TokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int position) {
        Token token = tokens.get(position);
        holder.tokenValue.setText("Value: " + token.getTokenValue());
        holder.tokenType.setText("Type: " + token.getTokenType());
        holder.tokenLine.setText("Line: " + token.getLineNumber());
    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }

    public void updateTokens(List<Token> newTokens) {
        this.tokens = newTokens;
        notifyDataSetChanged();
    }

    static class TokenViewHolder extends RecyclerView.ViewHolder {
        TextView tokenValue, tokenType, tokenLine;

        public TokenViewHolder(@NonNull View itemView) {
            super(itemView);
            tokenValue = itemView.findViewById(R.id.token_value);
            tokenType = itemView.findViewById(R.id.token_type);
            tokenLine = itemView.findViewById(R.id.token_line);
        }
    }
}
