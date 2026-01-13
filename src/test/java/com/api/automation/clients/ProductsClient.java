package com.api.automation.clients;

import java.util.HashMap;
import java.util.Map;

import com.api.automation.base.RequestSpecFactory;
import com.api.automation.utils.ApiExecutor;
import io.restassured.response.Response;

public class ProductsClient {

    private ProductsClient() {}

    public static Response getAllProducts() {
        return ApiExecutor.getWithAuthRetry("/products");
    }

    public static Response getProductById(int id) {
        return ApiExecutor.getWithAuthRetry("/products/" + id);
    }

    public static Response searchProducts(String query) {
        return ApiExecutor.getWithAuthRetry("/products/search?q=" + query);
    }
    public static Response getProductsWithParams(int skip, int limit) {
        return ApiExecutor.getWithAuthRetry(
            "/products?skip=" + skip + "&limit=" + limit
        );
    }

    public static Response getProductsSorted(String sortBy, String order) {
        return ApiExecutor.getWithAuthRetry(
            "/products?sortBy=" + sortBy + "&order=" + order
        );
    }

    public static Response addProduct(String title, int price) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", title);
        payload.put("price", price);

        return ApiExecutor.executeWithAuthRetry(
        	    spec -> spec
        	        .body(payload)
        	        .post("/products/add")
        	);

    }

    public static Response updateProduct(int productId, String newTitle) {

        Map<String, Object> payload = new HashMap<>();
        payload.put("title", newTitle);

        return ApiExecutor.executeWithAuthRetry(
        	    spec -> spec
        	        .body(payload)
        	        .put("/products/" + productId)
        	);

    }

    public static Response deleteProduct(int productId) {

    	return ApiExecutor.executeWithAuthRetry(
    		    spec -> spec
    		        .delete("/products/" + productId)
    		);
  


}}
