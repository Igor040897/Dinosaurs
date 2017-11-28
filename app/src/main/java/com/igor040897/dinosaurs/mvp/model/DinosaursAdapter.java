package com.igor040897.dinosaurs.mvp.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.igor040897.dinosaurs.API.DinoDate.Dino;
import com.igor040897.dinosaurs.API.DinoDate.Dino_;
import com.igor040897.dinosaurs.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DinosaursAdapter extends RecyclerView.Adapter<DinosaursAdapter.ItemViewHolder> {
    private final List<Dino_> items = new ArrayList<>();

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final Dino_ item = items.get(position);
        holder.name.setText(item.getDino_title());
        holder.description.setText(item.getDinoAbout());
        Picasso.with(holder.itemView.getContext()).load(item.getDinoImage().getSrc()).resize(350, 300).into(holder.imageDino);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(final List<Dino> items) {
        for (Dino dino : items) {
            this.items.add(dino.getDino());
        }
        notifyDataSetChanged();
    }

    public void add(final Dino_ item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_name)
        TextView name;
        @BindView(R.id.item_description)
        TextView description;
        @BindView(R.id.image_dino)
        ImageView imageDino;

        ItemViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
