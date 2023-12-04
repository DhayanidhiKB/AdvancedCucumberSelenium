@Regression @ExistingCustomer
Feature: Contract Deed of Variation

  @EParcel @DoV
  Rule: Existing customer has an active contract in place. So that he can initiate "Deed of Variation"
  from contract page, so as to modify the shopping cart by increasing (or) decreasing the products. A
  DoV opportunity is auto-generated from which the customer creates, generates and accepts the proposal.
  Finally create, generate contract document and amend the contract by adding new dov language and generate
  contract documents by managing account numbers

  @Increase
  Scenario Outline: Existing EParcel customer['<org_name>'] having 'EParcel International' products amends the contract by initiating DoV for adding 'eParcel' products
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>']
      | sub_type | stage    | close_date | is_it_startrack | type              |
      | New      | Identify | 31/12/2023 | No              | Existing Customer |
    And "Submitted" a "Customer Onboarding" DSR for "eParcel" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' "Onboarding" user "Checkout Only" "International Digital PCMS Bundle incl Airmail Letters" after validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel" and adding lodgement point '<lodgement_point>'
    And Proposal document "<org_name>__Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    And Creates contract from "Accepted" proposal by confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "International Digital PCMS Bundle incl Airmail Letters"
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    When User initiates dov["Proposal and Contract Flow","Increase Revenue"] by adding "eParcel" with rk code['<rk_code>'] after validating its attributes "CAT2 - 2000 to 4999", "BANDED {Z6}", "3000", "50" and "Checkout Only" after adding lodgement point "3000" from "DoV: <opp_name>"
    And Proposal document "<org_name>__Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    And Amends the contract by adding dov language["Open Ended", "Add new products", "eParcel"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @STEST
    Examples:
      | org_name                        | first_name    | last_name | opp_name                                | sender                  | lodgement_point | rk_code  |
      | THE CAPITAL SUPERANNUATION FUND | Vinoth Murali | Tester_02 | EParcel Contract DoV Process_05_09_2023 | Vinoth Murali Tester_02 | 3810            | 10015370 |