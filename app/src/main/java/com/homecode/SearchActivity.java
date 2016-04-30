package com.homecode;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private ImageView iv;
    private TextView text;
    private AnimatedVectorDrawable searchToBar;
    private AnimatedVectorDrawable barToSearch;
    private float offset;
    private Interpolator interp;
    private int duration=1000;
    private boolean expanded = false;


    ////


    private ImageView fingerPrint;
    private AnimatedVectorDrawable showFingerprint;
    private AnimatedVectorDrawable scanFingerprint;
    private AnimatedVectorDrawable fingerprintToTick;
    private AnimatedVectorDrawable fingerprintToCross;

    private boolean isDone = false;
    private boolean success = true;
    private int backgroundColor;



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        iv = (ImageView) findViewById(R.id.search);
        fingerPrint = (ImageView) findViewById(R.id.fingerprint);
        text = (TextView) findViewById(R.id.text);
        searchToBar = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_search_to_bar);
        barToSearch = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.anim_bar_to_search);
        interp = AnimationUtils.loadInterpolator(this, android.R.interpolator.linear_out_slow_in);
//        duration = getResources().getInteger(R.integer.duration_bar);
        // iv is sized to hold the search+bar so when only showing the search icon, translate the
        // whole view left by half the difference to keep it centered
        offset = -71f * (int) getResources().getDisplayMetrics().scaledDensity;
        iv.setTranslationX(offset);


        //FingerPrint

        backgroundColor = getResources().getColor(R.color.circle_default);
        showFingerprint = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.show_fingerprint);
        scanFingerprint = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.scan_fingerprint);
        fingerprintToTick = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.fingerprint_to_tick);
        fingerprintToCross = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.fingerprint_to_cross);


        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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
            }

        }catch (Exception e){

        }

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

        fingerPrint.setImageDrawable(showFingerprint);
        showFingerprint.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!scanFingerprint.isRunning()){
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
        },5000);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void animate(View view) {

        if (!expanded) {
            iv.setImageDrawable(searchToBar);
            searchToBar.start();
            iv.animate().translationX(0f).setDuration(duration).setInterpolator(interp);
            text.animate().alpha(1f).setStartDelay(duration - 100).setDuration(100).setInterpolator(interp);
        } else {
            iv.setImageDrawable(barToSearch);
            barToSearch.start();
            iv.animate().translationX(offset).setDuration(duration).setInterpolator(interp);
            text.setAlpha(0f);
        }
        expanded = !expanded;
    }


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
}
