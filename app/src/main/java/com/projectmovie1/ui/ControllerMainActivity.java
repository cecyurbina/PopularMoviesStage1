package com.projectmovie1.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.projectmovie1.data.repository.service.TheMovieApi;
import com.projectmovie1.data.model.PopularMovieResult;
import com.projectmovie1.utils.ListenerResponse;
import com.projectmovie1.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cecy on 9/19/17.
 */

public class ControllerMainActivity {
    private Retrofit retrofit;
    private TheMovieApi apiService;

    public ControllerMainActivity(){
        initializeCommunicationModule();
    }

    private void initializeCommunicationModule(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        apiService = retrofit.create(TheMovieApi.class);

    }

    public void getTopRatedMovies(){
        Call<PopularMovieResult> call = apiService.getMovieRated(Utils.API_KEY);
        call.enqueue(new Callback<PopularMovieResult>() {
            @Override
            public void onResponse(Call<PopularMovieResult> call, Response<PopularMovieResult> response) {
                if (response.isSuccessful()) {
                    PopularMovieResult topRatedMoviesResult = response.body();
                    EventBus.getDefault().post(new ListenerResponse(true, topRatedMoviesResult));
                } else {
                    EventBus.getDefault().post(new ListenerResponse(false, null));
                }

            }

            @Override
            public void onFailure(Call<PopularMovieResult> call, Throwable t) {
                EventBus.getDefault().post(new ListenerResponse(false, null));
            }
        });
    }

    public void getPopularMovies(){
        Call<PopularMovieResult> call = apiService.getMoviePopular(Utils.API_KEY);
        call.enqueue(new Callback<PopularMovieResult>() {
            @Override
            public void onResponse(Call<PopularMovieResult> call, Response<PopularMovieResult> response) {
                if (response.isSuccessful()) {
                    PopularMovieResult popularMovieResult = response.body();
                    EventBus.getDefault().post(new ListenerResponse(true, popularMovieResult));
                } else {
                    EventBus.getDefault().post(new ListenerResponse(false, null));
                }

            }

            @Override
            public void onFailure(Call<PopularMovieResult> call, Throwable t) {
                EventBus.getDefault().post(new ListenerResponse(false, null));
            }
        });
    }



}
