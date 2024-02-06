@Regression @ExistingCustomer
Feature: Performing Deed of Variation for an existing customer with an active eParcel contract in place

  @DoV @EParcel
  Rule: Existing customer has an active contract in place. So that he can initiate "Deed of Variation"
  from contract page, for to modify the shopping cart by increasing (or) decreasing the products. A DoV
  opportunity is auto-generated from which proposal is generated and accepted. Finally amend the contract
  after adding dov language and generating contract documents by managing account numbers

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
      | first_name | last_name         | opp_name                                                                     |
      | Dhaya      | Automation Tester | DoV Increase using generate agreement document for EParcel Bundle_31_10_2023 |
