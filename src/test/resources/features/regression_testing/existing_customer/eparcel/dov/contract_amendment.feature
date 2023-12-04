@Regression @ExistingCustomer
Feature: Performing Deed of Variation for an existing customer with an active eParcel contract in place

  @DoV @EParcel
  Rule: Existing customer has an active contract in place. So that he can initiate "Deed of Variation"
  from contract page, for to modify the shopping cart by increasing (or) decreasing the products. A DoV
  opportunity is auto-generated from which proposal is generated and accepted. Finally amend the contract
  after adding dov language and generating contract documents by managing account numbers

  @CheckoutOnly @Increase
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract amendment for Increase Revenue using "Checkout Only" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Increase Revenue"]
    And "Checkout Only" after adding "International Digital PCMS Bundle incl Airmail Letters" to the dov opportunity["DoV: <opp_name>"] by validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel" and lodgement point "3000"
    And From "DoV: <opp_name>" "Onboarding" user presents "<org_name>__Proposal" document[0] initially in "Draft" status to '<sender>' and later "Accepted"
    And Amends the contract by adding dov language["Open Ended", "Add new products", "International"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 2 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                            | first_name             | last_name          | opp_name                                                      | sender                                    |
      | NM ROTHSCHILD AUST HOLDINGS PTY LTD | Automation_Tester_1007 | Vinoth_Murali_1007 | As Sales checkout only EParcel Bundle new customer_31_10_2023 | Automation_Tester_1007 Vinoth_Murali_1007 |

  @GenerateProposal @Increase
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract amendment for Increase Revenue using "Generate Proposal Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Increase Revenue"]
    And "Generate Proposal Document" "<org_name>__Proposal" adding "International Digital PCMS Bundle incl Airmail Letters" to the dov opportunity["DoV: <opp_name>"] by validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel", lodgement point "3000" and "Accepted" after presenting to '<sender>'
    And Amends the contract by adding dov language["Open Ended", "Add new products", "International"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 2 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                 | first_name             | last_name          | opp_name                                                          | sender                                    |
      | KING SUPERANNUATION FUND | Automation_Tester_1008 | Vinoth_Murali_1008 | As Sales generate proposal EParcel Bundle new customer_31_10_2023 | Automation_Tester_1008 Vinoth_Murali_1008 |

  @GenerateAgreement @Increase
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract amendment for Increase Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Increase Revenue"]
    And "Generate Agreement Document" with below details after adding "International Digital PCMS Bundle incl Airmail Letters" to the dov opportunity["DoV: <opp_name>"] by validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel" and lodgement point "3000"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Amends the contract by adding dov language["Open Ended", "Add new products", "eParcel"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "DoV: <opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 2 "Customer Onboarding" dsr can be seen under "DoV: <opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                 | first_name             | last_name          | opp_name                                                                     |
      | KING SUPERANNUATION FUND | Automation_Tester_1008 | Vinoth_Murali_1008 | DoV Increase using generate agreement document for EParcel Bundle_31_10_2023 |

  @CheckoutOnly @Decrease
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract amendment for Decrease Revenue using "Checkout Only" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Decrease Revenue"]
    And "Checkout Only" after deleting "eParcel Express" from opportunity["<opp_name>"]
    And From '<opp_name>' "Onboarding" user presents "<org_name>__Proposal" document[2] initially in "Draft" status to '<sender>' and later "Accepted"
    And Amends the contract by adding dov language["Open Ended", "Remove Product", "eParcel"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[1] under "<opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 3 "Customer Onboarding" dsr can be seen under "<opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                    | first_name             | last_name          | opp_name                                                       | sender                                    |
      | ROTHSCHILD NOMINEES PTY LTD | Automation_Tester_1005 | Vinoth_Murali_1005 | DoV Decrease using checkout only for EParcel Bundle_31_10_2023 | Automation_Tester_1005 Vinoth_Murali_1005 |

  @GenerateProposal @Decrease
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract amendment for Increase Revenue using "Generate Proposal Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Decrease Revenue"]
    And "Generate Proposal Document" "<org_name>__Proposal" deleting "eParcel Express" from opportunity["<opp_name>"] and "Accepted" after presenting to '<sender>'
    And Amends the contract by adding dov language["Open Ended", "Remove Product", "eParcel"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "<opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 3 "Customer Onboarding" dsr can be seen under "<opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name      | first_name             | last_name          | opp_name                                                           | sender                                    |
      | FUNDI PTY LTD | Automation_Tester_1006 | Vinoth_Murali_1006 | DoV Decrease using generate proposal for EParcel Bundle_31_10_2023 | Automation_Tester_1006 Vinoth_Murali_1006 |

  @GenerateAgreement @Decrease
  Scenario Outline: Existing EParcel customer['<org_name>'] having active EParcel contract initiates Contract amendment for Increase Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Decrease Revenue"]
    And "Generate Agreement Document" with below details after deleting "eParcel Express" from opportunity["<opp_name>"]
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Amends the contract by adding dov language["Open Ended", "Remove Product", "eParcel"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under "<opp_name>" and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    Then Verify whether 3 "Customer Onboarding" dsr can be seen under "<opp_name>" in "Submitted" status

    @PTEST
    Examples:
      | org_name                          | first_name             | last_name          | opp_name                                                                     |
      | NATION FAMILY SUPERANNUATION FUND | Automation_Tester_1009 | Vinoth_Murali_1009 | DoV Decrease using generate agreement document for EParcel Bundle_31_10_2023 |

  @GenerateAgreement @Increase
  Scenario Outline: Existing International customer['<org_name>'] having active International PCMS Bundle contract initiates Contract amendment for Increase Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates dov["Proposal and Contract Flow","Increase Revenue"]
    And "Generate Agreement Document" with below details after adding "Local Pickup and Delivery Services" to the dov opportunity["DoV: <opp_name>"] by validating its attributes "Delivery service", "Monday", "2", "dhaya@auspost.com" and lodgement point "3000"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Amends the contract by adding dov language["Open Ended", "Add new products", "eParcel"], confirming details like key contact['<first_name>' '<last_name>'] and managing billing accounts for "eParcel"
      | entity                        | type         | product           |
      | Australian Postal Corporation | DoV Contract | eParcels Domestic |
    And Creates the contract [0] under '<opp_name>'
    @PTEST
    Examples:
      | org_name                 | first_name | last_name         | opp_name                                                                     |
      | KING SUPERANNUATION FUND | Dhaya      | Automation Tester | DoV Increase using generate agreement document for EParcel Bundle_31_10_2023 |
