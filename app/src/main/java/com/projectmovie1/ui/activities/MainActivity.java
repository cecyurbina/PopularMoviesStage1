package com.projectmovie1.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.projectmovie1.R;
import com.projectmovie1.data.model.PopularMovieResult;
import com.projectmovie1.data.model.Result;
import com.projectmovie1.presenter.MainPresenter;
import com.projectmovie1.presenter.MainPresenterImpl;
import com.projectmovie1.ui.view.MainView;
import com.projectmovie1.utils.Utils;
import com.projectmovie1.ui.adapters.AdapterMovie;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView, AdapterView.OnItemClickListener{
    @BindView(R.id.gv_movies) GridView gridView;

    private AdapterMovie adapterMovie;
    private List<Result> movies = new ArrayList<>();
    private MainPresenter controllerMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        controllerMainActivity = new MainPresenterImpl(this);
        adapterMovie = new AdapterMovie(this, movies);
        gridView.setAdapter(adapterMovie);
        gridView.setOnItemClickListener(this);
        getPopularMovies();
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void showToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order:
                showPopup(findViewById(R.id.action_order));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_order, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_order_popular:
                        getPopularMovies();
                        return true;
                    case R.id.action_order_rated:
                        getTopRatedMovies();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        Result tempResult = movies.get(position);
        intent.putExtra(DetailActivity.KEY_URL_POSTER, Utils.IMAGE_URL+tempResult.getPosterPath());
        intent.putExtra(DetailActivity.KEY_TITLE, tempResult.getTitle());
        intent.putExtra(DetailActivity.KEY_VOTE_AVERAGE, String.valueOf(tempResult.getVoteAverage()));
        intent.putExtra(DetailActivity.KEY_RELEASE_DATE, tempResult.getReleaseDate());
        intent.putExtra(DetailActivity.KEY_PLOT_SYNOPSIS, tempResult.getOverview());
        intent.putExtra(DetailActivity.KEY_MOVIE_ID, tempResult.getId());

        startActivity(intent);

    }

    @Override
    public void getPopularMovies() {
        controllerMainActivity.getPopularMovies();
    }

    @Override
    public void getTopRatedMovies() {
        controllerMainActivity.getTopRatedMovies();
    }

    @Override
    public void moviesSuccess(PopularMovieResult responseComments) {
            showToast(getString(R.string.response_success));
            movies.clear();
            movies.addAll(responseComments.getResults());
            adapterMovie.notifyDataSetChanged();

    }

    @Override
    public void moviesError(PopularMovieResult responseComments) {
        showToast(getString(R.string.response_error));

    }
}
