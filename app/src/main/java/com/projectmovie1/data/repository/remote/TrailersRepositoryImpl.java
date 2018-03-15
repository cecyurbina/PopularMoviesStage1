package com.projectmovie1.data.repository.remote;

import com.projectmovie1.data.model.videos.ResponseVideos;
import com.projectmovie1.data.repository.service.MovieApi;
import com.projectmovie1.data.repository.service.TheMovieApi;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cecy on 11/28/17.
 */

public class TrailersRepositoryImpl implements TrailersRepository{
    private final TheMovieApi api;

    public TrailersRepositoryImpl(){
        this.api = MovieApi.getApiService();
    }

    @Override
    public Observable<ResponseVideos> getVideos(String apiKey, Integer movieId) {
        return api.getVideos(movieId, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
