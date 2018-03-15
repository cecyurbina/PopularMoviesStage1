package com.projectmovie1.presenter;

import rx.Subscription;

/**
 * Created by cecy on 2/19/18.
 */

public interface MainPresenter {
    void onCreate();
    void onDestroy();
    void getPopularMovies();
    void getTopRatedMovies();
    void addSubscription(Subscription subscription);

}
