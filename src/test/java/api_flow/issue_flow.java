package api_flow;

import Ulits.ProjectInfo;
import builder.BodyJsonBuilder;
import builder.issueContentBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.IssueField;
import model.IssueTransition;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class issue_flow {
    private static Map<String,String>transitionTypeMap = new HashMap<>();
    public static final String issuePathPrefix ="/rest/api/3/issue";
    private RequestSpecification request;
    private String baseUrl ;
    private Response response;

    private String createIssueKey;
    private String projectKey;
    private String issueTypeStr;
    private IssueField issueField;
    private String status;
    static {
        transitionTypeMap.put("11","To Do");
        transitionTypeMap.put("21","In Progress");
        transitionTypeMap.put("31","Done");
    }


    public issue_flow(RequestSpecification request, String baseUrl, String projectKey, String issueTypeStr) {
        this.request = request;
        this.baseUrl = baseUrl;
        this.projectKey = projectKey;
        this.issueTypeStr = issueTypeStr;
        this.status="To Do";
    }

    public void createIssue()
    {
        ProjectInfo projectInfo = new ProjectInfo(baseUrl, projectKey);
        String randomsummary = RandomStringUtils.random(20, true, true);
        String issueTypeID = projectInfo.getIssueTypeID(issueTypeStr);
        issueContentBuilder issueContentBuilder = new issueContentBuilder();
        String issueContent = issueContentBuilder.build(projectKey, issueTypeID, randomsummary);
        issueField = issueContentBuilder.getIssueField();
        response = request.body(issueContent).post(issuePathPrefix);
        response.prettyPrint();
        Map<String, String> reponseBody = JsonPath.from(response.asString()).get();
        createIssueKey = reponseBody.get("key");
    }
    public void verifyIssueDetail ()
    {
        Map<String, String> issueInfo =getIssueInfo();
        String expectedSumary = issueField.getFields().getSummary().toString();
        String expectedStatus =status;
        String atcualSumary =issueInfo.get("summary");
        String actualStatus = issueInfo.get("status");
        System.out.println(expectedSumary);
        System.out.println(atcualSumary);
        System.out.println(expectedStatus);
        System.out.println(actualStatus);
    }

    private Map<String, String> getIssueInfo() {
        String getIssuePath = issuePathPrefix +"/"+createIssueKey;
        Response response_=request.get(getIssuePath);
        Map<String, Object> fields = JsonPath.from(response_.getBody().asString()).get("fields");
        String atcualSummary = fields.get("summary").toString();
        Map<String, Object> status = (Map<String, Object>) fields.get("status");
        Map<String, Object> statusCategory = (Map<String, Object>) status.get("statusCategory");
        String actualStatus = statusCategory.get("name").toString();

        Map<String, String> issueInfo = new HashMap<>();
        issueInfo.put("summary",atcualSummary);
        issueInfo.put("status",actualStatus);
        return issueInfo;
    }
    public void updateIssue (String issueStatusStr)
    {
        String targetTransitionId=null;
        for (String transitionId : transitionTypeMap.keySet()) {
            if(transitionTypeMap.get(transitionId).equalsIgnoreCase(issueStatusStr))
            {
                targetTransitionId=transitionId;
                break;
            }
        }
        if (targetTransitionId ==null)
        {
            throw new RuntimeException("Khong co data");
        }
        String issueTransitionPath = issuePathPrefix +"/"+createIssueKey + "/transitions";
        IssueTransition.Transition transition = new IssueTransition.Transition (targetTransitionId);

        IssueTransition issueTransition = new IssueTransition (transition);
        String transitionBody = BodyJsonBuilder.getJsoncontent(issueTransition);
        request.body(transitionBody).post(issueTransitionPath).then().statusCode(204);
        Map<String, String> issueInfo =getIssueInfo();
        String actualIssueStatus =issueInfo.get("status");
        String expectedIssueStatus =transitionTypeMap.get(targetTransitionId);
        System.out.println("actualIssueStatus" + actualIssueStatus);
        System.out.println("expectedIssueStatus" +expectedIssueStatus);

    }

    public void deleteIssue()
    {
        String path = issuePathPrefix +"/"+createIssueKey;
        request.delete(path);
        response=request.get(path);
        response.prettyPrint();
        Map<String,Object> notExistingIssue = JsonPath.from(response.asString()).get();
        List<String> errorMessage = (List<String>) notExistingIssue.get("errorMessages");
      //  System.out.println("Return msg: " + errorMessage.get(0));

    }

}
