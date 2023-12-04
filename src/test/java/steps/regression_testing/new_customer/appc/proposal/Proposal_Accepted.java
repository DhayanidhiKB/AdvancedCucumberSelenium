package steps.regression_testing.new_customer.appc.proposal;

import com.my.salesforce.sandbox.Constants;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import steps.Base;

public class Proposal_Accepted {
    private final Base lBase;

    public Proposal_Accepted(@NotNull Base base) {
        this.lBase = base;
    }

    @When("From {string} {string} user presents {string} document[{int}] initially in " +
            "{string} status to {string} and later {string}")
    public void proposal_accepted(String opp_name, @NotNull String user, String document_name,
                                  int index, @NotNull String proposal_status, String sender,
                                  String later_status) {
        this.lBase.login_into_salesforce_as(user);

        this.lBase.salesforce.getLoginPage().getHeader()
                .search(opp_name)
                .getAppNavigator()
                .is_ready()
                .getOpportunities().getOpportunityHeader()
                .is_ready(opp_name).verify_opportunity_actions()
                .getOpportunitySubHeader()
                .is_ready().scroll_into_view()
                .getOpportunityTabSet()
                .is_ready().products_contracts()
                .getProductsAndContracts()
                .is_ready().open_proposal(index);

        if (proposal_status.equals("Draft")) {
            this.lBase.salesforce.getLoginPage().getHeader()
                    .getAppNavigator().getProposalPage()
                    .is_ready().verify_status(proposal_status, sender)
                    .create_proposal(sender).verify_stage(Constants.PROPOSAL_STAGE);
        }

        this.lBase.proposalName = this.lBase.salesforce.getLoginPage().getHeader()
                .getAppNavigator().getProposalPage()
                .present_the_proposal(document_name, sender).is_ready()
                .accept_the_proposal().is_ready().verify_stage(later_status).getName();
    }
}