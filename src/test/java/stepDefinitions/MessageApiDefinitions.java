package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

import java.io.File;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public class MessageApiDefinitions {


    private String endPointURL;
    private Response response;

    @Given("The Endpoint {string}")
    public void the_endpoint(String apiURL) {
        endPointURL = apiURL;

    }

    @When("The Customer Sends a GET Request")
    public void the_customer_sends_a_get_request() {
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get(endPointURL);

    }

    @When("The customer sends a POST request with the following details:")
    public void customer_send_a_post_request_with_the_following_input(String payload) {
        response = RestAssured
                .given().
                contentType(ContentType.JSON).
                body(payload).
                when().
                post(endPointURL);
    }

    @When("The customer sends a POST request with the invalid details:")
    public void the_customer_sends_a_post_request_with_the_invalid_details(String invalidPayload) {

        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(invalidPayload)
                .when()
                .post(endPointURL);
    }

    @Then("The Response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {

        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());

    }

    @Then("The Response should contain messages")
    public void the_response_should_contain_messages() {

        String responseBody = response.getBody().asString();
        Assert.assertNotNull(responseBody);
        Assert.assertFalse(responseBody.isEmpty());

    }

    @Then("The Response should match the JSON schema")
    public void the_response_should_match_the_json_shema() {

        File jsonSchemaFile = new File("src/test/resources/schema/messagesSchema.json");
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchemaFile));

    }

    @Then("The Response should contain a valid {string}")
    public void the_response_should_contain_a_valid(String messageId){
        response.then()
                .assertThat()
                .body(messageId, notNullValue())
                .body(messageId, instanceOf(Number.class))
                .body(messageId, greaterThan(0));

    }

    @Then("The response should contain an error message {string}")
    public void the_response_should_contain_an_error_message(String expectedErrorMessage) {

        String actualErrorMessage = response.jsonPath().getString("fieldErrors[0]");
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);

    }

}

