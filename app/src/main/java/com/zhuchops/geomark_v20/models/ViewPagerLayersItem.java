package com.zhuchops.geomark_v20.models;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerLayersItem {
    private final List<RecyclerLayerItem> recyclerItemArrayList;

    public ViewPagerLayersItem(List<RecyclerLayerItem> recyclerItemArrayList) {
        this.recyclerItemArrayList = recyclerItemArrayList;
    }

    public List<RecyclerLayerItem> getRecyclerItemArrayList() {
        return recyclerItemArrayList;
    }
}
