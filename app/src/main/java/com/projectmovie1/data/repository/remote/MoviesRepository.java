package com.projectmovie1.data.repository.remote;

import com.projectmovie1.data.model.PopularMovieResult;

import rx.Observable;

/**
 * Created by cecy on 11/28/17.
 */

public interface MoviesRepository {
    Observable<PopularMovieResult> getMoviePopular(String apiKey);
    Observable<PopularMovieResult> getMovieRated(String apiKey);

}
