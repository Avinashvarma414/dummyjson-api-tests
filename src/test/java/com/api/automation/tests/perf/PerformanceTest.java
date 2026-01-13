package com.api.automation.tests.perf;

import org.testng.annotations.Test;

import com.api.automation.clients.CommentsClient;
import com.api.automation.clients.PostsClient;
import com.api.automation.clients.ProductsClient;
import com.api.automation.utils.ApiExecutor;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.response.Response;

public class PerformanceTest {
	@Test(groups = {"performance"})
	//End-to-end response time SLA
	public void verifyGetAllProductsPerformance() {

	    Response response = ProductsClient.getAllProducts();

	    assertEquals(response.getStatusCode(), 200);

	    long responseTime = response.getTime();

	    assertTrue(responseTime < 2000,
	        "Response time exceeded SLA. Actual: " + responseTime);
	}
	@Test(groups = {"performance"})
//	Stability under repeated calls
	
	public void verifyProductsEndpointStability() {

	    for (int i = 0; i < 50; i++) {
	        Response response = ProductsClient.getAllProducts();
	        assertEquals(response.getStatusCode(), 200);
	    }
	}
	@Test(groups= {"performance"})
	public void verifyGetProductsIdPerformance() {
		Response response = ProductsClient.getProductById(1);
		assertEquals(response.getStatusCode(), 200);

	    long responseTime = response.getTime();

	    assertTrue(responseTime < 2000,
	        "Response time exceeded SLA. Actual: " + responseTime);
		
	}
	@Test(groups= {"performance"})
	public void getAllPostsPerformance() {
		Response response = PostsClient.getAllPosts();
		assertEquals(response.getStatusCode(), 200);

	    long responseTime = response.getTime();

	    assertTrue(responseTime < 2000,
	        "Response time exceeded SLA. Actual: " + responseTime);	
	}
	@Test(groups = {"performance"})
	public void getCommentsByPostIdPerformance() {
		Response response = CommentsClient.getCommentsByPostId(1);
		assertEquals(response.getStatusCode(), 200);

	    long responseTime = response.getTime();

	    assertTrue(responseTime < 2000,
	        "Response time exceeded SLA. Actual: " + responseTime);	
	}
	@Test(groups = {"performance"})
    public void verifyAuthMeResponseTime() {

        Response response = ApiExecutor.getWithAuthRetry("/auth/me");

        assertEquals(response.getStatusCode(), 200);

        long responseTime = response.getTime();
//        Auth endpoint performance
//        /auth/me latency check
        // SLA: auth endpoints should be fast
        assertTrue(
            responseTime < 1500,
            "GET /auth/me response time too high: " + responseTime + " ms"
        );
    }



}
