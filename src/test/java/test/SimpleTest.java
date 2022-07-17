package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import  static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SimpleTest {
    public static void main(String[] args) {

        // Request scope
        String baseURl ="https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseURl);
        request.basePath("/todos");

        // respond
        final String FIRST_TODO="/1";
        Response repsone = request.get(FIRST_TODO);
        repsone.prettyPrint();
        repsone.then().body("userId",equalTo(1));
        repsone.then().body("id",equalTo(1));
        repsone.then().body("title",equalTo("delectus aut autem"));
        repsone.then().body("completed",equalTo(false));

    }
}
