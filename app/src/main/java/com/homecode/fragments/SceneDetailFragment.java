package com.homecode.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homecode.R;
import com.homecode.adapters.SceneDetailsAdapter;

public class SceneDetailFragment extends Fragment {
    private FragmentListener fragmentListener;


    public SceneDetailFragment() {
        // Required empty public constructor
    }


    public static SceneDetailFragment newInstance() {
        SceneDetailFragment fragment = new SceneDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scene_detail, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.scene_recyclerview);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        SceneDetailsAdapter adapter = new SceneDetailsAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int scrolledDy = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
//
//                }
                if (scrolledDy == 0 && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.i("TAG", "ScrollDY");
                    if (fragmentListener != null)
                        fragmentListener.expandAppBar();
//                    appBarLayout.setExpanded(true, true);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrolledDy += dy;
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            fragmentListener = (FragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public interface FragmentListener {
        void expandAppBar();
    }
}
