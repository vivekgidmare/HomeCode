package com.homecode.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homecode.R;

import java.lang.ref.WeakReference;

/**
 * Created by vivek on 19/05/16.
 */
public class SceneDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    static WeakReference<Activity> reference;
    public SceneDetailsAdapter(Activity activity) {
        this.reference = new WeakReference<Activity>(activity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transition_view_item, parent, false), reference.get());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bindItemViewHolder((ItemViewHolder) holder, position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }
    private void bindItemViewHolder(ItemViewHolder holder, int position) {
        holder.title.setText("Title :"+position);
//        holder.description.setText("Description : " + arrayList.get(position).getDescription());

    }


    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private WeakReference<Activity> reference;
        public TextView title, description;


        public ItemViewHolder(View itemView, Activity activity) {
            super(itemView);
            reference = new WeakReference<Activity>(activity);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
