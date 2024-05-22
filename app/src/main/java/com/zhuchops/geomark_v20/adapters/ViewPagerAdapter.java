package com.zhuchops.geomark_v20.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhuchops.geomark_v20.R;
import com.zhuchops.geomark_v20.models.ViewPagerLayersItem;
import com.zhuchops.geomark_v20.view_models.LayersListViewModel;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.RecyclerViewHolder> {

    private List<ViewPagerLayersItem> items;
    private LayoutInflater inflater;
    private LayersListViewModel viewModel;

    public ViewPagerAdapter(Context context, List<ViewPagerLayersItem> items, LayersListViewModel viewModel) {
        this.items = items;
        this.inflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layers_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        ViewPagerLayersItem item = items.get(position);
        holder.recyclerView.setAdapter(
                new RecyclerAdapter(inflater.getContext(), layer -> viewModel.onItemClicked(layer))
        );
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_view);
        }
    }
}
