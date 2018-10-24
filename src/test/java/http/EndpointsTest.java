package http;

import io.restassured.RestAssured;
import org.json.JSONObject;
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

    @DisplayName("Example with `Rest-Assured` and basic assertion.")
    @Test
    void githubZen() {
        get("/zen")
                .then().contentType(equalTo("text/plain;charset=utf-8"));
    }

    @DisplayName("Example with `org.json.JSONObject` for compare results.")
    @Test
    void githubUsersOrgs() {
        String expectedResponse = new JSONObject()
                .put("message", "Not Found")
                .put("documentation_url", "https://developer.github.com/v3").toString();
        get("/users/varomir/organizations_url")
                .then().body(equalTo(expectedResponse));
    }
}
