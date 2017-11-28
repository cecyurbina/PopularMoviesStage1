package com.projectmovie1;

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

import com.projectmovie1.Models.Result;
import com.projectmovie1.Utilities.ListenerResponse;
import com.projectmovie1.Utilities.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private GridView gridView;
    private AdapterMovie  adapterMovie;
    private List<Result> movies = new ArrayList<>();
    private ControllerMainActivity controllerMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controllerMainActivity = new ControllerMainActivity();
        gridView = (GridView) findViewById(R.id.gv_movies);
        adapterMovie = new AdapterMovie(this, movies);
        gridView.setAdapter(adapterMovie);
        gridView.setOnItemClickListener(this);
        controllerMainActivity.getPopularMovies();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ListenerResponse event) {
        if (event.isSuccessful) {
            showToast(getString(R.string.response_success));
            movies.clear();
            movies.addAll(event.result.getResults());
            adapterMovie.notifyDataSetChanged();
        } else {
            showToast(getString(R.string.response_error));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void showToast(String message) {
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

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_order, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_order_popular:
                        controllerMainActivity.getPopularMovies();
                        return true;
                    case R.id.action_order_rated:
                        controllerMainActivity.getTopRatedMovies();
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
        startActivity(intent);

    }
}
