package com.homecode;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.homecode.adapters.SceneDetailsAdapter;
import com.homecode.fragments.ListFragment;
import com.homecode.fragments.SceneDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class SceneRDetailsActivity extends AppCompatActivity implements SceneDetailFragment.FragmentListener, ListFragment.OnFragmentInteractionListener {
    private ImageView imageView;
    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene_r_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        assert toolbar != null;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

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

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        setUpViewPager(pager);
        tabLayout.setupWithViewPager(pager);


    }

    private void setUpViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        SceneDetailFragment sceneDetailFragment = new SceneDetailFragment();
        adapter.addFragments(sceneDetailFragment, "Scene");
        ListFragment listFragment = new ListFragment();
        adapter.addFragments(listFragment, "List");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void expandAppBar() {
        appBarLayout.setExpanded(true, true);
    }

    private static class PagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragments = new ArrayList<>();
        List<String> titles = new ArrayList<>();


        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragments(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
