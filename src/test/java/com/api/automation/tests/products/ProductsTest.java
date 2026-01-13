package com.api.automation.tests.products;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.restassured.response.Response;

import com.api.automation.clients.ProductsClient;

public class ProductsTest {

	@Test(groups = {"smoke", "regression"})
	public void verifyGetAllProducts() {
	    Response response = ProductsClient.getAllProducts();
	    assertEquals(response.getStatusCode(), 200);
	}

	@Test(groups = {"regression"})
	public void verifyGetProductById() {
	    Response response = ProductsClient.getProductById(1);
	    assertEquals(response.getStatusCode(), 200);
	}

	@Test(groups = {"regression"})
	public void verifySearchProducts() {
	    Response response = ProductsClient.searchProducts("phone");
	    assertEquals(response.getStatusCode(), 200);
	}

 
    @Test(groups = {"contract"})
    public void verifyProductsSchema() {

        Response response = ProductsClient.getAllProducts();
        assertEquals(response.getStatusCode(), 200);

        response
            .then()
            .body(matchesJsonSchemaInClasspath("schemas/products-schema.json"));
    }

    @Test
    public void verifyProductByIdSchema() {

        Response response = ProductsClient.getProductById(1);

        assertEquals(response.getStatusCode(), 200);

        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/product-by-id-schema.json"));
    }
    


}
