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
import com.example.ogan.codedenim.courses.TopicActivity;
import com.example.ogan.codedenim.gson.Module;

import java.util.ArrayList;

/**
 * Created by belema on 9/7/17.
 */

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleVH> {

    Context context;
    private ArrayList<Module> moduleResult;

    public ModuleAdapter(ArrayList<Module> myDataset) {

        moduleResult = myDataset;
    }

    @Override
    public ModuleAdapter.ModuleVH onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ModuleVH(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.module_view, parent, false));

    }



    @Override
    public void onBindViewHolder(ModuleAdapter.ModuleVH holder, final int position) {

        String moduleName = moduleResult.get(position).getModuleName();
        holder.moduleName.setText("ModuleName: " + moduleName);

        String moduleDescription = moduleResult.get(position).getModuleDescription();
        holder.moduleDescription.setText(moduleDescription);

        String moduleEstimatedTime = String.valueOf(moduleResult.get(position).getExpectedTime());
        holder.moduleEstimatedTime.setText("Estimated Time: " + moduleEstimatedTime +" minutes");

        final int moduleId = moduleResult.get(position).getModuleId();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TopicActivity.class);
                intent.putExtra("moduleId", moduleId);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return moduleResult.size();
    }

    //View Holders

    public class ModuleVH extends RecyclerView.ViewHolder{

        TextView moduleName;
        TextView moduleDescription;
        TextView moduleEstimatedTime;
        CardView cardView;




        public ModuleVH(View view){
            super(view);
            context = view.getContext();
            cardView = (CardView) view.findViewById(R.id.module_card);
            moduleName = (TextView) view.findViewById(R.id.moduleName_txt);
            moduleDescription = (TextView) view.findViewById(R.id.moduleDescription_txt);
            moduleEstimatedTime = (TextView) view.findViewById(R.id.moduleEstimatedTime_txt);

        }
    }


}
