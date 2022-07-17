package builder;

import model.IssueField;

public class issueContentBuilder {
    IssueField issueField;

    public IssueField getIssueField() {
        return issueField;
    }

    public  String build (String projectKey, String taskTypeId, String summary)
    {
        IssueField.IssueType  issueType= new IssueField.IssueType (taskTypeId) ;
        IssueField.Project  project= new IssueField.Project (projectKey) ;
        IssueField.Fields  field= new IssueField.Fields (summary,project, issueType) ;
        issueField = new IssueField(field);
        return BodyJsonBuilder.getJsoncontent(issueField);
    }
}
