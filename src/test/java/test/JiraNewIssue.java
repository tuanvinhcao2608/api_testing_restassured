package test;
import Ulits.AuthenicationHandler;
import Ulits.ProjectInfo;
import builder.issueContentBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.IssueField;
import model.RequestCapacity;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

import static  io.restassured.RestAssured.given;
import static model.RequestCapacity.getAuthorizationHeader;

public class JiraNewIssue {
    private static IssueField issueField;

    public static void main(String[] args) {
        // App info
        String baseURL = "https://tuancao31.atlassian.net";
        String path = "/rest/api/3/issue";
        String projectKey = "TCV";
        RequestSpecification request = given();
        request.baseUri(baseURL);
        String email = "tuanvinhcao2608@gmail.com";
        String apiToken = "Uyew18zjfvVcj6iXR8hn58BC";
        String encodeCredString = AuthenicationHandler.encodeCreStr(email, apiToken);
        request.header(RequestCapacity.defaultHeader);
        request.header(RequestCapacity.acceptJsonHeader);
        request.header(getAuthorizationHeader.apply(encodeCredString));
/*
        String fieldStr ="{\n" +
                "\"fields\": {\n" +
                "  \"summary\": \"Main order flow broken\",\n" +
                "  \"issuetype\": {\n" +
                "    \"id\": \"10001\"\n" +
                "  }, \"project\": {\n" +
                "    \"key\": \"TCV\"\n" +
                "  }\n" +
                "}\n" +
                "}";

 */
        ProjectInfo projectInfo = new ProjectInfo(baseURL, projectKey);
        String randomsummary = RandomStringUtils.random(20, true, true);
        String issueTypeID = projectInfo.getIssueTypeID("task");
        issueContentBuilder issueContentBuilder = new issueContentBuilder();
        String issueContent = issueContentBuilder.build(projectKey, issueTypeID, randomsummary);


        //   System.out.println(new Gson().toJson(issueField));

        //   Response response = request.body(fieldStr).post(path);
        // Create jira ticket

       Response response = request.body(issueContent).post(path);
        //  response.prettyPrint();
        // Check jira ticket detail
        Map<String, String> reponseBody = JsonPath.from(response.asString()).get();
        String getIssuePath = "/rest/api/3/issue/" + reponseBody.get("key");
        // get ticket

        response = request.get(getIssuePath);
        IssueField issueField = issueContentBuilder.getIssueField();
        String expectedSumary = issueField.getFields().getSummary().toString();
        String expectedStatus = "To Do";
        Map<String, Object> fields = JsonPath.from(response.getBody().asString()).get("fields");
        String atcualSumary = fields.get("summary").toString();
        Map<String, Object> status = (Map<String, Object>) fields.get("status");
        Map<String, Object> statusCategory = (Map<String, Object>) status.get("statusCategory");
        String actualStatus = statusCategory.get("name").toString();

        System.out.println(expectedSumary);
        System.out.println(atcualSumary);
        System.out.println(expectedStatus);
        System.out.println(actualStatus);


    }
}
