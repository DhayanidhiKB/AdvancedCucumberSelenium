@Regression @EParcel @ExistingCustomers
Feature: "Create Rating Plan" for EParcel Existing Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @CreateProposal
  Rule: Sales user creates an opportunity with customer type as "Existing Customer". If required
  a new deal support request of type "Customer Onboarding" with "New Proposal" work type is submitted.
  Proposal is created once pricing points are added for "EParcel" product and checked out successfully.
  "New Contract" is created by managing relationships at required levels once the proposal is accepted.
  Finally "Create Rating Plan" request is "Submitted" to SAP once the contract is activated.

  @International
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "CheckOut Only" with EParcel International belonging to revenue_commitment['<revenue_commitment>'] and integration_platforms['<integration_platforms>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user '<check_out_option>' '<product>' after validating its attributes '<product_industry>', '<domestic_customer>', '<revenue_commitment>', '<integration_platforms>' and adding lodgement point '<lodgement_point>'
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "<org_name>__Proposal" document[0] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    And '<user_type>' user changes '<opp_name>' stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Creates contract from "Accepted" proposal by confirming details like key contact['<first_name>' '<last_name>'] and managing account numbers
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | user_type  | org_name                          | first_name             | last_name          | opp_name                                                                           | product                                                | product_industry      | domestic_customer | revenue_commitment | integration_platforms | lodgement_point | check_out_option           | proposal_status | sender                                    | no_of_dsr |
     # | Onboarding | THE NATION FAMILY SUPERANNUATION FUND              | Automation_Tester_1001 | Vinoth_Murali_1001 | As Onboarding checkout only EParcel International existing customer_31_10_2023     | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Checkout Only              | Draft           | Automation_Tester_1001 Vinoth_Murali_1001 | 2         |
      | Onboarding | NATION FAMILY SUPERANNUATION FUND | Automation_Tester_1105 | Vinoth_Murali_1105 | As Onboarding generate proposal EParcel International existing customer_31_10_2023 | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1002 Vinoth_Murali_1002 | 2         |
     # | Sales      | Santos (NGA) Pty Ltd                               | Automation_Tester_1003 | Vinoth_Murali_1003 | As Sales checkout only EParcel International existing customer_31_10_2023          | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Checkout Only              | Draft           | Automation_Tester_1003 Vinoth_Murali_1003 | 2         |
     # | Sales      | ROTHSCHILD AUSTRALIA AIRCRAFT LEASING PTY. LIMITED | Automation_Tester_1004 | Vinoth_Murali_1004 | As Sales generate proposal EParcel International existing customer_31_10_2023      | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1004 Vinoth_Murali_1004 | 2         |

  @EParcelBundle
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "CheckOut Only" with EParcel Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "eParcel" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "eParcel" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    And From '<opp_name>' '<user_type>' user '<check_out_option>' "eParcel Express" after validating its attributes '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>'
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "<org_name>__Proposal" document[0] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    And '<user_type>' user changes '<opp_name>' stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Creates contract from "Accepted" proposal by confirming details like key contact['<first_name>' '<last_name>'] and managing account numbers
      | entity                        | type         | product           |
      | Australian Postal Corporation | New Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Rating Plan" request is "Submitted" for "eParcel" to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | user_type  | org_name                    | first_name    | last_name         | opp_name                                                                | category            | price_structure | lodgement_code | transit_cover | lodgement_point | check_out_option | proposal_status | sender                          | no_of_dsr |
      | Onboarding | ROTHSCHILD NOMINEES PTY LTD | Dhayanidhi KB | Automation Tester | As Onboarding checkout only EParcel Bundle existing customer_31_10_2023 | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Checkout Only    | Draft           | Dhayanidhi KB Automation Tester | 2         |
    #  | Onboarding | FUNDI PTY LTD               | Automation_Tester_1006 | Vinoth_Murali_1006 | As Onboarding generate proposal EParcel Bundle existing customer_31_10_2023 | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1006 Vinoth_Murali_1006 | 2         |
#      | Sales      | NM ROTHSCHILD AUST HOLDINGS PTY LTD | Automation_Tester_1007 | Vinoth_Murali_1007 | As Sales checkout only EParcel Bundle existing customer_31_10_2023          | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Checkout Only              | Draft           | Automation_Tester_1007 Vinoth_Murali_1007 | 2  |
#      | Sales      | KING SUPERANNUATION FUND    | Automation_Tester_1008 | Vinoth_Murali_1008 | As Sales generate proposal EParcel Bundle existing customer_31_10_2023      | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1008 Vinoth_Murali_1008 | 2         |

  @OfflineRates
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "CheckOut Only" with EParcel International belonging to revenue_commitment['<revenue_commitment>'] and integration_platforms['<integration_platforms>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user "Use Offline Rates and Checkout" '<product>' due to ["Technical Issue", "Testing"] after validating its attributes '<product_industry>', '<domestic_customer>', '<revenue_commitment>', '<integration_platforms>' and adding lodgement point '<lodgement_point>'

    @PTEST
    Examples:
      | user_type  | org_name         | first_name             | last_name          | opp_name                                                                          | product                                                | product_industry      | domestic_customer | revenue_commitment | integration_platforms | lodgement_point |
      | Onboarding | NATIONAL PTY LTD | Automation_Tester_1010 | Vinoth_Murali_1010 | As Onboarding offline checkout EParcel International existing customer_31_10_2023 | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            |


