@Regression @ExistingCustomer
Feature: Contract Deed of Variation

  @StarTrack @DoV
  Rule: Existing customer has an active contract in place. So that he can initiate "Deed of Variation"
  from contract page, so as to modify the shopping cart by increasing (or) decreasing the products. A
  DoV opportunity is auto-generated from which the customer creates, generates and accepts the proposal.
  Finally create, generate contract document and amend the contract by adding new dov language and generate
  contract documents by managing account numbers

  @Increase
  Scenario Outline: Existing StarTrack customer['<org_name>'] having 'Premium - STE' products amends the contract by initiating DoV for adding 'Road Express - STE' products
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], '<post_code>', '<monthly_spend>'
      | sub_type | stage    | close_date | is_it_startrack | type              |
      | New      | Identify | 31/12/2023 | Yes             | Existing Customer |
    And "Submitted" a "Customer Onboarding" DSR for "StarTrack" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' "Onboarding" user "Checkout Only" "Premium - STE" after validating its attributes "PRM", "3000-MELBOURNE", "3000-MELBOURNE" and "Tier 4"
    And Proposal document "<org_name>__StarTrack Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    And Sales user creates csq related to '<opp_name>' with freight offering of type["Premium"] and pickup location
      | customer_brief         | address_line1 | address_line2 | suburb    | state | postcode |
      | For StarTrack Customer | 111           | BOURKE ST     | MELBOURNE | VIC   | 3000     |
    And "Onboarding" confirms details like key contact['<first_name>' '<last_name>'] and creates contract documents from "Accepted" proposal
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | New Contract | StarTrack |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    When User initiates dov["Proposal and Contract Flow","Increase Revenue"] by adding "Road Express - STE" after validating its attributes "EXP", "3000-MELBOURNE", "3000-MELBOURNE", "Tier 4" and "Checkout Only" from "DoV: <opp_name>"
    And Proposal document "<org_name>__StarTrack Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    And Amends the contract by adding dov language["Open Ended", "Add new products", "StarTrack"], confirming details like key contact['<first_name>' '<last_name>'] and creates contract documents
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Onboarding user, manually sign the contract under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @STEST
    Examples:
      | org_name                        | first_name    | last_name | opp_name                                  | post_code      | monthly_spend | sender                  |
      | THE CAPITAL SUPERANNUATION FUND | Vinoth Murali | Tester_02 | StarTrack Contract DoV Process_05_09_2023 | 3000-MELBOURNE | 11000         | Vinoth Murali Tester_02 |