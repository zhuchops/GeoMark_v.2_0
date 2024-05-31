package com.zhuchops.geomark_v20.adapters;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.models.GeoMark;

import java.util.List;

public class MarkRecyclerAdapter extends RecyclerView.Adapter<MarkRecyclerAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private List<GeoMark> items;

    public interface OnItemClickListener {
        void onItemClick(GeoMark mark);
        void onMoreOptionClick(View view, GeoMark mark);
    }

    public MarkRecyclerAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setItems(List<GeoMark> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mark_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GeoMark item = items.get(position);
        holder.nameView.setText(item.getName());
        String coordinates = String.format("%f, %f", item.getLatitude(), item.getLongitude());
        holder.coordinatesView.setText(coordinates);
        holder.menuButton.setOnClickListener(v -> listener.onMoreOptionClick(v, item));
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView nameView;
        final TextView coordinatesView;
        final ImageView menuButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.markNameView);
            coordinatesView = itemView.findViewById(R.id.coordinateView);
            menuButton = itemView.findViewById(R.id.bt_more_menu);
        }
    }
}
