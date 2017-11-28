package com.projectmovie1.utils;

import com.projectmovie1.data.model.PopularMovieResult;

/**
 * Created by cecy on 9/19/17.
 */

public class ListenerResponse {
    public final boolean isSuccessful;
    public final PopularMovieResult result;

    public ListenerResponse(boolean isSuccessful, PopularMovieResult result) {
        this.isSuccessful = isSuccessful;
        this.result = result;

    }
}