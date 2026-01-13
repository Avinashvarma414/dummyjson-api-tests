package com.api.automation.tests.comments;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import com.api.automation.clients.CommentsClient;

import io.restassured.response.Response;

public class CommentsNegativeTest {
	@Test(groups = {"negative", "regression"})
	public void verifyGetCommentsWithInvalidPostId() {

	    Response response = CommentsClient.getCommentsByPostId(999999);

	    assertEquals(response.getStatusCode(), 404);
	}

	@Test(groups = {"negative", "regression"})
	public void verifyAddCommentWithoutBodyFails() {

	    Response response = CommentsClient.addComment(1, "");

	    assertEquals(response.getStatusCode(), 400);
	}
	@Test(groups = {"negative", "regression"})
	public void verifyDeleteNonExistingComment() {

	    Response response = CommentsClient.deleteComment(999999);

	    assertEquals(response.getStatusCode(), 404);
	}



}
