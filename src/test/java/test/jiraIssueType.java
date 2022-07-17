package test;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import model.RequestCapacity;

import io.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class jiraIssueType implements RequestCapacity {
    public static void main(String[] args) {
        String baseURL = "https://tuancao31.atlassian.net";
        String path = "/rest/api/3/project/TCV";
        RequestSpecification request = given();
        request.baseUri(baseURL);

        String email = "tuanvinhcao2608@gmail.com";
        String apiToken = "Uyew18zjfvVcj6iXR8hn58BC";
        String cred = email.concat(":").concat(apiToken);
        byte[] encodeCred = Base64.encodeBase64(cred.getBytes());
        String encodeCredString = new String(encodeCred);
        request.header(RequestCapacity.defaultHeader);
        // cach 1
        //   request.header(RequestCapacity.getAuthorizationHeader(encodeCredString));
        // cach 2
        request.header(getAuthorizationHeader.apply(encodeCredString));
        Response response = request.get(path);
       response.prettyPrint();
        Map<String, List<Map<String, String>>> projectInfo = JsonPath.from(response.asString()).get();
        // show map projectInfo
        for (Map.Entry<String, List<Map<String, String>>> entry : projectInfo.entrySet()) {
            System.out.println(entry.getKey() + "---" + entry.getValue());
        }
        List<Map<String, String>> issueTypes = projectInfo.get("issueTypes");
        // show map issue type
        for (Map<String, String> issueType : issueTypes) {
            System.out.println(issueType.get("id") + "---" + issueType.get("name"));
        }
    }
}