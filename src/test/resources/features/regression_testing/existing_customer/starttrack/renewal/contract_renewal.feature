@Regression @ExistingCustomer
Feature: Performing Standard Apttus Renewal for an existing customer with an active startrack contract in place

  @Renewal @StarTrack
  Rule: Existing customer has an active contract in place. So that he can initiate "Standard Apttus Renewal"
  from contract page, for to modify the shopping cart by increasing (or) decreasing the products. A Renewal
  opportunity is auto-generated from which proposal is generated and accepted. Finally renew the contract by
  generating contract documents and managing account numbers

  @GenerateAgreement @Increase
  Scenario Outline: Existing StarTrack customer['<org_name>'] having active EParcel contract initiates Contract renewal for Increase Revenue using "Generate Agreement Document" option
    When Onboarding user opens the active contract from '<opp_name>' initiates "Standard Apttus Renewal"
    And "Generate Agreement Document" with below details after adding "Road Express - STE" to the dov opportunity["Renewal: <opp_name>"] by validating its attributes "EXP", "3000-MELBOURNE", "3000-MELBOURNE", "Tier 1"
      | entity                    | type             | product   |
      | StarTrack Express Pty Ltd | Renewal Contract | StarTrack |
    And Creates the contract [0] under "Renewal: <opp_name>"

    @PTEST
    Examples:
      | opp_name          |
      | StarTrackProducts |