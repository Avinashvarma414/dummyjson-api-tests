package com.api.automation.tests.products;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.api.automation.clients.ProductsClient;

import io.restassured.response.Response;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertNotNull;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;




public class ProductsAdvancedTest {
	@Test(groups = {"regression"})
	public void verifyProductsPagination() {

	    Response response =
	            ProductsClient.getProductsWithParams(10, 10);

	    assertEquals(response.getStatusCode(), 200);
	    assertEquals(response.jsonPath().getInt("limit"), 10);
	    assertEquals(response.jsonPath().getInt("skip"), 10);
	    assertEquals(response.jsonPath().getList("products").size(), 10);
	}
	@Test(groups = {"regression"})
	public void verifyParallelExecutionProof() {
	    System.out.println(
	        "Thread: " + Thread.currentThread().getId() +
	        " | Test: " + this.getClass().getSimpleName()
	    );
	}

	@Test(groups = {"regression"})
	public void verifyProductsLimitZero() {

		Response response = ProductsClient.getProductsWithParams(0, 0);

		assertEquals(response.getStatusCode(), 200);
		assertTrue(response.jsonPath().getList("products").size() > 0,
		        "API ignores limit=0 and returns default dataset");

	}
	@Test(groups = {"regression"})
	public void verifyProductSearchRelevance() {

	    Response response =
	            ProductsClient.searchProducts("phone");

	    assertEquals(response.getStatusCode(), 200);

	    List<String> titles =
	            response.jsonPath().getList("products.title");

	    assertTrue(
	        titles.stream()
	              .anyMatch(t -> t.toLowerCase().contains("phone"))
	    );
	}
	@Test(groups = {"regression"})
	public void verifyProductSortingAscending() {

	    Response response =
	            ProductsClient.getProductsSorted("title", "asc");

	    List<String> titles =
	            response.jsonPath().getList("products.title");

	    List<String> sorted =
	            new ArrayList<>(titles);
	    Collections.sort(sorted);

	    assertEquals(titles.subList(0, 5), sorted.subList(0, 5));
	}
	@Test(groups = {"regression", "negative"})
	public void verifyInvalidSortByField() {

	    Response response =
	            ProductsClient.getProductsSorted("invalidField", "asc");

	    assertEquals(response.getStatusCode(), 200);
	    assertNotNull(response.jsonPath().getList("products"));
	}
	@Test(groups = {"regression"})
	public void verifyAddProduct() {

	    Response response =
	            ProductsClient.addProduct("Test Product", 999);

	    assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201);
	    assertNotNull(response.jsonPath().getInt("id"));
	    assertEquals(response.jsonPath().getString("title"), "Test Product");
	}
	@Test(groups = {"regression"})
	public void verifyUpdateProduct() {

	    Response response =
	            ProductsClient.updateProduct(1, "Updated Title");

	    assertEquals(response.getStatusCode(), 200);
	    assertEquals(response.jsonPath().getString("title"), "Updated Title");
	}
	@Test(groups = {"regression", "contract"})
	public void verifyDeleteProduct() {

	    Response response =
	            ProductsClient.deleteProduct(1);

	    assertEquals(response.getStatusCode(), 200);

	    response
	    .then()
	    .statusCode(200)
	    .body(matchesJsonSchemaInClasspath("schemas/delete-product-schema.json"));

	}









}
