package com.projectmovie1.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
 * Activities that contain this fragment must implement the
 * {@link TrailersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrailersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrailersFragment extends Fragment implements TrailersView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private TrailersPresenter presenter;
    private Unbinder unbinder;
    private TrailersAdapter trailersAdapter;
    List<Result> trailersList = new ArrayList<>();
    private DetailMovieView mCallback;
    @BindView(R.id.rv_trailers)
    RecyclerView rvTrailers;


    private String mParam1;
    private String mParam2;


    public TrailersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrailersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrailersFragment newInstance(String param1, String param2) {
        TrailersFragment fragment = new TrailersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
    }

    @Override
    public void trailersError(ResponseVideos responseComments) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
}
