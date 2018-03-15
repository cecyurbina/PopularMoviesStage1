package com.projectmovie1.presenter;

import android.util.Log;

import com.projectmovie1.data.model.videos.ResponseVideos;
import com.projectmovie1.data.repository.remote.TrailersRepository;
import com.projectmovie1.data.repository.remote.TrailersRepositoryImpl;
import com.projectmovie1.ui.view.TrailersView;
import com.projectmovie1.utils.Utils;

import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by cecy on 2/19/18.
 * InfoSync
 */

public class TrailersPresenterImpl implements TrailersPresenter {
    private TrailersView view;
    private CompositeSubscription mSubscription;
    private TrailersRepository trailersRepository;

    public TrailersPresenterImpl(TrailersView view){
        this.view = view;
        this.mSubscription = new CompositeSubscription();
        this.trailersRepository = new TrailersRepositoryImpl();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        this.view = null;
        mSubscription.clear();
    }

    @Override
    public void getTrailers(Integer movieId) {
        Subscription subscription = trailersRepository.getVideos(Utils.API_KEY, movieId).subscribe(new Observer<ResponseVideos>() {
            @Override
            public void onCompleted() {
                view.trailersError(null);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(">>>>", "hi");
            }

            @Override
            public void onNext(ResponseVideos responseVideos) {
                if (view != null){
                    view.trailersSuccess(responseVideos);
                }

            }
        });
        addSubscription(subscription);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        mSubscription.add(subscription);
    }
}
