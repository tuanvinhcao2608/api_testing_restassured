package test;

import Ulits.AuthenicationHandler;
import api_flow.issue_flow;
import io.restassured.specification.RequestSpecification;
import model.RequestCapacity;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static model.RequestCapacity.getAuthorizationHeader;

public class JiraIssueCRUD_02 extends BaseTest {
    @Test
    public  void testE2EFlow() {
        // App info

        issue_flow issue_flow = new issue_flow(request,baseURL,projectKey,"task");
        issue_flow.createIssue();
       issue_flow.verifyIssueDetail();
        issue_flow.updateIssue("Done");
        issue_flow.verifyIssueDetail();
       issue_flow.deleteIssue();
    }
    @Test
    public  void createIssue() {
        // App info

        issue_flow issue_flow = new issue_flow(request,baseURL,projectKey,"task");
        issue_flow.createIssue();
        issue_flow.verifyIssueDetail();
    }
    @Test
    public  void updateIssue() {
        // App info

        issue_flow issue_flow = new issue_flow(request,baseURL,projectKey,"task");
        issue_flow.createIssue();
        issue_flow.updateIssue("Done");
        issue_flow.verifyIssueDetail();
    }
    @Test
    public  void deleteIssue() {
        // App info

        issue_flow issue_flow = new issue_flow(request,baseURL,projectKey,"task");
        issue_flow.createIssue();
        issue_flow.deleteIssue();
    }
}
