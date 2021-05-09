package com.example.reminiscence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RvGalleryAdapter extends RecyclerView.Adapter<RvGalleryAdapter.ViewHolder> {


    Context context;
    String[] personNameList;
    int[] personPhotoList;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowName;
        ImageView rowPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rowName = itemView.findViewById(R.id.nameShow);
            rowPhoto = itemView.findViewById(R.id.imageShow);
        }
    }

    public RvGalleryAdapter(Context context, String[] personNameList, int[] personPhoto){

        this.context = context;
        this.personNameList = personNameList;
        this.personPhotoList = personPhoto;
    }

    @NonNull
    @Override
    public RvGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_image_gallery,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvGalleryAdapter.ViewHolder holder, int position) {

        holder.rowName.setText(personNameList[position]);
        holder.rowPhoto.setImageResource(personPhotoList[position]);

    }

    @Override
    public int getItemCount() {
        return personNameList.length;
    }
}
