package model;

public class IssueField {
    private Fields fields;

    public Fields getFields() {
        return fields;
    }

    public IssueField(Fields field) {
        this.fields = field;
    }

    public static class Fields
   {
       String summary;
       private Project project;
       private IssueType issuetype;

       public Fields(String summary, Project project, IssueType issuetype) {
           this.summary = summary;
           this.project = project;
           this.issuetype = issuetype;
       }

       public String getSummary() {
           return summary;
       }

       public Project getProject() {
           return project;
       }

       public IssueType getIssuetype() {
           return issuetype;
       }
   }
   public static class Project
   {
       String key;

       public Project(String key) {
           this.key = key;
       }

       public String getKey() {
           return key;
       }
   }
    public static class IssueType
    {
        String id;

        public IssueType(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

}
