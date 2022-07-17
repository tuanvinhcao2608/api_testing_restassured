package model;

public class IssueTransition {
    Transition transition;

    public IssueTransition(Transition transition) {
        this.transition = transition;
    }

    public Transition getTransition() {
        return transition;
    }

    public static class Transition
    {
        String id;

        public Transition(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }


}
