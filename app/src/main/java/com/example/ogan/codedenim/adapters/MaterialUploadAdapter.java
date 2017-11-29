package com.example.ogan.codedenim.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ogan.codedenim.R;
import com.example.ogan.codedenim.fragments.AudioActivity;
import com.example.ogan.codedenim.fragments.PDFActivity;
import com.example.ogan.codedenim.fragments.VideoActivity;
import com.example.ogan.codedenim.gson.MaterialUpload;

import java.util.ArrayList;

/**
 * Created by belema on 11/20/17.
 */

public class MaterialUploadAdapter extends RecyclerView.Adapter<MaterialUploadAdapter.MaterialViewHolder> {

    private ArrayList<MaterialUpload> materialUploads;
    private Context context;

    public MaterialUploadAdapter(ArrayList<MaterialUpload> materialUploads) {
        this.materialUploads = materialUploads;
    }

    @Override
    public MaterialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MaterialViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.topic_content_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final MaterialViewHolder holder, int position) {
        final String description = materialUploads.get(position).getDescription();
        final String fileLocation = materialUploads.get(position).getFileLocation();

        holder.description.setText(description);
        holder.fileType.setText(fileLocation);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String fileType = materialUploads.get(holder.getAdapterPosition()).getFileType();
                System.out.println(fileType);
                switch (fileType){
                    case "PDF":
                        intent = new Intent(context, PDFActivity.class);
                        intent.putExtra("fileLocation", fileLocation);
                        intent.putExtra("name", description);
                        context.startActivity(intent);
                        break;
                    case "MP4":
                        intent = new Intent(context, VideoActivity.class);
                        intent.putExtra("fileLocation", fileLocation);
                        intent.putExtra("name", description);
                        context.startActivity(intent);
                        break;
                    case "MP3":
                        intent = new Intent(context, AudioActivity.class);
                        intent.putExtra("fileLocation", fileLocation);
                        intent.putExtra("name", description);
                        context.startActivity(intent);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return materialUploads.size();
    }

    class MaterialViewHolder extends RecyclerView.ViewHolder{
        private TextView description;
        private TextView fileType;
        private CardView cardView;

        private MaterialViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            description = itemView.findViewById(R.id.tv_topic_name);
            fileType = itemView.findViewById(R.id.tv_file_type);
            cardView = itemView.findViewById(R.id.topic_content_card);
        }
    }
}
