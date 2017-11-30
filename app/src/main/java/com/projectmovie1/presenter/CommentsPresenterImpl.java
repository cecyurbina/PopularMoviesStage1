package com.projectmovie1.presenter;

import android.util.Log;

import com.projectmovie1.data.model.comments.ResponseComments;
import com.projectmovie1.data.repository.remote.CommentsRepository;
import com.projectmovie1.data.repository.remote.CommentsRepositoryImpl;
import com.projectmovie1.ui.CommentsView;
import com.projectmovie1.utils.Utils;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cecy on 11/28/17.
 */

public class CommentsPresenterImpl implements CommentsPresenter{

    private CommentsView view;
    private CompositeSubscription mSubscription;
    private CommentsRepository  commentsRepository;

    public CommentsPresenterImpl(CommentsView view){
        this.view = view;
        this.mSubscription = new CompositeSubscription();
        this.commentsRepository = new CommentsRepositoryImpl();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        this.view = null;
        mSubscription.clear();

    }

    @Override
    public void getComments(Integer movieId) {
        Subscription subscription = commentsRepository.getComments(Utils.API_KEY, movieId).subscribe(new Observer<ResponseComments>() {
            @Override
            public void onCompleted() {
                view.commentsError(null);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(">>>>", "hi");
            }

            @Override
            public void onNext(ResponseComments responseComments) {
                if (view != null){
                    view.commentsSuccess(responseComments);
                }

            }
        });
        addSubscription(subscription);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        mSubscription.add(subscription);
    }
}
