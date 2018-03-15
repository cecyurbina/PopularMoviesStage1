package com.projectmovie1.data.repository.remote;

import com.projectmovie1.data.model.PopularMovieResult;
import com.projectmovie1.data.repository.service.MovieApi;
import com.projectmovie1.data.repository.service.TheMovieApi;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cecy on 11/28/17.
 */

public class MoviesRepositoryImpl implements MoviesRepository{
    private final TheMovieApi api;

    public MoviesRepositoryImpl(){
        this.api = MovieApi.getApiService();
    }


    @Override
    public Observable<PopularMovieResult> getMoviePopular(String apiKey) {
        return api.getMoviePopular(apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }

    @Override
    public Observable<PopularMovieResult> getMovieRated(String apiKey) {
        return api.getMovieRated(apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io());
    }
}
