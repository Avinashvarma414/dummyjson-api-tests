package com.api.automation.tests.comments;

import com.api.automation.clients.CommentsClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class CommentsTest {

    @Test(groups = {"regression"})
    public void verifyGetCommentsByPostId() {
        Response response = CommentsClient.getCommentsByPostId(1);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression"})
    public void verifyAddCommentSchema() {

        Response response = CommentsClient.addComment(1, "Nice post");

        assertEquals(response.getStatusCode(), 201);

        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(
                    "schemas/comments/add-comment-schema.json"
                ));
    }

    @Test(groups = {"regression"})
    public void verifyDeleteComment() {
        Response response = CommentsClient.deleteComment(1);
        assertEquals(response.getStatusCode(), 200);
    }
    @Test(groups = {"contract", "regression"})
    public void verifyCommentsByPostSchema() {

        Response response = CommentsClient.getCommentsByPostId(1);

        assertEquals(response.getStatusCode(), 200);

        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath(
                    "schemas/comments/comments-by-post-schema.json"
                ));
    }

}
