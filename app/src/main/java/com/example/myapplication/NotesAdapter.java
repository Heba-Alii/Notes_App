package com.example.myapplication;

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
    boolean isChecked;
    int isFavorite = 0;

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
        NotesEntity notesEntity = notes.get(position);
        holder.course_name.setText(notesEntity.getCourseName());
        holder.course_desc.setText(notesEntity.getCourseDesc());
        holder.favorite_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChecked) {
                //   isChecked= notesEntity.isFavorite();
                    holder.favorite_img.setImageResource(R.drawable.baseline_favorite_24);


                    //  notes.get(holder.getAdapterPosition());

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            int id = (int) getItemId(position);
                            favInterface.isFavorite(notes);
                            NotesBuilder notesBuilder = NotesBuilder.getInstance(view.getContext());
                            notesBuilder.notesDao().update(isChecked, id);

                        }
                    });


                } else {
                    holder.favorite_img.setImageResource(R.drawable.baseline_favorite_border_24);

                }
                isChecked = !isChecked;

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
