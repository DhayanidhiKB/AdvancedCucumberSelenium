@Regression @ExistingCustomer
Feature: Performing Deed of Variation for an existing customer with an active startrack contract in place

  @DoV @StarTrack
  Rule: Existing customer has an active contract in place. So that he can initiate "Deed of Variation"
  from contract page, for to modify the shopping cart by increasing (or) decreasing the products. A DoV
  opportunity is auto-generated from which proposal is generated and accepted. Finally amend the contract
  after adding dov language and generating contract documents by managing account numbers

  @CheckoutOnly @Increase
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active StarTrack contract initiates Contract amendment for Increase Revenue using "Checkout Only" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Increase Revenue"]
    And "Checkout Only" after adding "Road Express - STE" to the dov opportunity["DoV: <opp_name>"] by validating its attributes "EXP", "3000-MELBOURNE", "3000-MELBOURNE", "Tier 4"
    And From '<opp_name>' "Onboarding" user presents "<org_name>__Proposal" document[1] initially in "Draft" status to '<sender>' and later "Accepted"
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

  @GenerateProposal @Increase
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active StarTrack contract initiates Contract amendment for Increase Revenue using "Generate proposal Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Increase Revenue"]
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
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active StarTrack contract initiates Contract amendment for Increase Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Increase Revenue"]
    And "Generate Agreement Document" with below details after adding "Road Express - STE" to the dov opportunity["DoV: <opp_name>"] by validating its attributes "EXP", "3000-MELBOURNE", "3000-MELBOURNE", "Tier 4"
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Amends the contract by adding dov language["Open Ended", "Add new products", "StarTrack"], confirming details like key contact['<first_name>' '<last_name>'] and creates contract documents
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Onboarding user, manually sign the contract[0] under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 1 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | first_name            | last_name         | opp_name                                                      |
      | Automation_Tester_233 | Vinoth_Murali_233 | StarTrack new customer skip proposal as onboarding_04_10_2023 |

  @CheckoutOnly @Decrease
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active StarTrack contract initiates Contract amendment for Decrease Revenue using "Checkout Only" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Decrease Revenue"]
    And "Checkout Only" after deleting "Road Express - STE" from "<opp_name>"
    And From '<opp_name>' "Onboarding" user presents "<org_name>__Proposal" document[1] initially in "Draft" status to '<sender>' and later "Accepted"
    And Amends the contract by adding dov language["Open Ended", "Remove Product", "StarTrack"], confirming details like key contact['<first_name>' '<last_name>'] and creates contract documents
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Onboarding user, manually sign the contract[0] under "<opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 3 "Customer Onboarding" dsr can be seen under "<opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                  | first_name            | last_name         | opp_name                                        | sender                                  |
      | ELAND SUPERANNUATION FUND | Automation_Tester_231 | Vinoth_Murali_231 | StarTrack new customer as onboarding_04_10_2023 | Automation_Tester_231 Vinoth_Murali_231 |

  @GenerateProposal @Decrease
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active StarTrack contract initiates Contract amendment for Decrease Revenue using "Generate proposal Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Decrease Revenue"]
    And "Generate Proposal Document" "<org_name>__Proposal" after deleting "Road Express - STE" from "<opp_name>" and "Accepted" after presenting to '<sender>'
    And Amends the contract by adding dov language["Open Ended", "Add new products", "StarTrack"], confirming details like key contact['<first_name>' '<last_name>'] and creates contract documents
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Onboarding user, manually sign the contract[0] under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 3 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                  | first_name            | last_name         | opp_name                                        | sender                                  |
      | ELAND SUPERANNUATION FUND | Automation_Tester_231 | Vinoth_Murali_231 | StarTrack new customer as onboarding_04_10_2023 | Automation_Tester_231 Vinoth_Murali_231 |

  @GenerateAgreement @Decrease
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active StarTrack contract initiates Contract amendment for Decrease Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Decrease Revenue"]
    And "Generate Agreement Document" with below details after deleting "Road Express - STE" from "<opp_name>"
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Amends the contract by adding dov language["Open Ended", "Add new products", "StarTrack"], confirming details like key contact['<first_name>' '<last_name>'] and creates contract documents
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | DoV Contract | StarTrack |
    And Onboarding user, manually sign the contract[0] under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 3 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | first_name            | last_name         | opp_name                                                      |
      | Automation_Tester_233 | Vinoth_Murali_233 | StarTrack new customer skip proposal as onboarding_04_10_2023 |