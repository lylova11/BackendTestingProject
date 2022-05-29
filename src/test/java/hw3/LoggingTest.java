package hw3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class LoggingTest {

    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}
