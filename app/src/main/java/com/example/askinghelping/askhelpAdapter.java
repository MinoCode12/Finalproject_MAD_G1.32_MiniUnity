package com.example.askinghelping;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class askhelpAdapter extends RecyclerView.Adapter<askhelpAdapter.askhelpViewHolder> {

    private Context mCtx;
    private List<askhelp> askhelpList;
    private askhelpViewHolder holder;
    private int position;

    public askhelpAdapter(Context mCtx, List<askhelp> askhelpList) {
        this.mCtx = mCtx;
        this.askhelpList = askhelpList;
    }

    @NonNull
    @Override
    public askhelpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new askhelpViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.layout_askhelp, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull askhelpViewHolder holder, int position) {

        this.holder = holder;
        this.position = position;
    }

    @Override
    public void onBindViewHolder(@NonNull askhelpViewHolder holder, int position) {
        this.holder = holder;
        this.position = position;
        askhelp a = askhelpList.get(position);

        holder.textViewDesc.setText(a.getDescription());
        holder.textViewLoc.setText(a.getLocation());
        holder.textViewTel.setText(a.getTelNo());
    }

    @Override
    public int getItemCount() {
        return askhelpList.size();
    }

    class askhelpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewDesc, textViewLoc, textViewTel;

        public askhelpViewHolder(View itemView) {
            super(itemView);

            textViewDesc = itemView.findViewById(R.id.textview_name);
            textViewLoc = itemView.findViewById(R.id.textview_brand);
            textViewTel = itemView.findViewById(R.id.textview_desc);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            askhelp ah = askhelp.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, UpdateAskhelpActivity.class);
            intent.putExtra("askhelp", ah.getClass());
            mCtx.startActivity(intent);
        }
    }
}
