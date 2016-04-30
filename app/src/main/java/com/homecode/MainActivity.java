package com.homecode;

import android.animation.Animator;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ArrayList<DataItem> dataItemArrayList = new ArrayList<>();
    private int scrolledDistance = 0, width = 0, height = 0;

    //    https://gist.github.com/nickbutcher/e36ee3b3d6580ddb7a0a
//    https://gist.github.com/nickbutcher/b3962f0d14913e9746f2
    //    https://github.com/google/android-ui-toolkit-demos/blob/master/RecyclerView/RecyclerViewAnimations/app/src/main/java/examples/android/com/recyclerviewanimations/MainActivity.java
//    https://github.com/lisawray/fontbinding
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = getResources().getAssets().open("navigation.json");
                    int size = inputStream.available();
                    byte[] bytes = new byte[size];
                    inputStream.read(bytes);
                    inputStream.close();
                    String json = new String(bytes);
                    JSONArray jsonArray = new JSONArray(json);
                    Log.i(TAG, "Length :" + jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        dataItemArrayList.add(new DataItem(jsonObject.getString("title"), jsonObject.getString("description"), jsonObject.getString("class")));
                    }
                    recyclerAdapter = new RecyclerAdapter(dataItemArrayList);
                    recyclerView.setAdapter(recyclerAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrolledDistance += dy;

            }
        });


        final FloatingActionButton actionButton = (FloatingActionButton) findViewById(R.id.fab);

        actionButton.animate().alpha(0f).scaleY(0f).scaleX(0f).setDuration(3000L).setInterpolator(new LinearOutSlowInInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                actionButton.animate().alpha(1f).scaleX(1f).scaleY(1f);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
//        ViewCompat.animate(actionButton).setDuration(1000L).translationZ(10);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revelAnimation(recyclerView);

            }
        });


    }

    private void revelAnimation(final View view) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final Animator animator = ViewAnimationUtils.createCircularReveal(view, width / 2, height / 2, 0f, Math.max(width, height) / 2);
            animator.setInterpolator(new FastOutSlowInInterpolator());
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();
        }
    }

}
