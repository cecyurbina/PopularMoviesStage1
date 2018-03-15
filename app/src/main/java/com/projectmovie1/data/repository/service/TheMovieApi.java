package com.projectmovie1.data.repository.service;

import com.projectmovie1.data.model.comments.ResponseComments;
import com.projectmovie1.data.model.PopularMovieResult;
import com.projectmovie1.data.model.videos.ResponseVideos;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cecy on 9/19/17.
 */

public interface TheMovieApi {

    @GET("movie/popular/")
    Observable<PopularMovieResult> getMoviePopular(@Query("api_key") String apiKey);

    @GET("movie/top_rated/")
    Observable<PopularMovieResult> getMovieRated(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Observable<ResponseVideos> getVideos(@Path("movie_id") Integer movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Observable<ResponseComments> getComments(@Path("movie_id") Integer movieId, @Query("api_key") String  apiKey);


}
