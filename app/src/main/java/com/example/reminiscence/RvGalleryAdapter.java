package com.example.reminiscence;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class RvGalleryAdapter extends ListAdapter<PhotoName, RvGalleryAdapter.ViewHolder> {

    private AdapterView.OnItemClickListener listener;

    //Context context;
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

    RvGalleryAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<PhotoName> DIFF_CALLBACK = new DiffUtil.ItemCallback<PhotoName>() {
        @Override
        public boolean areItemsTheSame(PhotoName oldItem, PhotoName newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(PhotoName oldItem, PhotoName newItem) {

            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getUri().equals(newItem.getUri());
        }
    };



    //public RvGalleryAdapter(Context context, String[] personNameList, int[] personPhoto){

      //  this.context = context;
       // this.personNameList = personNameList;
        //this.personPhotoList = personPhoto;
   // }

    @NonNull
    @Override
    public RvGalleryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_image_gallery,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvGalleryAdapter.ViewHolder holder, int position) {

        PhotoName model = getPhotoAt(position);
        holder.rowName.setText(model.getName());
        //holder.rowPhoto.setImageURI(Uri.parse(model.getUri()));

        Picasso.with(holder.rowPhoto.getContext()).load(model.getUri()).into(holder.rowPhoto);

    }

    public PhotoName getPhotoAt(int position) {
        return getItem(position);
    }


}

