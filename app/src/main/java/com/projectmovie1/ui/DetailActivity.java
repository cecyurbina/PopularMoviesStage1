package com.projectmovie1.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.projectmovie1.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements TrailersFragment.OnFragmentInteractionListener{

    public static final String KEY_URL_POSTER = "poster_url";
    public static final String KEY_TITLE = "title";
    public static final String KEY_RELEASE_DATE = "date";
    public static final String KEY_VOTE_AVERAGE = "average";
    public static final String KEY_PLOT_SYNOPSIS = "synopsis";
    private String titleText;
    private String releaseDateText;
    private String averageText;
    private String synopsisText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        ImageView ivPoster = (ImageView) findViewById(R.id.htab_header);
        /*
        TextView tvTitle = (TextView) findViewById(R.id.tv_movie_title);
        TextView tvReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);
        TextView tvVoteAverage = (TextView) findViewById(R.id.tv_movie_vote_average);
        TextView tvPlotSynopsis = (TextView) findViewById(R.id.tv_movie_plot_synopsis);*/

        DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
        final ViewPager mViewPager;

        Intent intent = getIntent();
        if (null != intent) { //Null Checking
            String urlText= intent.getStringExtra(KEY_URL_POSTER);
            titleText= intent.getStringExtra(KEY_TITLE);
            releaseDateText= intent.getStringExtra(KEY_RELEASE_DATE);
            averageText= intent.getStringExtra(KEY_VOTE_AVERAGE);
            synopsisText = intent.getStringExtra(KEY_PLOT_SYNOPSIS);
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
                            getSupportFragmentManager(), 3);
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
                    GeneralInfoFragment tab1 = GeneralInfoFragment.newInstance(titleText, releaseDateText, averageText, synopsisText);
                    return tab1;
                case 1:
                    TrailersFragment tab2 = new TrailersFragment();
                    return tab2;
                case 2:
                    CommentsFragment tab3 = new CommentsFragment();
                    return tab3;
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
