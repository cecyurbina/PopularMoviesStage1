package com.projectmovie1.data.repository.service;

import com.projectmovie1.data.model.comments.ResponseComments;
import com.projectmovie1.data.model.PopularMovieResult;
import com.projectmovie1.data.model.videos.ResponseVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cecy on 9/19/17.
 */

public interface TheMovieApi {

    @GET("movie/popular/")
    Call<PopularMovieResult> getMoviePopular(@Query("api_key") String apiKey);

    @GET("movie/top_rated/")
    Call<PopularMovieResult> getMovieRated(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos/")
    Call<ResponseVideos> getVideos(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews/")
    Call<ResponseComments> getComments(@Query("api_key") String apiKey);


}
