package com.api.automation.clients;

import com.api.automation.utils.ApiExecutor;
import io.restassured.response.Response;

public class PostsClient {

    private PostsClient() {}

    public static Response getAllPosts() {
        return ApiExecutor.getWithAuthRetry("/posts");
    }

    public static Response getPostById(int id) {
        return ApiExecutor.getWithAuthRetry("/posts/" + id);
    }

    public static Response searchPosts(String query) {
        return ApiExecutor.getWithAuthRetry("/posts/search?q=" + query);
    }

    public static Response getPostsSorted(String sortBy, String order) {
        return ApiExecutor.getWithAuthRetry(
                "/posts?sortBy=" + sortBy + "&order=" + order
        );
    }
    public static Response getPostsWithParams(int skip, int limit) {

        return ApiExecutor.getWithAuthRetry(
                "/posts?skip=" + skip + "&limit=" + limit
        );
    }
}
