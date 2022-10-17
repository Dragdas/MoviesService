package com.kkulpa.moviesservice.security;

import javax.naming.ldap.PagedResultsControl;

public enum ApplicationUserPermission {

    LOGIN_DETAILS_WRITE("user:write"),
    USER_DETAILS_WRITE("userDetails:write");
/*    USER_DELETE,
    MOVIE_SEARCH_READ,
    MOVIE_DETAILS_READ,
    MOVIE_MARK_FAVOURITE,
    MOVIE_REVIEW_WRITE,
    MOVIE_REVIEWS_READ,
    FAVOURITE_MOVIES_READ,
    MOVIE_RANKING_READ,
    MOVIE_RANKING_BY_FAV_READ,
    MOVIE_COMMENT_WRITE,
    MOVIE_COMMENT_DELETE,
    MOVIE_COMMENTS_READ,
    MOVIE_QUIZ_WRITE,
    MOST_SEARCHED_READ,
    MOVIE_POPULARITY_READ;*/

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
