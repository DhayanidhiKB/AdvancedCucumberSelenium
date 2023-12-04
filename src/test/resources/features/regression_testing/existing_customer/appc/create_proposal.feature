@Regression @APPC @ExistingCustomers @StandardPricing
Feature: "Create Rating Plan" request is "Submitted" to SAP for APPC Existing Customers belonging to Standard Pricing Points [Tier 4 to Tier 8] after proposal generation

  @CreateProposal
  Rule: Sales user creates an opportunity with customer type as "Existing Customer". If required
  a new deal support request of type "Customer Onboarding" with "New Proposal" work type is submitted.
  Proposal is created once pricing points are added for "APPC" product and checked out successfully.
  "New Contract" is created by managing relationships at required levels once the proposal is accepted.
  Finally "Create Rating Plan" request is "Submitted" to SAP once the contract is activated.

  @OrganisationLevel
  Scenario Outline: As a '<user_type>' user, on-board existing APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using '<check_out_option>' option and contract relationship applied at['<relationship>', '<level>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user '<check_out_option>' '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "R2T APPC Proposal" document[0] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    And Creates "Standard" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and relationship["Add Contract Relationship"] applied at ["Apply rates to All Accounts for this Organisation", "ST KILDA ROAD POST SHOP"]
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
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
      | user_type  | org_name                                          | first_name             | last_name          | opp_name                                                                          | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type | check_out_option           | proposal_status | sender                                    | contract_condition | contract_term | no_of_dsr |
      | Onboarding | NATION FAMILY SUPERANNUATION FUND                 | Automation_Tester_1105 | Vinoth_Murali_1105 | As Onboarding checkout only APPC existing customer at ba level_31_10_2023         | Australia Post Parcel Contract | 500000          | TIER 8 ($500,000.00 - $999,999.99) | 50            | Always On          | Checkout Only              | Draft           | Automation_Tester_1105 Vinoth_Murali_1105 | Fixed Term         | 12            | 2         |
      | Onboarding | NATIONAL PTY LTD                                  | Automation_Tester_1106 | Vinoth_Murali_1106 | As Onboarding generate proposal for APPC existing customer at ba level_31_10_2023 | Australia Post Parcel Contract | 999999.99       | TIER 8 ($500,000.00 - $999,999.99) | 60            | Always On          | Generate Proposal Document | Generated       | Automation_Tester_1106 Vinoth_Murali_1106 | Fixed Term         | 12            | 2         |
      | Sales      | ROTHSCHILD AUSTRALIA E-FUND INVESTORS PTY LIMITED | Automation_Tester_1107 | Vinoth_Murali_1107 | As Sales checkout only APPC existing customer at ba level_31_10_2023              | Australia Post Parcel Contract | 250000          | TIER 7 ($250,000.00 - $499,999.99) | 00            | Adhoc              | Checkout Only              | Draft           | Automation_Tester_1107 Vinoth_Murali_1107 | Open Ended         |               | 2         |
      | Sales      | NATION AUSTRALIA PTY LIMITED                      | Automation_Tester_1108 | Vinoth_Murali_1108 | As Sales generate proposal for APPC existing customer at ba level_31_10_2023      | Australia Post Parcel Contract | 499999.99       | TIER 7 ($250,000.00 - $499,999.99) | 00            | Adhoc              | Generate Proposal Document | Generated       | Automation_Tester_1108 Vinoth_Murali_1108 | Open Ended         |               | 2         |

  @OfflineRates
  Scenario Outline: As a '<user_type>' user, on-board existing APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using '<check_out_option>' option and contract relationship applied at['<relationship>', '<level>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    When "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    Then From '<opp_name>' '<user_type>' user "Use Offline Rates and Checkout" '<product>' due to ["Technical Issue", "Testing"] after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |

    @PTEST
    Examples:
      | user_type  | org_name                          | first_name             | last_name          | opp_name                                                                      | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type |
      | Onboarding | NATION FAMILY SUPERANNUATION FUND | Automation_Tester_1109 | Vinoth_Murali_1109 | As Onboarding Offline Rates for APPC existing customer at ba level_31_10_2023 | Australia Post Parcel Contract | 500000          | TIER 8 ($500,000.00 - $999,999.99) | 50            | Always On          |
      | Sales      | NATION FAMILY SUPERANNUATION FUND | Automation_Tester_1110 | Vinoth_Murali_1110 | As Sales Offline Rates for APPC existing customer at ba level_31_10_2023      | Australia Post Parcel Contract | 500000          | TIER 8 ($500,000.00 - $999,999.99) | 50            | Always On          |