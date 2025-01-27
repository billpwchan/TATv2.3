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
    private String baselineId;
    /**
     *
     */
    public CaseExecutionsResult() {
    }

    /**
     *
     * @param id
     * @param caseExecutions
     */
    public CaseExecutionsResult(CaseExecutionsResultId id, CaseExecutions caseExecutions) {
        this.id = id;
        this.caseExecutions = caseExecutions;
    }

    /**
     *
     * @param id
     * @param caseExecutions
     * @param result
     * @param comment
     */
    public CaseExecutionsResult(CaseExecutionsResultId id, CaseExecutions caseExecutions, String result, String comment) {
        this.id = id;
        this.caseExecutions = caseExecutions;
        this.result = result;
        this.comment = comment;
    }

    /**
     *
     * @param id
     * @param caseExecutions
     * @param result
     * @param comment
     * @param baselineId
     */
    public CaseExecutionsResult(CaseExecutionsResultId id, CaseExecutions caseExecutions, String result, String comment, String baselineId) {
        this.id = id;
        this.caseExecutions = caseExecutions;
        this.result = result;
        this.comment = comment;
        this.baselineId = baselineId;
    }

    /**
     *
     * @return
     */
    public CaseExecutionsResultId getId() {
        return this.id;
    }

    /**
     *
     * @param id
     */
    public void setId(CaseExecutionsResultId id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public CaseExecutions getCaseExecutions() {
        return this.caseExecutions;
    }

    /**
     *
     * @param caseExecutions
     */
    public void setCaseExecutions(CaseExecutions caseExecutions) {
        this.caseExecutions = caseExecutions;
    }

    /**
     *
     * @return
     */
    public String getResult() {
        return this.result;
    }

    /**
     *
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     *
     * @return
     */
    public String getComment() {
        return this.comment;
    }

    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
        OldCommentStringProperty().set(comment);
    }

    /**
     *
     * @param comment
     */
    public void setNewComment(String comment) {
        simpleStringCommentProperty().set(comment);
        this.newComment = comment;
    }

    /**
     *
     * @return
     */
    public String getNewComment() {
        return this.newComment;
    }
    
    /**
     *
     * @return
     */
    public String getSimpleStringCommentProperty() {
        return simpleStringCommentProperty().get();
    }



    public String getBaselineId() {
        return baselineId;
    }

    public void setBaselineId(String baselineId) {
        this.baselineId = baselineId;
    }
    /**
     *
     * @return
     */
    public StringProperty simpleStringCommentProperty() {
        if (simpleStringComment == null) {
            simpleStringComment = new SimpleStringProperty(this, "comment");
            simpleStringComment.set(" ");
        }
//        /System.out.println("SIMPLE STRING COMMENT= "+simpleStringComment.get());
        return simpleStringComment;
    }

    /**
     *
     * @return
     */
    public String getOldCommentStringProperty() {
        return OldCommentStringProperty().get();
    }

    /**
     *
     * @return
     */
    public StringProperty OldCommentStringProperty() {
        if (OldCommentStringProperty == null) {
            OldCommentStringProperty = new SimpleStringProperty(this, "comment");
            OldCommentStringProperty.set(" ");
        }
//        /System.out.println("SIMPLE STRING COMMENT= "+simpleStringComment.get());
        return OldCommentStringProperty;
    }

}
