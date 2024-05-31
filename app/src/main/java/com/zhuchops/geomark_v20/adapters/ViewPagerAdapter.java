package com.zhuchops.geomark_v20.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhuchops.geomark_v20.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.RecyclerViewHolder> {

    private final List<RecyclerAdapter> innerAdapters;
    private final List<RecyclerView.ItemDecoration> itemDecorations;

    public ViewPagerAdapter(List<RecyclerAdapter> innerAdapters, List<RecyclerView.ItemDecoration> itemDecorations) {
        this.innerAdapters = innerAdapters;
        this.itemDecorations = itemDecorations;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layers_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.recyclerView.setAdapter(innerAdapters.get(position));
        for (RecyclerView.ItemDecoration itemDecoration:
             itemDecorations) {
            holder.recyclerView.addItemDecoration(itemDecoration);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.layers_recycler_view);
        }
    }
}
