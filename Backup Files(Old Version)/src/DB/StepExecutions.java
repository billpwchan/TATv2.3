package DB;
// Generated Jun 19, 2015 5:06:37 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * StepExecutions generated by hbm2java
 */
public class StepExecutions implements java.io.Serializable {

    private Integer idstepExecutions;
    private CaseExecutions caseExecutions;
    private TestStep testStep;
    private Byte stepOrder;
    private Set stepExecutionsResults = new HashSet(0);
    private Set scriptExecutionses = new HashSet(0);
    private String result;
    private String comment;

    /**
     *
     */
    public StepExecutions() {
    }

    /**
     *
     * @param caseExecutions
     */
    public StepExecutions(CaseExecutions caseExecutions) {
        this.caseExecutions = caseExecutions;
    }
    
    /**
     *
     * @param caseExecutions
     * @param testStep
     * @param stepOrder
     */
    public StepExecutions(CaseExecutions caseExecutions, TestStep testStep, Byte stepOrder) {
        this.caseExecutions = caseExecutions;
        this.testStep=testStep;
        this.stepOrder=stepOrder;
    }

    /**
     *
     * @param caseExecutions
     * @param testStep
     * @param stepOrder
     * @param stepExecutionsResults
     * @param scriptExecutionses
     */
    public StepExecutions(CaseExecutions caseExecutions, TestStep testStep, Byte stepOrder, Set stepExecutionsResults, Set scriptExecutionses) {
        this.caseExecutions = caseExecutions;
        this.testStep = testStep;
        this.stepOrder = stepOrder;
        this.stepExecutionsResults = stepExecutionsResults;
        this.scriptExecutionses = scriptExecutionses;
    }

    /**
     *
     * @return
     */
    public Integer getIdstepExecutions() {
        return this.idstepExecutions;
    }

    /**
     *
     * @param idstepExecutions
     */
    public void setIdstepExecutions(Integer idstepExecutions) {
        this.idstepExecutions = idstepExecutions;
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
    public TestStep getTestStep() {
        return this.testStep;
    }

    /**
     *
     * @param testStep
     */
    public void setTestStep(TestStep testStep) {
        this.testStep = testStep;
    }

    /**
     *
     * @return
     */
    public Byte getStepOrder() {
        return this.stepOrder;
    }

    /**
     *
     * @param stepOrder
     */
    public void setStepOrder(Byte stepOrder) {
        this.stepOrder = stepOrder;
    }

    /**
     *
     * @return
     */
    public Set getStepExecutionsResults() {
        return this.stepExecutionsResults;
    }

    /**
     *
     * @param stepExecutionsResults
     */
    public void setStepExecutionsResults(Set stepExecutionsResults) {
        this.stepExecutionsResults = stepExecutionsResults;
    }

    /**
     *
     * @return
     */
    public Set getScriptExecutionses() {
        return this.scriptExecutionses;
    }

    /**
     *
     * @param scriptExecutionses
     */
    public void setScriptExecutionses(Set scriptExecutionses) {
        this.scriptExecutionses = scriptExecutionses;
    }
    
    /**
     *
     * @param result
     */
    public void setStepExecutionResult(String result) {
        this.result = result;
    }

    /**
     *
     * @return
     */
    public String getStepExecutionResult() {
        return this.result;
    }

    /**
     *
     * @param comment
     */
    public void setStepExecutionComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @return
     */
    public String getStepExecutionComment() {
        return this.comment;
    }

}