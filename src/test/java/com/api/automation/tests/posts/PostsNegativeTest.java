package com.api.automation.tests.posts;

import com.api.automation.clients.PostsClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PostsNegativeTest {

    @Test(groups = {"negative", "regression"})
    public void verifyGetPostWithInvalidId() {

        Response response = PostsClient.getPostById(999999);

        assertEquals(response.getStatusCode(), 404);
    }

    @Test(groups = {"negative", "regression"})
    public void verifySearchPostWithNoResults() {

        Response response = PostsClient.searchPosts("zzzzzznonexistent");

        assertEquals(response.getStatusCode(), 200);
        assertEquals(response.jsonPath().getInt("total"), 0);
    }

    @Test(groups = {"negative", "regression"})
    public void verifyInvalidSortFieldBehavior() {

        Response response = PostsClient.getPostsSorted("invalidField", "asc");

        // API should NOT crash
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.jsonPath().getList("posts").size() > 0);
    }

    @Test(groups = {"negative", "regression"})
    public void verifyInvalidPaginationLimit() {

        Response response = PostsClient.getPostsWithParams(0, -1);

        // Document actual behavior — DummyJSON returns full list
        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.jsonPath().getList("posts").size() > 0);
    }
}
