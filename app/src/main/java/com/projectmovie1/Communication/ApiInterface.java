package com.projectmovie1.Communication;

import com.projectmovie1.Models.Comments.ResponseComments;
import com.projectmovie1.Models.PopularMovieResult;
import com.projectmovie1.Models.Videos.ResponseVideos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cecy on 9/19/17.
 */

public interface ApiInterface {

    @GET("movie/popular/")
    Call<PopularMovieResult> getMoviePopular(@Query("api_key") String apiKey);

    @GET("movie/top_rated/")
    Call<PopularMovieResult> getMovieRated(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos/")
    Call<ResponseVideos> getVideos(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews/")
    Call<ResponseComments> getComments(@Query("api_key") String apiKey);


}
