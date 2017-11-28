package com.projectmovie1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GeneralInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GeneralInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralInfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "title";
    private static final String ARG_RELEASE_DATE = "release_date";
    private static final String ARG_VOTE_AVERAGE = "average";
    private static final String ARG_PLOT_SYNOPSIS = "synopsis";


    // TODO: Rename and change types of parameters
    private String title;
    private String releaseDate;
    private String voteAverage;
    private String plotSynopsis;

    private OnFragmentInteractionListener mListener;

    public GeneralInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GeneralInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralInfoFragment newInstance(String title, String releaseDate, String voteAverage, String plotSynopsis) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_RELEASE_DATE, releaseDate);
        args.putString(ARG_VOTE_AVERAGE, voteAverage);
        args.putString(ARG_PLOT_SYNOPSIS, plotSynopsis);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            releaseDate = getArguments().getString(ARG_RELEASE_DATE);
            voteAverage = getArguments().getString(ARG_VOTE_AVERAGE);
            plotSynopsis = getArguments().getString(ARG_PLOT_SYNOPSIS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_general_info, container, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_movie_title);
        TextView tvReleaseDate = (TextView) view.findViewById(R.id.tv_movie_release_date);
        TextView tvVoteAverage = (TextView) view.findViewById(R.id.tv_movie_vote_average);
        TextView tvPlotSynopsis = (TextView) view.findViewById(R.id.tv_movie_plot_synopsis);
        tvTitle.setText(title);
        tvReleaseDate.setText(releaseDate);
        tvVoteAverage.setText(voteAverage);
        tvPlotSynopsis.setText(plotSynopsis);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
