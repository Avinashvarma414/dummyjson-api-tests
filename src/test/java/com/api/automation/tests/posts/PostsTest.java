package com.api.automation.tests.posts;

import com.api.automation.clients.PostsClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PostsTest {

    @Test(groups = {"smoke"})
    public void verifyGetAllPosts() {
        Response response = PostsClient.getAllPosts();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression"})
    public void verifyGetPostById() {
        Response response = PostsClient.getPostById(1);
        assertEquals(response.getStatusCode(), 200);
    }

    @Test(groups = {"regression"})
    public void verifySearchPosts() {
        Response response = PostsClient.searchPosts("love");
        assertEquals(response.getStatusCode(), 200);
    }
}
