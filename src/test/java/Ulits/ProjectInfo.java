package Ulits;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.RequestCapacity;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProjectInfo  implements RequestCapacity {
  private   String baseURL;
  private  String projectKey;
  private List<Map<String, String>> issueTypes;
    private Map<String, List<Map<String, String>>> projectInfo;

    public ProjectInfo(String baseURL, String projectKey) {
        this.baseURL = baseURL;
        this.projectKey = projectKey;
        getProjectInfo();
    }
    public String getIssueTypeID(String issueTypeStr)
    {   getIssueType();
        String issueTypeID = null;
        for (Map<String, String> issueType : issueTypes) {
            System.out.println(issueType.get("id") + "---" + issueType.get("name"));
            if(issueType.get("name").equalsIgnoreCase(issueTypeStr))
            {
                issueTypeID= issueType.get("id");
                break;
            }
        }
        if (issueTypeID ==  null)
        {
            throw new RuntimeException("Could not be found1");
        }
        return issueTypeID;
    }

    private void getIssueType ()
    {
        issueTypes = projectInfo.get("issueTypes");
    }

    private void getProjectInfo()
    {
        String path = "/rest/api/3/project/".concat(projectKey);
        System.out.println(path);
        RequestSpecification request = given();
        request.baseUri(baseURL);


        String email = "tuanvinhcao2608@gmail.com";
        String apiToken = "Uyew18zjfvVcj6iXR8hn58BC";
        String cred = email.concat(":").concat(apiToken);
        byte[] encodeCred = Base64.encodeBase64(cred.getBytes());
        String encodeCredString = new String(encodeCred);
        request.header(RequestCapacity.defaultHeader);
        request.header(getAuthorizationHeader.apply(encodeCredString));
        Response response = request.get(path);
       //    response.prettyPrint();
         projectInfo = JsonPath.from(response.asString()).get();

    }
}
