package com.homecode;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GoogleAnimation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_animation);

        ImageView image_one = (ImageView) findViewById(R.id.image_one);
        ImageView image_two = (ImageView) findViewById(R.id.image_two);
        ImageView image_three = (ImageView) findViewById(R.id.image_three);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AnimatedVectorDrawable vectorDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.drawable_avd_hash_9016);
            image_two.setImageDrawable(vectorDrawable);
            vectorDrawable.start();

            AnimatedVectorDrawable zeroVectorDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.hash_zero);
            image_one.setImageDrawable(zeroVectorDrawable);
            zeroVectorDrawable.start();


            AnimatedVectorDrawable vectorDrawableCompat = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.simple_vector);
            image_three.setImageDrawable(vectorDrawableCompat);
            vectorDrawableCompat.start();


        }

    }
}
