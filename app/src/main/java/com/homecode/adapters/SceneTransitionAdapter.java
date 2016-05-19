package com.homecode.adapters;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.homecode.DataItem;
import com.homecode.R;
import com.homecode.SceneDetailsActivity;
import com.homecode.SceneRDetailsActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by vivek on 17/05/16.
 */
public class SceneTransitionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<DataItem> arrayList = new ArrayList<>();
    static WeakReference<Activity> reference;

    public SceneTransitionAdapter(Activity activity, ArrayList<DataItem> list) {
        this.reference = new WeakReference<Activity>(activity);
        this.arrayList = list;
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

    private void bindItemViewHolder(ItemViewHolder holder, int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText("Description : " + arrayList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {
        private WeakReference<Activity> reference;
        public TextView title, description;

        public ItemViewHolder(View itemView, Activity activity) {
            super(itemView);
            reference = new WeakReference<Activity>(activity);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
//                    v.setBackgroundColor(reference.get().getResources().getColor(R.color.circle_default));
                    Pair<View, String> pt = Pair.create((View) title, "title");
                    Pair<View, String> pd = Pair.create((View) description, "description");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(reference.get(), pt, pd);
                    Intent intent = new Intent(reference.get(), SceneRDetailsActivity.class);
                    reference.get().startActivity(intent, options.toBundle());
                }
            });
        }
    }

}
