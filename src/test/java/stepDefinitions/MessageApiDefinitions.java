package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;
import java.util.Objects;

public class MessageApiDefinitions {


    private String endPointURL;
    private Response response;

    @Given("The Endpoint {string}")
    public void the_endpoint(String apiURL) {
        endPointURL = apiURL;

    }


    @When("The Customer Sends a GET Request")
    public void the_customer_sends_a_get_request() {

        response = RestAssured.given().contentType(ContentType.JSON).get(endPointURL);

    }

    @When("The customer sends a POST request with the following details:")
    public void customer_send_a_post_request_with_the_following_input(io.cucumber.datatable.DataTable dataTable) {

        Map<String,String>  requestBody = dataTable.asMaps().get(0);
        response = RestAssured
                .given().
                contentType(ContentType.JSON).
                body(requestBody).
                when().
                post(endPointURL);

    }

    @Then("The Response status code should be {int}")
    public void the_response_status_code_should_be(Integer statusCode) {

        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());

    }

    @Then("The Response should contain messages")
    public void the_response_should_contain_messages() {

        Assert.assertTrue(Objects.nonNull(response.getBody().asString()));

    }





}

