package com.projectmovie1.ui.view;

import com.projectmovie1.data.model.comments.ResponseComments;

/**
 * Created by cecy on 11/28/17.
 * InfoSync
 */

public interface CommentsView {
    void getComments();

    void commentsSuccess(ResponseComments responseComments);

    void commentsError(ResponseComments responseComments);
}
