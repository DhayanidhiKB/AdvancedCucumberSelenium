@Regression @NewCustomers
Feature: Create Billing Account/Rating Plan ID for APPC New Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @APPC @StandardPricing
  Rule: On-boarding user creates an organisation and as a sales user create an opportunity with
  customer onboarding dsr (if required). As On-boarding/Sales user create proposal, check-out
  appc products from shopping cart, accept the proposal after credit assessment, create contract
  by adding required relationship. Finally on-boarding user signs the contract and submit
  new billing account/rating plan id request to SAP

  @OnboardingUser @OrgLevel
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    And As "Onboarding" user, a "Draft" of "R2T APPC Proposal" document belonging to '<opp_name>' is presented to '<sender>' and later "Accepted"
    When "Onboarding" user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Creates "Standard" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | org_name                   | abn         | acn | b_street       | b_city       | b_state | b_code | b_country | p_street       | p_city       | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                 | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | contract_condition | contract_term | relationship              | level                                             | lodgement_point         |
      | NATION SUPERANNUATION FUND | 38310492414 |     | 43 Alma Street | Lower Plenty | VIC     | 3093   | Australia | 43 Alma Street | Lower Plenty | VIC     | 3093   | Australia | Mr.   | Automation_Tester_201 | Vinoth_Murali_201 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at org level as onboarding_28_09_2023 | Australia Post Parcel Contract | 30000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Automation_Tester_201 Vinoth_Murali_201 | Charge Account | B Mining | 43            | Open Ended         |               | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @OnboardingUser @BALevel
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    And As "Onboarding" user, a "Draft" of "R2T APPC Proposal" document belonging to '<opp_name>' is presented to '<sender>' and later "Accepted"
    When "Onboarding" user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>', creates sub account ['<sub_account_name>', '<sub_account_name2>'] for '<org_name>' with below details and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And Creates "Standard" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | org_name                          | abn         | acn | b_street          | b_city     | b_state | b_code | b_country | p_street          | p_city     | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type | sender                                  | account_type                 | industry | street_number | sub_account_name | sub_account_name2 | contract_condition | contract_term | relationship                         | level                                                | lodgement_point         |
      | NATION FAMILY SUPERANNUATION FUND | 28787868564 |     | 212 Hoddle Street | Abbotsford | VIC     | 3067   | Australia | 212 Hoddle Street | Abbotsford | VIC     | 3067   | Australia | Mr.   | Automation_Tester_202 | Vinoth_Murali_202 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at ba level as onboarding_28_09_2023 | Australia Post Parcel Contract | 700000          | TIER 8 ($500,000.00 - $999,999.99) | 60            | Always On          | Automation_Tester_202 Vinoth_Murali_202 | Charge Account + Sub Account | B Mining | 212           | SA BA Level Name | SA BA Level Name2 | Fixed Term         | 12            | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |

  @SalesUser @OrgLevel
  Scenario Outline: As a Sales user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And From '<opp_name>' "Sales" user "Checkout Only" '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    And As "Sales" user, a "Draft" of "R2T APPC Proposal" document belonging to '<opp_name>' is presented to '<sender>' and later "Accepted"
    When "Sales" user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Creates "Standard" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 1 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | org_name                                   | abn         | acn       | b_street        | b_city | b_state | b_code | b_country | p_street        | p_city | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                            | product                        | evaluated_spend | tier                              | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | contract_condition | contract_term | relationship              | level                                             | lodgement_point         |
      | ROTHSCHILD AUSTRALIA PETROLEUM PTY LIMITED | 51008936794 | 008936794 | 65 Martin Place | Sydney | NSW     | 2000   | Australia | 65 Martin Place | Sydney | NSW     | 2000   | Australia | Mr.   | Automation_Tester_203 | Vinoth_Murali_203 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at org level as sales_28_09_2023 | Australia Post Parcel Contract | 90000           | TIER 5 ($50,000.00 - $124,999.99) | 00            | Adhoc              | Automation_Tester_203 Vinoth_Murali_203 | Charge Account | B Mining | 65            | Open Ended         |               | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @SalesUser @BALevel
  Scenario Outline: As a Sales user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And From '<opp_name>' "Sales" user "Checkout Only" '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    And As "Sales" user, a "Draft" of "R2T APPC Proposal" document belonging to '<opp_name>' is presented to '<sender>' and later "Accepted"
    When "Sales" user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>', creates sub account ['<sub_account_name>', '<sub_account_name2>'] for '<org_name>' with below details and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And Creates "Standard" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 1 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | org_name                                          | abn         | acn       | b_street        | b_city | b_state | b_code | b_country | p_street        | p_city | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                           | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type | sender                                  | account_type                 | industry | street_number | sub_account_name | sub_account_name2 | contract_condition | contract_term | relationship                         | level                                                | lodgement_point         |
      | ROTHSCHILD AUSTRALIA E-FUND INVESTORS PTY LIMITED | 79003208044 | 003208044 | 189 Kent Street | Sydney | NSW     | 2000   | Australia | 189 Kent Street | Sydney | NSW     | 2000   | Australia | Mr.   | Automation_Tester_204 | Vinoth_Murali_204 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at ba level as sales_28_09_2023 | Australia Post Parcel Contract | 350000          | TIER 7 ($250,000.00 - $499,999.99) | 70            | Always On          | Automation_Tester_204 Vinoth_Murali_204 | Charge Account + Sub Account | B Mining | 189           | SA BA Level Name | SA BA Level Name2 | Fixed Term         | 12            | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |

  @OnboardingUser @SkipProposal @OrgLevel
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with '<product>' after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details by "Onboarding" user
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When "Onboarding" user performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and "Generate Agreement Document" for '<product>'
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and contract relationship is applied at['<relationship>', '<level>', '<lodgement_point>'] by "Onboarding" user
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | org_name                      | abn         | acn | b_street       | b_city    | b_state | b_code | b_country | p_street       | p_city    | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                               | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | relationship              | level                                             | lodgement_point         |
      | PETER R CARTER PROVIDENT FUND | 68493895799 |     | 66 Hyde Street | Bellingen | NSW     | 2454   | Australia | 66 Hyde Street | Bellingen | NSW     | 2454   | Australia | Mr.   | Automation_Tester_205 | Vinoth_Murali_205 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at org level as onboarding skip proposal_28_09_2023 | Australia Post Parcel Contract | 30000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Automation_Tester_205 Vinoth_Murali_205 | Charge Account | B Mining | 66            | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @OnboardingUser @SkipProposal @BALevel
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with '<product>' after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details by "Onboarding" user
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When "Onboarding" user performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and creates "Generate Agreement Document" along with sub account ['<sub_account_name>', '<sub_account_name2>'] with below details
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and contract relationship is applied at['<relationship>', '<level>', '<lodgement_point>'] by "Onboarding" user
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | org_name                  | abn         | acn | b_street            | b_city    | b_state | b_code | b_country | p_street            | p_city    | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                              | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type | sender                                  | account_type                 | industry | street_number | sub_account_name | sub_account_name2 | relationship                         | level                                                | lodgement_point         |
      | FOUND SUPERANNUATION FUND | 19459519126 |     | 13 Marfayley Street | Salisbury | QLD     | 4107   | Australia | 13 Marfayley Street | Salisbury | QLD     | 4107   | Australia | Mr.   | Automation_Tester_206 | Vinoth_Murali_206 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at ba level as onboarding skip proposal_28_09_2023 | Australia Post Parcel Contract | 350000          | TIER 7 ($250,000.00 - $499,999.99) | 70            | Always On          | Automation_Tester_206 Vinoth_Murali_206 | Charge Account + Sub Account | B Mining | 13            | SA BA Level Name | SA BA Level Name2 | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |

  @SalesUser @SkipProposal @OrgLevel
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And '<opp_name>' cart saved with '<product>' after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details by "Sales" user
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When "Sales" user performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and "Generate Agreement Document" for '<product>'
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and contract relationship is applied at['<relationship>', '<level>', '<lodgement_point>'] by "Sales" user
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | org_name                | abn         | acn       | b_street           | b_city   | b_state | b_code | b_country | p_street           | p_city   | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                          | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | relationship              | level                                             | lodgement_point         |
      | Santos (Bawean) Pty Ltd | 17099332162 | 099332162 | 19 Grenfell Street | Adelaide | SA      | 5000   | Australia | 19 Grenfell Street | Adelaide | SA      | 5000   | Australia | Mr.   | Automation_Tester_207 | Vinoth_Murali_207 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at org level as sales skip proposal_28_09_2023 | Australia Post Parcel Contract | 30000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Automation_Tester_207 Vinoth_Murali_207 | Charge Account | B Mining | 19            | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @SalesUser @SkipProposal @BALevel
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to standard pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And '<opp_name>' cart saved with '<product>' after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details by "Onboarding" user
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When "Sales" user performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and creates "Generate Agreement Document" along with sub account ['<sub_account_name>', '<sub_account_name2>'] with below details
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and contract relationship is applied at['<relationship>', '<level>', '<lodgement_point>'] by "Sales" user
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | org_name                | abn         | acn       | b_street          | b_city   | b_state | b_code | b_country | p_street          | p_city   | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                         | product                        | evaluated_spend | tier                               | transit_cover | transit_cover_type | sender                                  | account_type                 | industry | street_number | sub_account_name | sub_account_name2 | relationship                         | level                                                | lodgement_point         |
      | Santos (Popodi) Pty Ltd | 18104796410 | 104796410 | 55 Halifax Street | Adelaide | SA      | 5000   | Australia | 55 Halifax Street | Adelaide | SA      | 5000   | Australia | Mr.   | Automation_Tester_208 | Vinoth_Murali_208 | vinoth.murali3@auspost.com.au | APPC new customer standard pricing at ba level as sales skip proposal_28_09_2023 | Australia Post Parcel Contract | 350000          | TIER 7 ($250,000.00 - $499,999.99) | 70            | Always On          | Automation_Tester_208 Vinoth_Murali_208 | Charge Account + Sub Account | B Mining | 55            | SA BA Level Name | SA BA Level Name2 | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |