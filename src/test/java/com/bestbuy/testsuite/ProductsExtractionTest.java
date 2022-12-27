package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/Products")
                .then().statusCode(200); //method type of this is validatable response
    }

    //21. Extract the limit
    @Test
    public void test01() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("total limit  : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test2() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("total is  : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //23. Extract the name of 5th product
    @Test
    public void test003() {
        String name = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("5th product name is : " + name);
        System.out.println("------------------End of Test---------------------------");

    }

    //24. Extract the names of all the products
    @Test
    public void test04() {
        List<String> product = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" names of all the products: " + product);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test05() {
        List<String> allTheProduct = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  all the products are: " + allTheProduct);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test06() {
        List<?> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  size of data: " + data);
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test07() {
        List<String> value = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  product value is : " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test28() {
        List<String> model = Collections.singletonList(response.extract().path("data[8].model").toString());
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  product model is : " + model);
        System.out.println("------------------End of Test---------------------------");
    }
    //29. Get all the categories of 8th products
    @Test
    public void test09() {
        List<String> categories = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  List of cagagories: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test10() {
        List<String> categories = response.extract().path("data[3].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("categories of the store where product: " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 31. Get all the descriptions of all the products
    @Test
    public void test11() {
        List<String> description = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the descriptions of all the products: " + description);
        System.out.println("------------------End of Test---------------------------");

    }

    //32. Get id of all the all categories of all the products
    @Test
    public void test12() {
        List<Integer> idList = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of the all categories of all the products: " + idList);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test33() {
        List<String> productNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The product name is : " + productNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test34() {
        List<HashMap<String, ?>> categories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of categories for the product where product name : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    // 35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test35() {
        List<String> productName = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt of products that price is less than 5.49 are: " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test36() {
        List<String> categories = response.extract().path("data[3].categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total number of categories for the product where product name : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }

    //37. Find the manufacturer of all the products
    @Test
    public void test371() {
        List<String> manufacture = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the manufacturer of all the products : " + manufacture);
        System.out.println("------------------End of Test---------------------------");
    }

    //38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test38() {
        List<String> image = Collections.singletonList(response.extract().path("data[8].image").toString());
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Energizer image is : " + image);
        System.out.println("------------------End of Test---------------------------");
    }

    //39. Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test39() {
        List<String> productName = response.extract().path("data.findAll{it.price > 5.99}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt of products that price is grater than 5.99 are: " + productName);
        System.out.println("------------------End of Test---------------------------");
    }

    //40. Find the uri of all the products
    @Test
    public void test40() {
        List<String> URL = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("  the URL of all the products : " + URL);
        System.out.println("------------------End of Test---------------------------");
    }


}
