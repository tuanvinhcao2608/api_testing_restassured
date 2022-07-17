package test;
import Ulits.AuthenicationHandler;
import Ulits.ProjectInfo;
import api_flow.issue_flow;
import builder.issueContentBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.IssueField;
import model.RequestCapacity;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.Map;

import static  io.restassured.RestAssured.given;
import static model.RequestCapacity.getAuthorizationHeader;

public class JiraIssueCRUD  extends BaseTest{
    @Test
    public  void testE2EFlow() {
        // App info

        String baseURL = "https://tuancao31.atlassian.net";

        String projectKey = "TCV";
        RequestSpecification request = given();
        request.baseUri(baseURL);
        String email = "tuanvinhcao2608@gmail.com";
        String apiToken = "Uyew18zjfvVcj6iXR8hn58BC";
        String encodeCredString = AuthenicationHandler.encodeCreStr(email, apiToken);
        request.header(RequestCapacity.defaultHeader);
        request.header(RequestCapacity.acceptJsonHeader);
        request.header(getAuthorizationHeader.apply(encodeCredString));
        issue_flow issue_flow = new issue_flow(request,baseURL,projectKey,"task");
        issue_flow.createIssue();
        issue_flow.verifyIssueDetail();
        issue_flow.updateIssue("Done");
        issue_flow.verifyIssueDetail();
       issue_flow.deleteIssue();
    }

}
