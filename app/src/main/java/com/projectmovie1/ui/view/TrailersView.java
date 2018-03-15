package com.projectmovie1.ui.view;

import com.projectmovie1.data.model.videos.ResponseVideos;

/**
 * Created by cecy on 11/28/17.
 */

public interface TrailersView {
    void getTrailers();

    void trailersSuccess(ResponseVideos responseComments);

    void trailersError(ResponseVideos responseComments);
}
