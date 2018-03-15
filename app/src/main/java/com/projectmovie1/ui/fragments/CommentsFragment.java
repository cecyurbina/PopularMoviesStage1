package com.projectmovie1.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projectmovie1.R;
import com.projectmovie1.data.model.comments.ResponseComments;
import com.projectmovie1.data.model.comments.Result;
import com.projectmovie1.presenter.CommentsPresenter;
import com.projectmovie1.presenter.CommentsPresenterImpl;
import com.projectmovie1.ui.adapters.CommentsAdapter;
import com.projectmovie1.ui.view.CommentsView;
import com.projectmovie1.ui.view.DetailMovieView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentsFragment extends Fragment implements CommentsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private CommentsPresenter presenter;
    private Unbinder unbinder;
    private CommentsAdapter commentsAdapter;
    private List<Result> commentsList = new ArrayList<>();
    private DetailMovieView mCallback;


    @BindView(R.id.rv_comments)
    RecyclerView rvComments;

    public CommentsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommentsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommentsFragment newInstance(String param1, String param2) {
        return new CommentsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CommentsPresenterImpl(this);
        presenter.onCreate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comments, container, false);
        unbinder = ButterKnife.bind(this, view);
        commentsAdapter = new CommentsAdapter(this, commentsList);
        rvComments.setAdapter(commentsAdapter);
        getComments();
        return view;
    }

    @Override
    public void getComments() {
        presenter.getComments(mCallback.getMovieId());
    }

    @Override
    public void commentsSuccess(ResponseComments responseComments) {
        commentsList.clear();
        commentsList.addAll(responseComments.getResults());
        commentsAdapter.notifyDataSetChanged();

    }

    @Override
    public void commentsError(ResponseComments responseComments) {

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

}
