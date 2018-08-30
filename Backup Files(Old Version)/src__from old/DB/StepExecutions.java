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

    public StepExecutions() {
    }

    public StepExecutions(CaseExecutions caseExecutions) {
        this.caseExecutions = caseExecutions;
    }
    
    public StepExecutions(CaseExecutions caseExecutions, TestStep testStep, Byte stepOrder) {
        this.caseExecutions = caseExecutions;
        this.testStep=testStep;
        this.stepOrder=stepOrder;
    }

    public StepExecutions(CaseExecutions caseExecutions, TestStep testStep, Byte stepOrder, Set stepExecutionsResults, Set scriptExecutionses) {
        this.caseExecutions = caseExecutions;
        this.testStep = testStep;
        this.stepOrder = stepOrder;
        this.stepExecutionsResults = stepExecutionsResults;
        this.scriptExecutionses = scriptExecutionses;
    }

    public Integer getIdstepExecutions() {
        return this.idstepExecutions;
    }

    public void setIdstepExecutions(Integer idstepExecutions) {
        this.idstepExecutions = idstepExecutions;
    }

    public CaseExecutions getCaseExecutions() {
        return this.caseExecutions;
    }

    public void setCaseExecutions(CaseExecutions caseExecutions) {
        this.caseExecutions = caseExecutions;
    }

    public TestStep getTestStep() {
        return this.testStep;
    }

    public void setTestStep(TestStep testStep) {
        this.testStep = testStep;
    }

    public Byte getStepOrder() {
        return this.stepOrder;
    }

    public void setStepOrder(Byte stepOrder) {
        this.stepOrder = stepOrder;
    }

    public Set getStepExecutionsResults() {
        return this.stepExecutionsResults;
    }

    public void setStepExecutionsResults(Set stepExecutionsResults) {
        this.stepExecutionsResults = stepExecutionsResults;
    }

    public Set getScriptExecutionses() {
        return this.scriptExecutionses;
    }

    public void setScriptExecutionses(Set scriptExecutionses) {
        this.scriptExecutionses = scriptExecutionses;
    }
    
        public void setStepExecutionResult(String result) {
        this.result = result;
    }

    public String getStepExecutionResult() {
        return this.result;
    }

    public void setStepExecutionComment(String comment) {
        this.comment = comment;
    }

    public String getStepExecutionComment() {
        return this.comment;
    }

}