@Regression @ExistingCustomer
Feature: Performing Standard Apttus Renewal for an existing customer with an active eParcel contract in place

  @Renewal @EParcel
  Rule: Existing customer has an active contract in place. So that he can initiate "Standard Apttus Renewal"
  from contract page, for to modify the shopping cart by increasing (or) decreasing the products. A Renewal
  opportunity is auto-generated from which proposal is generated and accepted. Finally renew the contract by
  generating contract documents and managing account numbers

  @CheckoutOnly @Increase
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract renewal for Increase Revenue using "Checkout Only" option
    When Onboarding user opens the active contract from '<opp_name>' initiates "Standard Apttus Renewal"
    And "Checkout Only" after adding "International Digital PCMS Bundle incl Airmail Letters" with rk code['<rk_code>'] to the dov opportunity["Renewal: <opp_name>"] by validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel" and lodgement point "3000"
    And From '<opp_name>' "Onboarding" user presents "<org_name>__Proposal" document[1] initially in "Draft" status to '<sender>' and later "Accepted"
    And "Create Renewal Contract" documents for["Fixed Term", "12"] by managing "eParcel" billing accounts and confirming details like key contact['<first_name>' '<last_name>']
      | entity                        | type             | product           |
      | Australian Postal Corporation | Renewal Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "Renewal: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "Renewal: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                    | first_name             | last_name          | opp_name                                                           | sender                                    | rk_code  |
      | ROTHSCHILD NOMINEES PTY LTD | Automation_Tester_1005 | Vinoth_Murali_1005 | Renewal Increase using checkout only for EParcel Bundle_31_10_2023 | Automation_Tester_1005 Vinoth_Murali_1005 | 10015370 |

  @GenerateProposal @Increase
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract renewal for Increase Revenue using "Generate Proposal Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates "Standard Apttus Renewal"
    And "Generate Proposal Document" "<org_name>__Proposal" adding "International Digital PCMS Bundle incl Airmail Letters" with rk code['<rk_code>'] to the dov opportunity["DoV: <opp_name>"] by validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel", lodgement point "3000" and "Accepted" after presenting to '<sender>'
    And "Create Renewal Contract" documents for["Fixed Term", "12"] by managing "eParcel" billing accounts and confirming details like key contact['<first_name>' '<last_name>']
      | entity                        | type             | product           |
      | Australian Postal Corporation | Renewal Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "Renewal: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "Renewal: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name      | first_name             | last_name          | opp_name                                                               | sender                                    | rk_code  |
      | FUNDI PTY LTD | Automation_Tester_1006 | Vinoth_Murali_1006 | Renewal Increase using generate proposal for EParcel Bundle_31_10_2023 | Automation_Tester_1006 Vinoth_Murali_1006 | 10015370 |

  @GenerateAgreement @Increase
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract renewal for Increase Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates "Standard Apttus Renewal"
    And "Generate Agreement Document" with below details after adding "International Digital PCMS Bundle incl Airmail Letters" with rk code['<rk_code>'] to the opportunity["Renewal: <opp_name>"] by validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel" and lodgement point "3000"
      | entity                        | type             | product           |
      | Australian Postal Corporation | Renewal Contract | eParcels Domestic |
    And "Create Renewal Contract" documents for["Fixed Term", "12"] by managing "eParcel" billing accounts and confirming details like key contact['<first_name>' '<last_name>']
      | entity                        | type             | product           |
      | Australian Postal Corporation | Renewal Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "Renewal: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "Renewal: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                          | first_name             | last_name          | opp_name                                                                         | rk_code  |
      | NATION FAMILY SUPERANNUATION FUND | Automation_Tester_1009 | Vinoth_Murali_1009 | Renewal Increase using generate agreement document for EParcel Bundle_31_10_2023 | 10015370 |