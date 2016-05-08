package com.homecode;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class PathAnimationsActivity extends AppCompatActivity {


    //FingerPrint Animations
    private ImageView fingerPrint;
    private AnimatedVectorDrawable showFingerprint;
    private AnimatedVectorDrawable scanFingerprint;
    private AnimatedVectorDrawable fingerprintToTick;
    private AnimatedVectorDrawable fingerprintToCross;
    private boolean isDone = false;
    private boolean success = true;
    private int backgroundColor;
    //FingerPrint Animations

    //Search Bar animations
    private ImageView searchImageView;
    private TextView searchText;
    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;
    private float offset;
    private Interpolator interp;
    private int duration=1000;
    private boolean expanded = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_animation);

        ImageView image_one = (ImageView) findViewById(R.id.image_one);
        ImageView image_two = (ImageView) findViewById(R.id.image_two);
        ImageView image_three = (ImageView) findViewById(R.id.image_three);

        fingerPrint = (ImageView) findViewById(R.id.fingerprint);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AnimatedVectorDrawable zeroVectorDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.hash_zero);
            image_one.setImageDrawable(zeroVectorDrawable);
            zeroVectorDrawable.start();

//            AnimatedVectorDrawable vectorDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.drawable_avd_hash_9016);
//            image_two.setImageDrawable(vectorDrawable);
//            vectorDrawable.start();

            AnimatedVectorDrawable vectorDrawableCompat = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.simple_vector);
            image_two.setImageDrawable(vectorDrawableCompat);
            vectorDrawableCompat.start();


        }


        //FingerPrint
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            backgroundColor = getResources().getColor(R.color.circle_default);
            showFingerprint = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.show_fingerprint);
            scanFingerprint = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.scan_fingerprint);
            fingerprintToTick = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.fingerprint_to_tick);
            fingerprintToCross = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.fingerprint_to_cross);
            try {


                scanFingerprint.registerAnimationCallback(new Animatable2.AnimationCallback() {
                    @Override
                    public void onAnimationStart(Drawable drawable) {
                        super.onAnimationStart(drawable);
                    }

                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        if (success) {
                            fingerPrint.setImageDrawable(fingerprintToTick);
                            fingerprintToTick.start();
                            setCircleColor(getResources().getColor(R.color.circle_success));
                        } else {
                            fingerPrint.setImageDrawable(fingerprintToCross);
                            fingerprintToCross.start();
                            setCircleColor(getResources().getColor(R.color.circle_error));
                        }
                        success = !success;
                    }
                });


            } catch (Exception e) {

            }

            fingerPrint.setImageDrawable(showFingerprint);
            showFingerprint.start();
 /*scanFingerprint.addListener(new AnimatorListenerAdapter() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animation) {
                if (success) {
                    iv.setImageDrawable(fingerprintToTick);
                    fingerprintToTick.start();
                    setCircleColor(getColor(R.color.circle_success));
                } else {
                    iv.setImageDrawable(fingerprintToCross);
                    fingerprintToCross.start();
                    setCircleColor(getColor(R.color.circle_error));
                }
                success = !success;
            }
        });*/
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (!scanFingerprint.isRunning()) {
                            if (success) {
                                fingerPrint.setImageDrawable(fingerprintToTick);
                                fingerprintToTick.start();
                                setCircleColor(getResources().getColor(R.color.circle_success));
                            } else {
                                fingerPrint.setImageDrawable(fingerprintToCross);
                                fingerprintToCross.start();
                                setCircleColor(getResources().getColor(R.color.circle_error));
                            }
                            success = !success;
                        }
                    }
                }
            }, 5000);
        }

        //search Animations
        searchImageView = (ImageView) findViewById(R.id.search);
        searchText = (TextView) findViewById(R.id.text);
        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);
        interp = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
//        duration = getResources().getInteger(R.integer.duration_bar);
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        offset = -71f * (int) getResources().getDisplayMetrics().scaledDensity;
        searchImageView.setTranslationX(offset);



    }

    //=====================================FingerPrint Related=====================================

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateFingerPrint(View view) {
        if (!isDone) {
            fingerPrint.setImageDrawable(scanFingerprint);
            scanFingerprint.start();
            isDone = true;
        } else {
            fingerPrint.setImageDrawable(showFingerprint);
            showFingerprint.start();
            setCircleColor(getResources().getColor(R.color.circle_default));
            isDone = false;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setCircleColor(int to) {
        ObjectAnimator.ofArgb(fingerPrint.getBackground(), "color", backgroundColor, to).start();
        backgroundColor = to;
    }
    //=====================================FingerPrint Related=====================================

    //=====================================SearchBar Related Related=====================================
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animateSearch(View view) {

        if (!expanded) {
            searchImageView.setImageDrawable(searchToBar);
            searchToBar.start();
            searchImageView.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
            searchText.animate().alpha(1f).setStartDelay(duration - 100).setDuration(100).setInterpolator(interp);
        } else {
            searchImageView.setImageDrawable(barToSearch);
            barToSearch.start();
            searchImageView.animate().translationX(offset).setDuration(duration).setInterpolator(interp);
            searchText.setAlpha(0f);
        }
        expanded = !expanded;
    }
}
