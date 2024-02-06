package steps.regression_testing.existing_customer.eparcel;

import com.my.salesforce.sandbox.Constants;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Accept_The_Approved_Proposal {
    private final Base lBase;

    public Accept_The_Approved_Proposal(@NotNull Base base) {
        this.lBase = base;
    }

    @When("Lands on the proposal document {string} in {string} status is presented to {string} and later {string}")
    public void draft_proposal_is_accepted(
            String document_name, String initial_status,
            String sender, String later_status) {
        this.lBase.proposalName = this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator().getProposalPage()
                .is_ready().verify_status(initial_status, sender)
                .create_proposal(sender).verify_stage(Constants.PROPOSAL_STAGE)
                .present_the_proposal(document_name, sender).is_ready()
                .accept_the_proposal().is_ready()
                .verify_stage(later_status).getName();
        System.out.println("Proposal Name:" + this.lBase.proposalName);
    }
}