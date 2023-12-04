@Regression @APPC @NewCustomers @StandardPricing
Feature: "Create Billing Account/Rating Plan" request is "Submitted" to SAP for APPC New Customers belonging to Standard Pricing Points [Tier 4 to Tier 8] after proposal generation

  @CreateProposal
  Rule: Onboarding creates a new organisation with key contact and sales creates an opportunity
  with customer type as "New Customer". If required a new deal support request of type
  "Customer Onboarding" with "New Proposal" work type is submitted. Proposal is created once
  pricing points are added for "APPC" product and checked out successfully. "New Contract" is
  created by managing relationships at required levels after performing credit assessment once
  the proposal is accepted. Finally "Create Billing Account/Rating Plan" request is "Submitted"
  to SAP once the contract is activated.

  @OrganisationLevel
  Scenario Outline: As a '<user_type>' user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using '<check_out_option>' option and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user '<check_out_option>' '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "R2T APPC Proposal" document[1] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    And '<user_type>' user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for "Charge Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Creates "Standard" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and relationship["Add Contract Relationship"] applied at ["Apply rates to All Accounts for this Organisation", "ST KILDA ROAD POST SHOP"]
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | user_type  | org_name                   | abn         | acn | b_street       | b_city       | b_state | b_code | p_street       | p_city       | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                              | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type | check_out_option | proposal_status | sender                                    | industry | street_number | contract_condition | contract_term | no_of_dsr |
      | Onboarding | NATION SUPERANNUATION FUND | 38310492414 |     | 43 Alma Street | Lower Plenty | VIC     | 3093   | 43 Alma Street | Lower Plenty | VIC     | 3093   | Mr.   | Automation_Tester_1101 | Vinoth_Murali_1101 | vinoth.murali3@auspost.com.au | As onboarding checkout only APPC new customer at org level_31_10_2023 | Australia Post Parcel Contract | 20000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Checkout Only    | Draft           | Automation_Tester_1101 Vinoth_Murali_1101 | B Mining | 43            | Open Ended         |               | 2         |
 #     | Onboarding | NATIONAL ESTATE OF W.A. BUILDERS PTY LTD    | 20009250417 | 009250417 | 55 Carrington Street | NEDLANDS     | WA      | 6009   | 55 Carrington Street | NEDLANDS     | WA      | 6009   | Mr.   | Automation_Tester_1102 | Vinoth_Murali_1102 | vinoth.murali3@auspost.com.au | As onboarding generate proposal for APPC new customer at org level_31_10_2023 | Australia Post Parcel Contract | 49999.99        | TIER 4 ($20,000.00 - $49,999.99)  | 00            | Adhoc              | Generate Proposal Document | Generated       | Automation_Tester_1102 Vinoth_Murali_1102 | B Mining | 55            | Open Ended         |               | 2         |
 #     | Sales      | ROTHSCHILD AUSTRALIA PETROLEUM PTY LIMITED  | 51008936794 | 008936794 | 65 Martin Place  | Sydney       | NSW     | 2000   | 65 Martin Place  | Sydney       | NSW     | 2000   | Mr.   | Automation_Tester_1103 | Vinoth_Murali_1103 | vinoth.murali3@auspost.com.au | As sales checkout only APPC new customer at org level_31_10_2023         | Australia Post Parcel Contract | 50000           | TIER 5 ($50,000.00 - $124,999.99) | 50            | Always On          | Checkout Only              | Draft           | Automation_Tester_1103 Vinoth_Murali_1103 | B Mining | 65            | Fixed Term         | 12            | 2         |
 #     | Sales      | NATIONAL AUSTRALIA FINANCIAL MANAGEMENT LTD | 56000176116 | 000176116 | 1 Denison Street | North Sydney | NSW     | 2060   | 1 Denison Street | North Sydney | NSW     | 2060   | Mr.   | Automation_Tester_1104 | Vinoth_Murali_1104 | vinoth.murali3@auspost.com.au | As sales generate proposal for APPC new customer at org level_31_10_2023 | Australia Post Parcel Contract | 124,999.99      | TIER 5 ($50,000.00 - $124,999.99) | 60            | Always On          | Generate Proposal Document | Generated       | Automation_Tester_1104 Vinoth_Murali_1104 | B Mining | 1             | Fixed Term         | 12            | 2         |

  @BusinessAccountLevel
  Scenario Outline: As a '<user_type>' user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using '<check_out_option>' option and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user '<check_out_option>' '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "R2T APPC Proposal" document[1] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    When '<user_type>' user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for "Charge Account + Sub Account" by entering '<industry>', '<email>', '<street_number>', '<sender>', creates sub account ["SA BA Level Name1", "SA BA Level Name2"] for '<org_name>' with below details and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And Creates "Standard" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and relationship["Add Product specific Billing Account"] applied at ["Apply rates only to new Charge Accounts/Sub Accounts", "ST KILDA ROAD POST SHOP"]
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | user_type  | org_name                                          | abn         | acn       | b_street           | b_city     | b_state | b_code | p_street           | p_city     | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                     | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type | check_out_option           | proposal_status | sender                                    | industry | street_number | contract_condition | contract_term | no_of_dsr |
      | Onboarding | NATION FAMILY SUPERANNUATION FUND                 | 28787868564 |           | 212 Hoddle Street  | Abbotsford | VIC     | 3067   | 212 Hoddle Street  | Abbotsford | VIC     | 3067   | Mr.   | Automation_Tester_1105 | Vinoth_Murali_1105 | vinoth.murali3@auspost.com.au | As Onboarding checkout only APPC new customer at ba level_31_10_2023         | Australia Post Parcel Contract | 500000          | TIER 8 ($500,000.00 - $999,999.99) | 50            | Always On          | Checkout Only              | Draft           | Automation_Tester_1105 Vinoth_Murali_1105 | B Mining | 212           | Fixed Term         | 12            | 2         |
      | Onboarding | NATIONAL PTY LTD                                  | 27104830006 | 104830006 | 9 Bay Street       | Southport  | QLD     | 4215   | 9 Bay Street       | Southport  | QLD     | 4215   | Mr.   | Automation_Tester_1106 | Vinoth_Murali_1106 | vinoth.murali3@auspost.com.au | As Onboarding generate proposal for APPC new customer at ba level_31_10_2023 | Australia Post Parcel Contract | 999999.99       | TIER 8 ($500,000.00 - $999,999.99) | 60            | Always On          | Generate Proposal Document | Generated       | Automation_Tester_1106 Vinoth_Murali_1106 | B Mining | 9             | Fixed Term         | 12            | 2         |
      | Sales      | ROTHSCHILD AUSTRALIA E-FUND INVESTORS PTY LIMITED | 79003208044 | 003208044 | 189 Kent Street    | Sydney     | NSW     | 2000   | 189 Kent Street    | Sydney     | NSW     | 2000   | Mr.   | Automation_Tester_1107 | Vinoth_Murali_1107 | vinoth.murali3@auspost.com.au | As Sales checkout only APPC new customer at ba level_31_10_2023              | Australia Post Parcel Contract | 250000          | TIER 7 ($250,000.00 - $499,999.99) | 00            | Adhoc              | Checkout Only              | Draft           | Automation_Tester_1107 Vinoth_Murali_1107 | B Mining | 189           | Open Ended         |               | 2         |
      | Sales      | NATION AUSTRALIA PTY LIMITED                      | 16100302850 | 100302850 | 18 Cocupara Avenue | Lindfield  | NSW     | 2070   | 18 Cocupara Avenue | Lindfield  | NSW     | 2070   | Mr.   | Automation_Tester_1108 | Vinoth_Murali_1108 | vinoth.murali3@auspost.com.au | As Sales generate proposal for APPC new customer at ba level_31_10_2023      | Australia Post Parcel Contract | 499999.99       | TIER 7 ($250,000.00 - $499,999.99) | 00            | Adhoc              | Generate Proposal Document | Generated       | Automation_Tester_1108 Vinoth_Murali_1108 | B Mining | 18            | Open Ended         |               | 2         |

  @OfflineRates
  Scenario Outline: As a '<user_type>' user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using '<check_out_option>' option and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    When "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    Then From '<opp_name>' '<user_type>' user "Use Offline Rates and Checkout" '<product>' due to ["Technical Issue", "Testing"] after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |

    @PTEST
    Examples:
      | user_type  | org_name                          | abn         | acn | b_street          | b_city     | b_state | b_code | p_street          | p_city     | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                 | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type |
      | Onboarding | NATION FAMILY SUPERANNUATION FUND | 28787868564 |     | 212 Hoddle Street | Abbotsford | VIC     | 3067   | 212 Hoddle Street | Abbotsford | VIC     | 3067   | Mr.   | Automation_Tester_1109 | Vinoth_Murali_1109 | vinoth.murali3@auspost.com.au | As Onboarding Offline Rates for APPC new customer at ba level_31_10_2023 | Australia Post Parcel Contract | 500000          | TIER 8 ($500,000.00 - $999,999.99) | 50            | Always On          |
      | Sales      | NATION FAMILY SUPERANNUATION FUND | 28787868564 |     | 212 Hoddle Street | Abbotsford | VIC     | 3067   | 212 Hoddle Street | Abbotsford | VIC     | 3067   | Mr.   | Automation_Tester_1110 | Vinoth_Murali_1110 | vinoth.murali3@auspost.com.au | As Sales Offline Rates for APPC new customer at ba level_31_10_2023      | Australia Post Parcel Contract | 500000          | TIER 8 ($500,000.00 - $999,999.99) | 50            | Always On          |