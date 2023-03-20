package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Entity;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private List<NotesEntity> notesEntities;

    public FavoritesAdapter(List<NotesEntity> notesEntities) {
        this.notesEntities = notesEntities;
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        NotesEntity notes = notesEntities.get(position);
        holder.course_name.setText(notes.getCourseName());
        holder.course_desc.setText(notes.getCourseDesc());
        holder.imageView.setImageResource(R.drawable.baseline_favorite_24);


    }

    @Override
    public int getItemCount() {
        return notesEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView course_name;
        TextView course_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.favorite_img);
            course_name = itemView.findViewById(R.id.course_name);
            course_desc = itemView.findViewById(R.id.course_desc);
        }
    }
}
