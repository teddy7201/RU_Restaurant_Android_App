package com.example.software_meth_project_5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_meth_project_5.Model.Flavor;

import java.util.List;

public class BeverageAdapter extends RecyclerView.Adapter<BeverageAdapter.BeverageViewHolder> {
    private final List<Flavor> flavors;
    private final OnFlavorClickListener listener;
    private Flavor selectedFlavor;

    public interface OnFlavorClickListener {
        void onFlavorClick(Flavor flavor);
    }

    public BeverageAdapter(List<Flavor> flavors, OnFlavorClickListener listener) {
        this.flavors = flavors;
        this.listener = listener;
        this.selectedFlavor = null;
    }

    @NonNull
    @Override
    public BeverageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new BeverageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeverageViewHolder holder, int position) {
        Flavor flavor = flavors.get(position);
        holder.flavorName.setText(flavor.getFlavorName());

        // Highlight selected flavor
        if (flavor.equals(selectedFlavor)) {
            holder.itemView
                    .setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.selected_item));
        } else {
            holder.itemView.setBackgroundColor(
                    ContextCompat.getColor(holder.itemView.getContext(), android.R.color.transparent));
        }

        holder.itemView.setOnClickListener(v -> {
            selectedFlavor = flavor;
            notifyDataSetChanged(); // Update all items to show selection
            listener.onFlavorClick(flavor);
        });
    }

    @Override
    public int getItemCount() {
        return flavors.size();
    }

    public Flavor getSelectedFlavor() {
        return selectedFlavor;
    }

    static class BeverageViewHolder extends RecyclerView.ViewHolder {
        TextView flavorName;

        BeverageViewHolder(@NonNull View itemView) {
            super(itemView);
            flavorName = itemView.findViewById(android.R.id.text1);
        }
    }
}