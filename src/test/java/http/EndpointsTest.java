package http;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
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
                .then().
                statusCode(200).
                contentType(ContentType.TEXT);
    }

    @DisplayName("Example with `org.json.JSONObject` for compare results.")
    @Test
    void githubUsersOrgs() {
        String expectedResponse = new JSONObject()
                .put("message", "Not Found")
                .put("documentation_url", "https://developer.github.com/v3").toString();
        get("/users/Varomir/wrong-endpoint").then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .body(equalTo(expectedResponse));
    }

    @DisplayName("Example with `Rest-Assured` and JSON schema verification.")
    @Test
    void validateContract() {
        given()
                .config(RestAssured.config().jsonConfig(jsonConfig())).
        when()
                .get("/users/Varomir/wrong-endpoint").
        then()
                .statusCode(404)
                .contentType(ContentType.JSON)
                .assertThat().body(matchesJsonSchemaInClasspath("wrong-endpoint-schema.json"))
                .body(equalTo("{\"message\":\"Not Found\",\"documentation_url\":\"https://developer.github.com/v3\"}"));
    }
}
