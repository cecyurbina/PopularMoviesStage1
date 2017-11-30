package com.projectmovie1.data.repository.remote;

import com.projectmovie1.data.model.comments.ResponseComments;
import com.projectmovie1.data.repository.service.MovieApi;
import com.projectmovie1.data.repository.service.TheMovieApi;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cecy on 11/28/17.
 * InfoSync
 */

public class CommentsRepositoryImpl implements CommentsRepository{
    private final TheMovieApi api;

    public CommentsRepositoryImpl(){
        this.api = MovieApi.getApiService();
    }

    @Override
    public Observable<ResponseComments> getComments(String apiKey, Integer movieId) {
        return api.getComments(movieId, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());
    }
}
