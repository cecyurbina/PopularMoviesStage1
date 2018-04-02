package com.projectmovie1.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.projectmovie1.R;
import com.projectmovie1.ui.fragments.CommentsFragment;
import com.projectmovie1.ui.fragments.GeneralInfoFragment;
import com.projectmovie1.ui.fragments.TrailersFragment;
import com.projectmovie1.ui.view.DetailMovieView;
import com.projectmovie1.utils.Utils;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailMovieView {
    private final static String KEY_SAVED_POSITION = "key_saved_position";
    public static final String KEY_MOVIE_ID = "movie_id";
    public static final String KEY_URL_POSTER = "poster_url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_RELEASE_DATE = "date";
    public static final String KEY_VOTE_AVERAGE = "average";
    public static final String KEY_PLOT_SYNOPSIS = "synopsis";
    private String urlPoster;
    private String titleText;
    private String releaseDateText;
    private String averageText;
    private String synopsisText;
    private Integer movieId;
    private Integer selectedSection = 0;
    @BindView(R.id.ctl_image)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.htab_header)
    ImageView ivPoster;
    @BindView(R.id.htab_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;

        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            urlPoster = intent.getStringExtra(KEY_URL_POSTER);
            titleText = intent.getStringExtra(KEY_TITLE);
            releaseDateText = intent.getStringExtra(KEY_RELEASE_DATE);
            averageText = intent.getStringExtra(KEY_VOTE_AVERAGE);
            synopsisText = intent.getStringExtra(KEY_PLOT_SYNOPSIS);
            movieId = intent.getIntExtra(KEY_MOVIE_ID, 0);
            collapsingToolbarLayout.setTitle(titleText);
            setTitle(titleText);
            Picasso.with(this).load(Utils.IMAGE_URL+urlPoster).into(ivPoster);

            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_info)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_tab_videos)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_tab_reviews)));

            mDemoCollectionPagerAdapter =
                    new DemoCollectionPagerAdapter(
                            getSupportFragmentManager());
            mViewPager.setAdapter(mDemoCollectionPagerAdapter);

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    selectedSection = tab.getPosition();
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

        if (savedInstanceState != null) {
            selectedSection = savedInstanceState.getInt(KEY_SAVED_POSITION);
            tabLayout.setScrollPosition(selectedSection,0f,true);
            mViewPager.setCurrentItem(selectedSection);
        }

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
            selectedSection = position;
            switch (position) {
                case 0:
                    return GeneralInfoFragment.newInstance(titleText, releaseDateText, averageText, synopsisText, urlPoster, movieId);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return (super.onOptionsItemSelected(menuItem));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(KEY_SAVED_POSITION, selectedSection);
        super.onSaveInstanceState(outState);
    }

}
