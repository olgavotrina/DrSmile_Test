package tests;

import io.restassured.RestAssured;
import org.junit.Before;

/**
 * An implementation of test base class with predefined parameters for RestAssured.
 * @autor Olga Votrina
 */
public class TestBase {

    /**
     * Set baseURI for RestAssured
     */
    // Arrange
    @Before
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }
}
