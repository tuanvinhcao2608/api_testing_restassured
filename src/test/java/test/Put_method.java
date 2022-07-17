package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import  io.restassured.specification.RequestSpecification;
import model.postbody;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class Put_method {
    public static void main(String[] args) {
        String baseUrl= "https://jsonplaceholder.typicode.com";
        RequestSpecification request = given();
        request.baseUri(baseUrl);

        request.header(new Header("Content-type","application/json; charset=UTF-8"));

        Gson gson = new Gson();
    /*    postbody postBody = new postbody();
       postBody.setUserId(1);
        postBody.setId(1);
        postBody.setTitle("Title test");
        postBody.setBody("Body test"); */

        //dung mang
        postbody postBody1 = new postbody(1,1,"Title test 01","Body test 01");
        postbody postBody2 = new postbody(2,2,"Title test 02","Body test 02");
        postbody postBody3 = new postbody(3,3,"Title test 03","Body test 03");
        postbody postBody4 = new postbody(4,4,"Title test 04","Body test 04");
        List<postbody> listPostBody= Arrays.asList(postBody1,postBody2,postBody3,postBody4);
        for (postbody postbody : listPostBody) {
            final int TARGET_PUT_NUM =1;
            Response repsone= request.body(gson.toJson(postbody)).put("/posts/".concat(String.valueOf(TARGET_PUT_NUM)));
            repsone.prettyPrint();
            System.out.println(repsone.statusCode());
            repsone.then().body("title",equalTo(postbody.getTitle()));
            repsone.then().body("body",equalTo(postbody.getBody()));
        }


    }
}
