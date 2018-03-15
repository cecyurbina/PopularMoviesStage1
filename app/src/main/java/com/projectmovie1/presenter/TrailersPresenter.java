package com.projectmovie1.presenter;

import rx.Subscription;

/**
 * Created by cecy on 2/19/18.
 */

public interface TrailersPresenter {
    void onCreate();
    void onDestroy();
    void getTrailers(Integer movieId);
    void addSubscription(Subscription subscription);
}
