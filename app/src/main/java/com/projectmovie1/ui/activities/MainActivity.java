package com.projectmovie1.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.projectmovie1.R;
import com.projectmovie1.data.DatabaseHelper;
import com.projectmovie1.data.model.PopularMovieResult;
import com.projectmovie1.data.model.Result;
import com.projectmovie1.presenter.MainPresenter;
import com.projectmovie1.presenter.MainPresenterImpl;
import com.projectmovie1.ui.adapters.AdapterMovie;
import com.projectmovie1.ui.view.MainView;
import com.projectmovie1.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    private static final String KEY_SAVED_RESULTS = "key_saved_results";
    @BindView(R.id.gv_movies)
    GridView gridView;
    @BindView(R.id.spinner_order_by)
    Spinner spinner;

    private AdapterMovie adapterMovie;
    private List<Result> movies = new ArrayList<>();
    private MainPresenter presenter;
    private PopularMovieResult popularMovieResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenterImpl(this);

        //movies list
        adapterMovie = new AdapterMovie(this, movies);
        gridView.setAdapter(adapterMovie);
        gridView.setOnItemClickListener(this);

        //order by
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.movies_order_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0,false);
        spinner.setOnItemSelectedListener(this);

        if (savedInstanceState != null) {
            String json = savedInstanceState.getString(KEY_SAVED_RESULTS);
            Gson gson = new Gson();
            moviesSuccess(gson.fromJson(json, PopularMovieResult.class));

        } else {
            getPopularMovies();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (popularMovieResult != null) {
            Gson gson = new Gson();
            String json = gson.toJson(popularMovieResult);
            outState.putString(KEY_SAVED_RESULTS, json);
        }
        super.onSaveInstanceState(outState);
    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Result tempResult = movies.get(position);
        intent.putExtra(DetailActivity.KEY_URL_POSTER, tempResult.getPosterPath());
        intent.putExtra(DetailActivity.KEY_TITLE, tempResult.getTitle());
        intent.putExtra(DetailActivity.KEY_VOTE_AVERAGE, String.valueOf(tempResult.getVoteAverage()));
        intent.putExtra(DetailActivity.KEY_RELEASE_DATE, tempResult.getReleaseDate());
        intent.putExtra(DetailActivity.KEY_PLOT_SYNOPSIS, tempResult.getOverview());
        intent.putExtra(DetailActivity.KEY_MOVIE_ID, tempResult.getId());

        startActivity(intent);

    }

    @Override
    public void getPopularMovies() {
        presenter.getPopularMovies();
    }

    @Override
    public void getTopRatedMovies() {
        presenter.getTopRatedMovies();
    }

    @Override
    public void moviesSuccess(PopularMovieResult responseComments) {
        popularMovieResult = responseComments;
        movies.clear();
        movies.addAll(popularMovieResult.getResults());
        adapterMovie.notifyDataSetChanged();

    }

    @Override
    public void moviesError(PopularMovieResult responseComments) {
        showToast(getString(R.string.response_error));
    }

    private void getFavoritesMovies() {
        popularMovieResult = new PopularMovieResult();
        DatabaseHelper helper = DatabaseHelper.getInstance(this);
        movies.clear();
        popularMovieResult.setResults(helper.getFavorites());
        movies.addAll(popularMovieResult.getResults());
        adapterMovie.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                getPopularMovies();
                break;
            case 1:
                getTopRatedMovies();
                break;
            case 2:
                getFavoritesMovies();
                break;
            default:
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
