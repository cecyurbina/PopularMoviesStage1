package com.projectmovie1.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.projectmovie1.R;
import com.projectmovie1.ui.fragments.CommentsFragment;
import com.projectmovie1.ui.fragments.GeneralInfoFragment;
import com.projectmovie1.ui.fragments.TrailersFragment;
import com.projectmovie1.ui.view.DetailMovieView;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements TrailersFragment.OnFragmentInteractionListener, DetailMovieView {

    public static final String KEY_MOVIE_ID = "movie_id";
    public static final String KEY_URL_POSTER = "poster_url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_RELEASE_DATE = "date";
    public static final String KEY_VOTE_AVERAGE = "average";
    public static final String KEY_PLOT_SYNOPSIS = "synopsis";
    private String titleText;
    private String releaseDateText;
    private String averageText;
    private String synopsisText;
    private Integer movieId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        ImageView ivPoster = (ImageView) findViewById(R.id.htab_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
        final ViewPager mViewPager;

        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            String urlText= intent.getStringExtra(KEY_URL_POSTER);
            titleText= intent.getStringExtra(KEY_TITLE);
            releaseDateText= intent.getStringExtra(KEY_RELEASE_DATE);
            averageText= intent.getStringExtra(KEY_VOTE_AVERAGE);
            synopsisText = intent.getStringExtra(KEY_PLOT_SYNOPSIS);
            movieId = intent.getIntExtra(KEY_MOVIE_ID, 0);
            setTitle(titleText);
            Picasso.with(this).load(urlText).into(ivPoster);
            /*tvTitle.setText(titleText);
            tvReleaseDate.setText(releaseDateText);
            tvVoteAverage.setText(averageText);
            tvPlotSynopsis.setText(synopsisText);*/

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_info)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_tab_videos)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_tab_reviews)));

            mDemoCollectionPagerAdapter =
                    new DemoCollectionPagerAdapter(
                            getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.pager);
            mViewPager.setAdapter(mDemoCollectionPagerAdapter);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mViewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public Integer getMovieId() {
        return movieId;
    }

    // Since this is an object collection, use a FragmentStatePagerAdapter,
    // and NOT a FragmentPagerAdapter.
    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        final int mNumOfTabs;

        public DemoCollectionPagerAdapter(FragmentManager fm) {
            super(fm);
            this.mNumOfTabs = 3;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return GeneralInfoFragment.newInstance(titleText, releaseDateText, averageText, synopsisText);
                case 1:
                    return new TrailersFragment();
                case 2:
                    return new CommentsFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }



}
