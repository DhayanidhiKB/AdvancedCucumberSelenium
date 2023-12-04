Feature: Validation of SAP response and other resources generated after submitting the request to Create Billing Account/Rating Plan ID

  Scenario Outline: Validation of Contract, DSR, Contract line item, ALP and Billing account pages
    Given Logged in as Onboarding user
    Then Make sure "Customer Onboarding" '<dsr_name>' is updated with "<billing_account_number>"#, "<sub_account_number>"# and below details
      | dsr_stage | integration_status | integration_status_description | rating_integration_status | rating_integration_status_description            |
      | Submitted | Success            | Success                        | Completed                 | Rating plan(s) have been activated successfully. |
    And In the contract page related to ['<opp_name>','<proposal_name>'] verify whether the relationship is at '<contract_relationship>' level and "Active" SAP contract related to "<billing_account_number>"# with '<lodgement_point>' is generated
    And Validate '<tier>', '<price_structure>' in contract line item
      | zone    | zone1 | zone2   |
      | Capital | Metro | Country |
    And Finally validate whether the Lodgement Point '<lodgement_point>' is "Active"
      | integration_status | integration_status_description |
      | Success            | Success                        |

    @PTEST
    Examples:
      | opp_name                                                | proposal_name | dsr_name   | billing_account_number | sub_account_number | contract_relationship | lodgement_point         | tier                             | price_structure |
      | i8.1_New Customer Standard Pricing Org Level_14_03_2023 | Q-00183917    | DS-0141250 | 4815852                |                    | Organisation          | ST KILDA ROAD POST SHOP | TIER 4 ($20,000.00 - $49,999.99) | Z6              |