package com.example.notesapp.Adopters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.Models.Notes;
import com.example.notesapp.NoteClickListner;
import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {

    Context context;
    List<Notes> list;
    NoteClickListner listner;

    public NotesListAdapter(Context context, List<Notes> list, NoteClickListner listner) {
        this.context = context;
        this.list = list;
        this.listner = listner;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_cards,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
    holder.textview_title.setText(list.get(position).getTitle());
    holder.textview_title.setSelected(true);

    holder.notes_content.setText((list.get(position).getNotes()));

    holder.notes_date.setText(list.get(position).getDate());
    holder.notes_date.setSelected(true);

        if (list.get(position).isPinned()){
            holder.imageview_pinned.setImageResource(R.drawable.pin_icon);
        }else {
            holder.imageview_pinned.setImageResource(0);

        }

        int color = getRandomColor();
        holder.notes_card.setCardBackgroundColor(holder.itemView.getResources().getColor(color,null));

        holder.notes_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notes_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listner.onLongClick(list.get(holder.getAdapterPosition()),holder.notes_card);
                return true;
            }
        });

    }

    private int getRandomColor(){
        List<Integer> colorCodes = new ArrayList<>();
        colorCodes.add(R.color.color1);
        colorCodes.add(R.color.color2);
        colorCodes.add(R.color.color3);
        colorCodes.add(R.color.color4);
        colorCodes.add(R.color.color5);

        Random random = new Random();
        int num = random.nextInt(colorCodes.size());
        return colorCodes.get(num);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filtered(List<Notes> filteredList){
        list = filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder{

    CardView notes_card;
    TextView textview_title,notes_content,notes_date;
    ImageView imageview_pinned;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_card =  itemView.findViewById(R.id.notes_card);
        textview_title = itemView.findViewById(R.id.textview_title);
        notes_content = itemView.findViewById(R.id.notes_content);
        notes_date = itemView.findViewById(R.id.notes_date);
        imageview_pinned= itemView.findViewById(R.id.imageview_pinned);


    }
}

