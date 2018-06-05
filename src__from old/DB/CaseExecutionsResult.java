package DB;
// Generated Jun 19, 2015 5:06:37 PM by Hibernate Tools 4.3.1

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * CaseExecutionsResult generated by hbm2java
 */
public class CaseExecutionsResult implements java.io.Serializable {

    private CaseExecutionsResultId id;
    private CaseExecutions caseExecutions;
    private String result;
    private String comment;
    private String newComment;
    private SimpleStringProperty simpleStringComment;
    private SimpleStringProperty OldCommentStringProperty;

    public CaseExecutionsResult() {
    }

    public CaseExecutionsResult(CaseExecutionsResultId id, CaseExecutions caseExecutions) {
        this.id = id;
        this.caseExecutions = caseExecutions;
    }

    public CaseExecutionsResult(CaseExecutionsResultId id, CaseExecutions caseExecutions, String result, String comment) {
        this.id = id;
        this.caseExecutions = caseExecutions;
        this.result = result;
        this.comment = comment;
    }

    public CaseExecutionsResultId getId() {
        return this.id;
    }

    public void setId(CaseExecutionsResultId id) {
        this.id = id;
    }

    public CaseExecutions getCaseExecutions() {
        return this.caseExecutions;
    }

    public void setCaseExecutions(CaseExecutions caseExecutions) {
        this.caseExecutions = caseExecutions;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        OldCommentStringProperty().set(comment);
    }

    public void setNewComment(String comment) {
        simpleStringCommentProperty().set(comment);
        this.newComment = comment;
    }

    public String getNewComment() {
        return this.newComment;
    }
    

    public String getSimpleStringCommentProperty() {
        return simpleStringCommentProperty().get();
    }

    public StringProperty simpleStringCommentProperty() {
        if (simpleStringComment == null) {
            simpleStringComment = new SimpleStringProperty(this, "comment");
            simpleStringComment.set(" ");
        }
//        /System.out.println("SIMPLE STRING COMMENT= "+simpleStringComment.get());
        return simpleStringComment;
    }

    public String getOldCommentStringProperty() {
        return OldCommentStringProperty().get();
    }

    public StringProperty OldCommentStringProperty() {
        if (OldCommentStringProperty == null) {
            OldCommentStringProperty = new SimpleStringProperty(this, "comment");
            OldCommentStringProperty.set(" ");
        }
//        /System.out.println("SIMPLE STRING COMMENT= "+simpleStringComment.get());
        return OldCommentStringProperty;
    }

}
