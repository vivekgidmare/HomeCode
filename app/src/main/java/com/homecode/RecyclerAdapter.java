package com.homecode;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vivek on 29/04/16.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<DataItem> dataItems;

    public RecyclerAdapter(ArrayList<DataItem> items) {
        dataItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataItem dataItem = dataItems.get(position);
        bindCustomHolder((CustomViewHolder) holder, dataItem);
    }


    private void bindCustomHolder(CustomViewHolder holder, DataItem dataItem) {
        holder.title.setText(dataItem.getTitle());
        holder.description.setText(dataItem.getDescription());
        holder.className = dataItem.getClassName();
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    private static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        private String className;

        public CustomViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(itemView.getContext(), Class.forName(itemView.getContext().getPackageName()+"."+className));
                        itemView.getContext().startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}
