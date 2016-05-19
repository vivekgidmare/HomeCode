package com.homecode;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class SceneDetailsActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Action bar title");

        imageView = (ImageView) findViewById(R.id.image_details);
        imageView.post(new Runnable() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                int cx = imageView.getWidth() / 2;
                int cy = imageView.getHeight() / 2;
                float radius = (float) Math.hypot(cx, cy);
                Animator animator = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, 0, radius).setDuration(1000);
                imageView.setVisibility(View.VISIBLE);
                animator.start();
            }
        });




    }
}
