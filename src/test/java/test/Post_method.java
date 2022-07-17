package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import  io.restassured.specification.RequestSpecification;
import model.postbody;

import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class Post_method {
    public static void main(String[] args) {
        String baseUrl= "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseUrl);

        request.header(new Header("Content-type","application/json; charset=UTF-8"));

        String postBody1 ="{\n" +
                "\"userId\": 1,\n" +
                "\"id\": 1,\n" +
                "\"title\": \"Title test\",\n" +
                "\"body\": \"Body test\"\n" +
                "}";

        Gson gson = new Gson();
        postbody postBody = new postbody();
        postBody.setUserId(1);
        postBody.setId(1);
        postBody.setTitle("Title test");
        postBody.setBody("Body test");

     //   Response repsone= request.body(postBody).post("/posts");
        //dung gson
        Response repsone= request.body(gson.toJson(postBody)).post("/posts");
        repsone.prettyPrint();
        System.out.println(repsone.statusCode());
        repsone.then().body("title",equalTo("Title test"));
        repsone.then().body("body",equalTo("Body test"));

    }
}
