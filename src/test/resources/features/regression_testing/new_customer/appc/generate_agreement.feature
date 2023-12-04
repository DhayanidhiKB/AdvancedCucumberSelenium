@Regression @APPC @NewCustomers @StandardPricing
Feature: "Create Billing Account/Rating Plan" request is "Submitted" to SAP for APPC New Customers belonging to Standard Pricing Points [Tier 4 to Tier 8] skipping proposal generation

  @GenerateAgreement
  Rule: Onboarding creates a new organisation with key contact and sales creates an opportunity
  with customer type as "New Customer". If required a new deal support request of type
  "Customer Onboarding" with "New Proposal" work type is submitted. Proposal generation is
  skipped by "Generate Agreement Document" while adding pricing points for "APPC" product. "New Contract" is
  created by managing relationships at required levels after performing credit assessment.
  Finally "Create Billing Account/Rating Plan" request is "Submitted" to SAP once the contract is activated.

  @OrganisationLevel
  Scenario Outline: As a '<user_type>' user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using "Generate Agreement Document" and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with '<product>' after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details by '<user_type>' user
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When '<user_type>' user performs credit assessment of the '<opp_name>' using abn['<abn>'] for "Charge Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and "Generate Agreement Document" for '<product>' redirected to contract page
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and contract relationship is applied at["Add Contract Relationship", "Apply rates to All Accounts for this Organisation", "ST KILDA ROAD POST SHOP"] by '<user_type>' user
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
      | user_type  | org_name                      | abn         | acn       | b_street           | b_city    | b_state | b_code | p_street           | p_city    | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                                | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type | sender                                    | industry | street_number | no_of_dsr |
      | Onboarding | PETER R CARTER PROVIDENT FUND | 68493895799 |           | 66 Hyde Street     | Bellingen | NSW     | 2454   | 66 Hyde Street     | Bellingen | NSW     | 2454   | Mr.   | Automation_Tester_1111 | Vinoth_Murali_1111 | vinoth.murali3@auspost.com.au | As Onboarding generate agreement document for APPC new customer at org level_31_10_2023 | Australia Post Parcel Contract | 20000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Automation_Tester_1111 Vinoth_Murali_1111 | B Mining | 66            | 2         |
      | Sales      | Santos (Bawean) Pty Ltd       | 17099332162 | 099332162 | 19 Grenfell Street | Adelaide  | SA      | 5000   | 19 Grenfell Street | Adelaide  | SA      | 5000   | Mr.   | Automation_Tester_1112 | Vinoth_Murali_1112 | vinoth.murali3@auspost.com.au | As Sales generate agreement document for APPC new customer at org level_31_10_2023      | Australia Post Parcel Contract | 49999.99        | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Automation_Tester_1112 Vinoth_Murali_1112 | B Mining | 19            | 2         |

  @BusinessAccountLevel
  Scenario Outline: As a '<user_type>' user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, lodgement zones[Capital, Country, Metro] using "Generate Agreement Document" and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with '<product>' after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details by '<user_type>' user
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When '<user_type>' user performs credit assessment of the '<opp_name>' using abn['<abn>'] for "Charge Account + Sub Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and creates "Generate Agreement Document" along with sub account ["SA BA Level Name1", "SA BA Level Name2"] with below details
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and contract relationship is applied at["Add Product specific Billing Account", "Apply rates only to new Charge Accounts/Sub Accounts", "ST KILDA ROAD POST SHOP"] by '<user_type>' user
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
      | user_type  | org_name                  | abn         | acn       | b_street            | b_city    | b_state | b_code | p_street            | p_city    | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                               | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type | sender                                    | industry | street_number | no_of_dsr |
      | Onboarding | FOUND SUPERANNUATION FUND | 19459519126 |           | 13 Marfayley Street | Salisbury | QLD     | 4107   | 13 Marfayley Street | Salisbury | QLD     | 4107   | Mr.   | Automation_Tester_1113 | Vinoth_Murali_1113 | vinoth.murali3@auspost.com.au | As onboarding generate agreement document for APPC new customer at ba level_31_10_2023 | Australia Post Parcel Contract | 350000          | TIER 7 ($250,000.00 - $499,999.99) | 70            | Always On          | Automation_Tester_1113 Vinoth_Murali_1113 | B Mining | 13            | 2         |
      | Sales      | Santos (Popodi) Pty Ltd   | 18104796410 | 104796410 | 55 Halifax Street   | Adelaide  | SA      | 5000   | 55 Halifax Street   | Adelaide  | SA      | 5000   | Mr.   | Automation_Tester_1114 | Vinoth_Murali_1114 | vinoth.murali3@auspost.com.au | As sales generate agreement document for APPC new customer at ba level_31_10_2023      | Australia Post Parcel Contract | 350000          | TIER 7 ($250,000.00 - $499,999.99) | 70            | Always On          | Automation_Tester_1114 Vinoth_Murali_1114 | B Mining | 55            | 2         |