@Regression @ExistingCustomer
Feature: Performing Standard Apttus Renewal for an existing customer with an active startrack contract in place

  @Renewal @StarTrack
  Rule: Existing customer has an active contract in place. So that he can initiate "Standard Apttus Renewal"
  from contract page, for to modify the shopping cart by increasing (or) decreasing the products. A Renewal
  opportunity is auto-generated from which proposal is generated and accepted. Finally renew the contract by
  generating contract documents and managing account numbers

  @CheckoutOnly @Increase
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active EParcel contract initiates Contract renewal for Increase Revenue using "Checkout Only" option
    When Onboarding user opens the active contract from '<opp_name>' initiates "Standard Apttus Renewal"
    And "Checkout Only" after adding "Road Express - STE" to the dov opportunity["Renewal: <opp_name>"] by validating its attributes "EXP", "3000-MELBOURNE", "3000-MELBOURNE", "Tier 4"
    And From '<opp_name>' "Onboarding" user presents "<org_name>__Proposal" document[1] initially in "Draft" status to '<sender>' and later "Accepted"
    And "Create Renewal Contract" documents for["Fixed Term", "12"] by confirming details like key contact['<first_name>' '<last_name>']
      | entity                    | type             | product   |
      | StarTrack Express Pty Ltd | Renewal Contract | StarTrack |
    And Onboarding user, manually sign the contract[0] under "Renewal: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "Renewal: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                            | first_name            | last_name         | opp_name                                   | sender                                  |
      | SANTOS (KORINCI-BARU NO. 2) PTY LTD | Automation_Tester_232 | Vinoth_Murali_232 | StarTrack new customer as sales_04_10_2023 | Automation_Tester_232 Vinoth_Murali_232 |

  @GenerateProposal @Increase
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active EParcel contract initiates Contract renewal for Increase Revenue using "Checkout Only" option
    When Onboarding user opens the active contract from '<opp_name>' initiates "Standard Apttus Renewal"
    And "Generate Proposal Document" "<org_name>__Proposal" after adding "Road Express - STE" to the dov opportunity["DoV: <opp_name>"] by validating its attributes "EXP", "3000-MELBOURNE", "3000-MELBOURNE", "Tier 4" and "Accepted" after presenting to '<sender>'
    And Amends the contract by adding dov language["Open Ended", "Add new products", "StarTrack"], confirming details like key contact['<first_name>' '<last_name>'] and creates contract documents
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Onboarding user, manually sign the contract[0] under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                  | first_name            | last_name         | opp_name                                        | sender                                  |
      | ELAND SUPERANNUATION FUND | Automation_Tester_231 | Vinoth_Murali_231 | StarTrack new customer as onboarding_04_10_2023 | Automation_Tester_231 Vinoth_Murali_231 |

  @GenerateAgreement @Increase
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active EParcel contract initiates Contract renewal for Increase Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates "Standard Apttus Renewal"
    And "Generate Agreement Document" with below details after adding "Road Express - STE" to the dov opportunity["Renewal: <opp_name>"] by validating its attributes "EXP", "3000-MELBOURNE", "3000-MELBOURNE", "Tier 1"
      | entity                    | type             | product   |
      | StarTrack Express Pty Ltd | Renewal Contract | StarTrack |
    And Creates the contract [0] under "Renewal: <opp_name>"
    And Onboarding user, manually sign the contract[0] under "Renewal: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |


    @PTEST
    Examples:
      | opp_name          |
      | StarTrackProducts |