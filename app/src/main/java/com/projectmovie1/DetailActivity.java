package com.projectmovie1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements TrailersFragment.OnFragmentInteractionListener{

    public static final String KEY_URL_POSTER = "poster_url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_RELEASE_DATE = "date";
    public static final String KEY_VOTE_AVERAGE = "average";
    public static final String KEY_PLOT_SYNOPSIS = "synopsis";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ivPoster = (ImageView) findViewById(R.id.iv_movie_poster);
        TextView tvTitle = (TextView) findViewById(R.id.tv_movie_title);
        TextView tvReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        TextView tvVoteAverage = (TextView) findViewById(R.id.tv_movie_vote_average);
        TextView tvPlotSynopsis = (TextView) findViewById(R.id.tv_movie_plot_synopsis);
        //RecyclerView rvMoviesTrailers = (RecyclerView) findViewById(R.id.rv_movies_trailers);
        //RecyclerView.Adapter mAdapter;
        //RecyclerView.LayoutManager mLayoutManager;
        DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
        final ViewPager mViewPager;

        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            String urlText= intent.getStringExtra(KEY_URL_POSTER);
            String titleText= intent.getStringExtra(KEY_TITLE);
            String releaseDateText= intent.getStringExtra(KEY_RELEASE_DATE);
            String averageText= intent.getStringExtra(KEY_VOTE_AVERAGE);
            String synopsisText= intent.getStringExtra(KEY_PLOT_SYNOPSIS);
            setTitle(titleText);
            Picasso.with(this).load(urlText).into(ivPoster);
            tvTitle.setText(titleText);
            tvReleaseDate.setText(releaseDateText);
            tvVoteAverage.setText(averageText);
            tvPlotSynopsis.setText(synopsisText);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_tab_videos)));
            tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.detail_movie_tab_reviews)));
            /*
            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(this);
            rvMoviesTrailers.setLayoutManager(mLayoutManager);
            rvMoviesTrailers.setNestedScrollingEnabled(false);

            // specify an adapter (see also next example)
             String[] myDataset = new String[20];
            myDataset[0] = "a";
            myDataset[1] = "b";
            myDataset[2] = "c";
            myDataset[3] = "d";
            myDataset[4] = "f";
            myDataset[5] = "a";
            myDataset[6] = "b";
            myDataset[7] = "c";
            myDataset[8] = "d";
            myDataset[9] = "f";
            myDataset[10] = "aa";
            myDataset[11] = "bb";
            myDataset[12] = "cc";
            myDataset[13] = "dd";
            myDataset[14] = "ff";
            myDataset[15] = "aaa";
            myDataset[16] = "bbb";
            myDataset[17] = "ccc";
            myDataset[18] = "ddd";
            myDataset[19] = "fff";

            mAdapter = new MyAdapter(myDataset);
            rvMoviesTrailers.setAdapter(mAdapter);*/
            // ViewPager and its adapters use support library
            // fragments, so use getSupportFragmentManager.
            mDemoCollectionPagerAdapter =
                    new DemoCollectionPagerAdapter(
                            getSupportFragmentManager(), 2);
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

    // Since this is an object collection, use a FragmentStatePagerAdapter,
    // and NOT a FragmentPagerAdapter.
    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;

        public DemoCollectionPagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    TrailersFragment tab1 = new TrailersFragment();
                    return tab1;
                case 1:
                    CommentsFragment tab2 = new CommentsFragment();
                    return tab2;
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
