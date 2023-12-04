@Regression @APPC @ExistingCustomers @StandardPricing
Feature: "Create Rating Plan" request is "Submitted" to SAP for APPC Existing Customers belonging to Standard Pricing Points [Tier 4 to Tier 8] skipping proposal generation

  @GenerateAgreementManually
  Rule: Sales user creates an opportunity with customer type as "Existing Customer". If required
  a new deal support request of type "Customer Onboarding" with "New Proposal" work type is submitted.
  Proposal generation is skipped by "Generate Manual Agreement Document" while adding pricing points
  for "APPC" product. "New Contract" is created by managing relationships at required levels once
  the proposal is accepted.Finally "Create Rating Plan" request is "Submitted" to SAP once the
  contract is activated.

  @OrganisationLevel
  Scenario Outline: As a '<user_type>' user, on-board existing APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using "Generate Manual Agreement Document" and contract relationship applied at['<relationship>', '<level>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with '<product>' after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details by '<user_type>' user
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When '<user_type>' user "Generate Manual Agreement Document" for '<product>' redirected to contract page
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and '<user_type>' user manually merges '<file_name>' contact after applying relationship at["Add Contract Relationship", "Apply rates to All Accounts for this Organisation", "ST KILDA ROAD POST SHOP"]
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | user_type  | org_name                      | first_name             | last_name          | opp_name                                                                                       | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type | sender                                    | file_name                  | no_of_dsr |
      | Onboarding | PETER R CARTER PROVIDENT FUND | Automation_Tester_1114 | Vinoth_Murali_1114 | As onboarding generate manual agreement document for APPC new customer at org level_31_10_2023 | Australia Post Parcel Contract | 20000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Automation_Tester_1114 Vinoth_Murali_1114 | PDA-COMPASS-0000000084.pdf | 2         |