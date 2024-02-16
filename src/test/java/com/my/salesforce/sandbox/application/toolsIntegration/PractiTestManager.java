package com.my.salesforce.sandbox.application.toolsIntegration;

import com.example.practitest.PractiTest;
import com.example.practitest.TestRunBuilder;
import com.example.practitest.api.APIRequestResponse;
import com.example.practitest.api.APIResponse;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

import java.io.IOException;


public class PractiTestManager implements ConcurrentEventListener {

    private PractiTest practiTest;
    private TestRunBuilder testRun;
    private TestRunBuilder.StepsBuilder testStep;
    private static final Logger logger = LogManager.getLogger(PractiTestManager.class);


    public PractiTestManager(){
        //Registered in  CucumberOptions plugin
    }

  /*  public static void main(String[] args){
        System.out.println(UserConfig.getProperties().practiTestProjectId());
        System.out.println(UserConfig.getProperties().practiTestSetId());
        System.out.println(UserConfig.getProperties().practiTestAPIToken());
    }*/

    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::scenarioStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::scenarioFinished);
        publisher.registerHandlerFor(TestStepFinished.class, this::stepFinished);
    }

    private void stepFinished(TestStepFinished event) {

        String stepName;
        String keyword;


        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();
            stepName = steps.getStep().getText();
            keyword = steps.getStep().getKeyword();

            try {
                testStep.setName(keyword).setDescription(stepName);
                setTestStatus(event);


            } catch (IOException e) {
                logger.error("Issue with recording the test status to PractiTest {} ",e.getMessage());
            }
        }
    }


    private void scenarioStarted(TestCaseStarted event) {
        logger.info("TestCase Started");
        logger.info("PractiTest ProjectID "+ UserConfig.getProperties().practiTestProjectId());
        practiTest = new PractiTest(UserConfig.getProperties().practiTestProjectId(), UserConfig.getProperties().practiTestAPIToken());
        testRun = practiTest.defineTestRunDetails();
        testStep = testRun.defineSteps();
    }


    private void scenarioFinished(TestCaseFinished event) {
        String testId = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("testId");
        try {
            testRun.createTestRun(getTestInstanceId(testId, UserConfig.getProperties().practiTestSetId()));
        } catch (IOException e) {
            logger.error("Issue with uploading the test details to PractiTest {} ",e.getMessage());
        }

    }


    private void setTestStatus(TestStepFinished event) throws IOException {
        String status = event.getResult().getStatus().toString();
        switch (status){
            case "PASSED" :
                testStep.setStatusAsPASSED();
                writeActualStep(event,"Step was successful");
                break;

            case "FAILED":
                testStep.setStatusAsFAILED();
              //  testStep.setStatusAsFAILED().setStepAttachment("STEP Failed",System.getProperty("user.dir")+ UserConfig.getProperties().snaps()+event.getTestCase().getName()+".png");
                writeActualStep(event,"Step failed due to below error :\r\n "+event.getResult().getError().toString());
                break;

            case "SKIPPED":
                testStep.setStatusAsNotApplicable();
                writeActualStep(event,"Step was skipped due to previous step failure");
                break;

            case "NOT COMPLETED":
                testStep.setStatusAsFAILED();
                testStep.setStatusAsFAILED().setStepAttachment("STEP Failed",System.getProperty("user.dir")+ UserConfig.getProperties().snaps()+event.getTestCase().getName()+".png");
                writeActualStep(event,"Step failed due to below error :\r\n "+event.getResult().getError().toString());
                break;

            default:
                logger.debug("Invalid status type provided : {} ",status);
                break;

        }
        testStep.addStep();
    }


    private void writeActualStep(TestStepFinished event,String message){
        StringBuilder actualStep = new StringBuilder();
        PickleStepTestStep steps = (PickleStepTestStep) event.getTestStep();

        DataTableArgument  dta = (DataTableArgument) steps.getStep().getArgument();
        if(dta!=null) {
            String table = formatDataTable(dta);
            actualStep.append("Test Data Used \r\n").append(table);
        }
        actualStep.append(message).append("\r\n");

        testStep.setActualResult(actualStep.toString());
    }

    private Integer getTestInstanceId(String testId , String setId) throws IOException {
        APIResponse ids;
        APIRequestResponse res ;
        ids = practiTest.getInstanceIdWithFilters().withInSetIds(setId).withTestIds(testId).sendRequest();
        System.out.println("InstanceID: "+ids.getIds().get(0));
        return ids.getIds().get(0);
    }

    private String formatDataTable(DataTableArgument dataTable) {

        StringBuilder dataUsed = new StringBuilder();
        for (int i = 0; i < dataTable.cells().size(); i++) {
            dataUsed.append(dataTable.cells().get(0).get(i)).append(" = ")
                    .append(dataTable.cells().get(1).get(i)).append("\n");
        }
        dataUsed.append(" ").append("\n");
        return dataUsed.toString();
    }


    public void launchPractiTest() {
    }
}
