package com.projectmovie1.presenter;

import com.projectmovie1.data.model.PopularMovieResult;
import com.projectmovie1.data.repository.remote.MoviesRepository;
import com.projectmovie1.data.repository.remote.MoviesRepositoryImpl;
import com.projectmovie1.ui.view.MainView;
import com.projectmovie1.utils.Utils;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cecy on 2/19/18.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView view;
    private CompositeSubscription mSubscription;
    private MoviesRepository moviesRepository;

    public MainPresenterImpl(MainView view){
        this.view = view;
        this.mSubscription = new CompositeSubscription();
        this.moviesRepository = new MoviesRepositoryImpl();
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
    public void getPopularMovies() {
        Subscription subscription = moviesRepository.getMoviePopular(Utils.API_KEY).subscribe(new Observer<PopularMovieResult>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                view.moviesError(null);
            }

            @Override
            public void onNext(PopularMovieResult responseVideos) {
                if (view != null){
                    view.moviesSuccess(responseVideos);
                }

            }
        });
        addSubscription(subscription);

    }

    @Override
    public void getTopRatedMovies() {
        Subscription subscription = moviesRepository.getMovieRated(Utils.API_KEY).subscribe(new Observer<PopularMovieResult>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                view.moviesError(null);
            }

            @Override
            public void onNext(PopularMovieResult responseVideos) {
                if (view != null){
                    view.moviesSuccess(responseVideos);
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
