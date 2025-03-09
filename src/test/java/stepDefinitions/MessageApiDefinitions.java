package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Objects;

public class MessageApiDefinitions {


    private String endPointURL;
    private Response response;


    @Given("The Endpoint {string}")
    public void the_endpoint(String apiURL) {
        endPointURL = apiURL;

    }


    @When("The Customer Sends a Get Request")
    public void the_customer_sends_a_get_request() {

        response = RestAssured.given().contentType(ContentType.JSON).get(endPointURL);

    }

    @Then("The Response status code should be {int}")
    public void the_response_status_code_should_be(int statusCode) {

        Assert.assertEquals(statusCode, response.getStatusCode());

    }

    @Then("The Response should contain messages")
    public void the_response_should_contain_messages() {

        Assert.assertTrue(Objects.nonNull(response.getBody().asString()));

    }
}

