package org.example;

import com.sun.org.apache.regexp.internal.RE;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.hamcrest.Matcher;
import sun.security.x509.OtherName;

import static org.hamcrest.Matchers.equalTo;
import javax.naming.ldap.StartTlsResponse;
import javax.xml.soap.SAAJResult;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class BestBuyPost {

    @Test
    public void postProduct() {

        Response response = given().contentType("application/json")
                .when().body("{\n" +
                        "  \"name\": \"Darshan\",\n" +
                        "  \"type\": \"Manager\",\n" +
                        "  \"price\": 0,\n" +
                        "  \"shipping\": 0,\n" +
                        "  \"upc\": \"string\",\n" +
                        "  \"description\": \"string\",\n" +
                        "  \"manufacturer\": \"string\",\n" +
                        "  \"model\": \"string\",\n" +
                        "  \"url\": \"string\",\n" +
                        "  \"image\": \"string\"\n" +
                        "}")
                .post("http://localhost:3030/products");
        response.then().statusCode(201)
                .body("name", is(equalTo("Darshan")))
                .body("shipping", is(0));
        response.prettyPrint();

    }

    @Test
    public void storePost(){

        Response response = given().contentType("application/json")
                .when().body("{\n" +
                        "  \"name\": \"Best Buy\",\n" +
                        "  \"type\": \"Express\",\n" +
                        "  \"address\": \"Upton Park\",\n" +
                        "  \"address2\": \"London\",\n" +
                        "  \"city\": \"Essex\",\n" +
                        "  \"state\": \"England\",\n" +
                        "  \"zip\": \"E10 7jq\",\n" +
                        "  \"lat\": 0,\n" +
                        "  \"lng\": 0,\n" +
                        "  \"hours\": \"07:00 - 21:00\",\n" +
                        "  \"services\": {}\n" +
                        "}")
                .post("http://localhost:3030/stores");

        response.then().statusCode(201)
                .body("name",is("Best Buy"))
                .body("type",is("Express"))
                .body("hours",is("07:00 - 21:00"));

        response.prettyPrint();

    }

    @Test
    public void servicesPost(){

        Response response = given().contentType("Application/json")
                .when().body("{\n" +
                        "  \"name\": \"Check Out\"\n" +
                        "}")
                .post("http://localhost:3030/services");
        response.then().statusCode(201)
                .body("name",is("Check Out"));

        response.prettyPrint();
    }
    @Ignore
    public void categoriesPOst(){

        Response response = given().contentType("application/json")
                .when().body("{\n" +
                        "  \"name\": \"Consumables\",\n" +
                        "  \"id\": \"012345\"\n" +
                        "}")
                .post("http://localhost:3030/categories");
        response.then().statusCode(201)
                .body("name",is("Consumables"))
                .body("id",is(equalTo(012345)));

        response.prettyPrint();
// Best Buy wrote the test to FAIL! It meant to be status code 400.
    }



}
