package com.projectmovie1.Communication;

import com.projectmovie1.POJOS.PopularMovieResult;

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

}
