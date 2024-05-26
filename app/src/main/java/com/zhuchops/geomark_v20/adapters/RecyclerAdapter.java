package com.zhuchops.geomark_v20.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.models.GeoLayer;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private final OnItemClickListener listener;
    private List<GeoLayer> items;
    private LayoutInflater inflater;

    public interface OnItemClickListener {
        void onItemClick(GeoLayer layer);
    }

    public RecyclerAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<GeoLayer> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layer_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        GeoLayer item = items.get(position);
        holder.imageView.setImageResource(R.drawable.no_image_sign);
        holder.nameView.setText(item.getName());
        holder.descriptionView.setText(item.getDescription());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView nameView;
        final TextView descriptionView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewOfLayer);
            nameView = itemView.findViewById(R.id.nameViewOfLayer);
            descriptionView = itemView.findViewById(R.id.descriptionViewOfLayer);
        }
    }
}
