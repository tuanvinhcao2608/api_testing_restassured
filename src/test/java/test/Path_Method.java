package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import  io.restassured.specification.RequestSpecification;
import model.BuildModelJson;
import model.RequestCapacity;
import model.postbody;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class Path_Method {

    public static void main(String[] args) {
        String baseUrl= "https://jsonplaceholder.typicode.com";
        // Form up request instant, headr
        RequestSpecification request = given();
        request.baseUri(baseUrl);
        request.header(RequestCapacity.defaultHeader);

        // Form up body

        postbody postBody = new postbody();
        postBody.setTitle("Tuan Cao Test");
        final String TARGET_POST_ID= "1";

        Response repsone= request.body(BuildModelJson.parseJsonString(postBody)).patch("/posts/".concat(TARGET_POST_ID));
        repsone.prettyPrint();
    }
}
