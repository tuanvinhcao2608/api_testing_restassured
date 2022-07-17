package test;

import Ulits.AuthenicationHandler;
import io.restassured.specification.RequestSpecification;
import model.RequestCapacity;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;
import static model.RequestCapacity.getAuthorizationHeader;

public class BaseTest {
    protected String baseURL;
    protected String projectKey ;
    protected String encodeCredString;
    protected String email;
    protected String apiToken;
    protected RequestSpecification request;

    @BeforeSuite
    public void beforeSuite()
    {
        baseURL="https://tuancao31.atlassian.net";
        projectKey="TCV";
         email = "tuanvinhcao2608@gmail.com";
         apiToken = "Uyew18zjfvVcj6iXR8hn58BC";
         encodeCredString = AuthenicationHandler.encodeCreStr(email, apiToken);
        System.out.println(encodeCredString);
    }
    @BeforeTest
    public void beforeTest()
    {
        request = given();
        request.baseUri(baseURL);
        request.header(RequestCapacity.defaultHeader);
        request.header(RequestCapacity.acceptJsonHeader);
        request.header(getAuthorizationHeader.apply(encodeCredString));
    }
}
