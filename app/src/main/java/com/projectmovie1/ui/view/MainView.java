package com.projectmovie1.ui.view;

import com.projectmovie1.data.model.PopularMovieResult;

/**
 * Created by cecy on 11/28/17.
 */

public interface MainView {
    void getPopularMovies();

    void getTopRatedMovies();

    void moviesSuccess(PopularMovieResult responseComments);

    void moviesError(PopularMovieResult responseComments);
}
