package test;

import Ulits.ProjectInfo;
import model.RequestCapacity;

import java.util.List;
import java.util.Map;

public class IssueTypeV2 implements RequestCapacity {
    public static void main(String[] args) {
        String baseURL = "https://tuancao31.atlassian.net";
        String projectKey = "TCV";
        ProjectInfo projectInfo  = new ProjectInfo(baseURL,projectKey);
      System.out.println("Task ID: " + projectInfo.getIssueTypeID("task"));

    }
}
