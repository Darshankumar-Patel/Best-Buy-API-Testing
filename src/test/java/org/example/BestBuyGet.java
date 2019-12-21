package org.example;

import com.sun.org.apache.regexp.internal.RE;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.hamcrest.Matcher;
import sun.security.x509.OtherName;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static org.hamcrest.Matchers.equalTo;
import javax.naming.ldap.StartTlsResponse;
import javax.xml.soap.SAAJResult;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class BestBuyGet {

    @Test
    public void getProduct(){
        Response response = given()
                .when().get("http://localhost:3030/products");
        response.then().statusCode(200)
                .body("data.type",hasItem("HardGood"))
                .body("data.model",hasItems("MN2400B4Z"));
//             .body("data.categories.name",hasItems("Alkaline Batteries","Housewares"));

        response.prettyPrint();
    }


    @Test
        public void storesGet(){
            Response response = given()
                    .when().get("http://localhost:3030/stores");
            response.then().statusCode(200)
                    .body("limit",is(equalTo(10)))
                    .body("data.type",hasItems("BigBox"));
//                    .body("services.storeId",is(4))

            response.prettyPrint();
        }
    @Test
    public void servicesGet(){

        Response response = given()
                .when().get("http://localhost:3030/services");
        response.then().statusCode(200)
                .body("total",is(equalTo(21)))
                .body("data.id",hasItems(1,3,5))
                .body("data.name",hasItems("Windows Store"));

        response.prettyPrint();

    }

    @Test
    public void categoriesGet(){

        Response response = given()
                .when().get("http://localhost:3030/categories");
        response.then().statusCode(200)
                .body("skip",is(0))
                .body("data.name",hasItems("Gift Ideas"));

        response.prettyPrint();
    }

    @Test
    public void utilitiesGet(){

        Response response = given()
                .when().get("http://localhost:3030/version");
        response.then().statusCode(200)
                .body("version",is("1.1.0"));

        response.prettyPrint();
    }

    @Test
    public void healthCheckGet(){

        Response response = given()
                .when().get("http://localhost:3030/healthcheck");
        response.then().statusCode(200)
                .body("readonly",is(false));

        response.prettyPrint();
    }
}
