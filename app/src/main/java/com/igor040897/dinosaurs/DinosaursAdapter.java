package com.igor040897.dinosaurs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.igor040897.dinosaurs.API.DinoDate.Dino;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanre on 6/27/2017.
 */

public class DinosaursAdapter extends RecyclerView.Adapter<DinosaursAdapter.ItemViewHolder> {
    private final List<Dino> items = new ArrayList<>();

    @Override
    public ItemViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        final Dino item = items.get(position);
        holder.name.setText(item.getDino().getDino_title());
        holder.description.setText(item.getDino().getDinoAbout());
        Picasso.with(holder.itemView.getContext()).load(item.getDino().getDinoImage().getSrc()).resize(350, 300).into(holder.imageDino);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(final List<Dino>items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void add(final Dino item){
        this.items.add(item);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, description;
        private final ImageView imageDino;

        ItemViewHolder(final View itemView) {
            super(itemView);
//            View view = itemView.findViewById(R.id.info);
            name = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
            imageDino = itemView.findViewById(R.id.image_dino);
        }
    }
}
