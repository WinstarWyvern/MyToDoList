package com.example.mytodolist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyToDoListAdapter extends RecyclerView.Adapter<MyToDoListAdapter.ViewHolder> {
    private final ArrayList<MyToDoList> toDoListData;

    public MyToDoListAdapter(ArrayList<MyToDoList> toDoListData)
    {
        this.toDoListData = toDoListData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(listItem);

        holder.layout.setOnClickListener(view -> {
            String strTitle = toDoListData.get(holder.getAdapterPosition()).getTitle();
            String strContent = toDoListData.get(holder.getAdapterPosition()).getContent();

            int intStatus = toDoListData.get(holder.getAdapterPosition()).getImageId();
            String strStatus = getImageStatus(intStatus);

            Intent intent = new Intent(view.getContext(), MyToDoListDetail.class);
            intent.putExtra("position", holder.getAdapterPosition());
            intent.putExtra("title", strTitle);
            intent.putExtra("content", strContent);
            intent.putExtra("status", strStatus);

            parent.getContext().startActivity(intent);
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(toDoListData.get(position).getTitle());
        holder.imageView.setImageResource(toDoListData.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return toDoListData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.statusImage);
            this.textView = itemView.findViewById(R.id.textTitle);
            layout = itemView.findViewById(R.id.listLayout);
        }
    }

    public String getImageStatus(int newStatus){
        if(newStatus == android.R.drawable.ic_dialog_alert){
            return "Urgent";
        }
        else if(newStatus == android.R.drawable.ic_dialog_info){
            return "Reminder";
        }

        return "General";
    }
}
