package com.example.myapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private List<NotesEntity> notes;
    private FavInterface favInterface;

    public NotesAdapter(List<NotesEntity> notes, FavInterface favInterface) {
        this.notes = notes;
        this.favInterface = favInterface;
    }


    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        NotesEntity notesEntity = notes.get(position);
        holder.course_name.setText(notesEntity.getCourseName());
        holder.course_desc.setText(notesEntity.getCourseDesc());
        holder.favorite_img.setImageResource(R.drawable.baseline_favorite_border_24);
        holder.favorite_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                favInterface.isFavorite(notesEntity);
                holder.favorite_img.setImageResource(R.drawable.baseline_favorite_24);

            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favorite_img;
        TextView course_name;
        TextView course_desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            favorite_img = itemView.findViewById(R.id.favorite_img);
            course_name = itemView.findViewById(R.id.course_name);
            course_desc = itemView.findViewById(R.id.course_desc);
        }
    }
}
