package com.projectmovie1.presenter;


import rx.Subscription;

/**
 * Created by cecy on 11/28/17.
 */

public interface CommentsPresenter {
    void onCreate();
    void onDestroy();
    void getComments(Integer movieId);
    void addSubscription(Subscription subscription);
}
