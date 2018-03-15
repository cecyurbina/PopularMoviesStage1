package com.projectmovie1.data.repository.remote;

import com.projectmovie1.data.model.videos.ResponseVideos;

import rx.Observable;

/**
 * Created by cecy on 11/28/17.
 */

public interface TrailersRepository {
    Observable<ResponseVideos> getVideos(String apiKey, Integer movieId);
}
