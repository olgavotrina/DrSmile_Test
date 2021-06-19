package tests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * An implementation of tests for REST "https://jsonplaceholder.typicode.com" with RestAssured
 * @autor Olga Votrina
 */
public class TestRequestUserCreate extends TestBase {

    /**
     * Testing POST method for user creation
     */
    @Test
    public void sendPostTest(){

        // Act
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(TestBase.class.getClassLoader().getResourceAsStream("request-body.json"))
                .when()
                .post("/users")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("response-schema.json"))
                .extract().response();


        // Assert
        Assert.assertEquals(201, response.statusCode());
        Assert.assertNotNull(response.jsonPath().getString("id"));
        Assert.assertEquals("Bruce Eckel", response.jsonPath().getString("name"));
        Assert.assertEquals("BruceEck", response.jsonPath().getString("username"));
        Assert.assertEquals("bruce@domain.com", response.jsonPath().getString("email"));
        Assert.assertEquals("Mira street", response.jsonPath().getString("address.street"));
        Assert.assertEquals("Apt. 556", response.jsonPath().getString("address.suite"));
        Assert.assertEquals("Novosibirsk", response.jsonPath().getString("address.city"));
        Assert.assertEquals("630-110", response.jsonPath().getString("address.zipcode"));
        Assert.assertEquals("-37.3159", response.jsonPath().getString("address.geo.lat"));
        Assert.assertEquals("81.1496", response.jsonPath().getString("address.geo.lng"));
        Assert.assertEquals("777-777-777", response.jsonPath().getString("phone"));
        Assert.assertEquals("oracle.com", response.jsonPath().getString("website"));
        Assert.assertEquals("JetBrains", response.jsonPath().getString("company.name"));
        Assert.assertEquals("Great minds think alike", response.jsonPath().
                getString("company.catchPhrase"));
        Assert.assertEquals("harness real-time e-markets", response.jsonPath().
                getString("company.bs"));
    }

}