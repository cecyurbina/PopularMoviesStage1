package com.projectmovie1.data.repository.remote;

import com.projectmovie1.data.model.comments.ResponseComments;

import rx.Observable;

/**
 * Created by cecy on 11/28/17.
 * InfoSync
 */

public interface CommentsRepository {
    Observable<ResponseComments> getComments(String apiKey, Integer id);
}
