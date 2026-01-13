package com.api.automation.clients;

import com.api.automation.utils.ApiExecutor;
import io.restassured.response.Response;

import java.util.Map;

public class CommentsClient {

    private CommentsClient() {}

    public static Response getCommentsByPostId(int postId) {
        return ApiExecutor.getWithAuthRetry("/comments/post/" + postId);
    }

    public static Response addComment(int postId, String body) {
        return ApiExecutor.executeWithAuthRetry(
                spec -> spec
                        .body(Map.of(
                                "postId", postId,
                                "body", body,
                                "userId", 1
                        ))
                        .post("/comments/add")
        );
    }


    public static Response updateComment(int commentId, String newBody) {
        return ApiExecutor.executeWithAuthRetry(
                spec -> spec
                        .body(Map.of("body", newBody))
                        .put("/comments/" + commentId)
        );
    }

    public static Response deleteComment(int commentId) {
        return ApiExecutor.executeWithAuthRetry(
                spec -> spec.delete("/comments/" + commentId)
        );
    }
}
