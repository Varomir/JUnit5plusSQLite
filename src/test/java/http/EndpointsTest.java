package http;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.core.IsEqual.equalTo;

public class EndpointsTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://api.github.com";
    }

    @DisplayName("Rest-Assured http test")
    @Test
    void restAssuredExample() {
        get("/zen")
                .then().contentType(equalTo("text/plain;charset=utf-8"));
    }
}
