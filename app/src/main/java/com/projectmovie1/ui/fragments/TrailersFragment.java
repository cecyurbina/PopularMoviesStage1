package com.projectmovie1.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectmovie1.R;
import com.projectmovie1.data.model.videos.ResponseVideos;
import com.projectmovie1.data.model.videos.Result;
import com.projectmovie1.presenter.TrailersPresenter;
import com.projectmovie1.presenter.TrailersPresenterImpl;
import com.projectmovie1.ui.adapters.TrailersAdapter;
import com.projectmovie1.ui.view.DetailMovieView;
import com.projectmovie1.ui.view.TrailersView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrailersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrailersFragment extends Fragment implements TrailersView {
    private static final String SAVED_LAYOUT_MANAGER = "key_saved_layout_manager";
    private Parcelable listState;
    private TrailersPresenter presenter;
    private Unbinder unbinder;
    private TrailersAdapter trailersAdapter;
    List<Result> trailersList = new ArrayList<>();
    private DetailMovieView mCallback;
    @BindView(R.id.rv_trailers)
    RecyclerView rvTrailers;

    public TrailersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TrailersFragment.
     */
    public static TrailersFragment newInstance(String param1, String param2) {
        return new TrailersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TrailersPresenterImpl(this);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trailers, container, false);
        unbinder = ButterKnife.bind(this, view);
        trailersAdapter = new TrailersAdapter(this, trailersList);
        rvTrailers.setAdapter(trailersAdapter);
        if (savedInstanceState != null) {
            listState = savedInstanceState.getParcelable(SAVED_LAYOUT_MANAGER);
        }
        getTrailers();
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void getTrailers() {
        presenter.getTrailers(mCallback.getMovieId());
    }

    @Override
    public void trailersSuccess(ResponseVideos responseVideos) {
        trailersList.clear();
        trailersList.addAll(responseVideos.getResults());
        trailersAdapter.notifyDataSetChanged();
        if (listState != null) {
            rvTrailers.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void trailersError(ResponseVideos responseComments) {

    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (DetailMovieView) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(SAVED_LAYOUT_MANAGER, rvTrailers.getLayoutManager().onSaveInstanceState());
    }
}
