@Regression @NewCustomers
Feature: Create Billing Account/Rating Plan ID for APPC New Customers with Delegated Pricing Points [Tier 1 to Tier 3 (or) Tier 9 to Tier 14]

  @APPC @DelegatedPricing
  Rule: On-boarding user creates an organisation and as a sales user create an opportunity with
  customer onboarding dsr (if required). As On-boarding/Sales user create proposal, check-out
  appc products from shopping cart, accept the proposal after credit assessment, create contract
  by adding required relationship. Finally on-boarding user signs the contract and submit
  new billing account/rating plan id request to SAP

  @OnboardingUser @OrgLevel @WithProposal
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to out of delegation pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event    |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Delegated Pricing |
    And From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>'
    And Creates "Delegated" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "with" psr
    And Can submit "Create Billing Account/Rating Plan" request to SAP

    @PTEST
    Examples:
      | org_name                | abn         | acn       | b_street           | b_city   | b_state | b_code | b_country | p_street           | p_city   | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                     | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                            | psr_evaluated_spend | evaluated_spend | tier                                | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | contract_condition | contract_term | relationship              | level                                             | lodgement_point         |
      | Santos (Bawean) Pty Ltd | 17099332162 | 099332162 | 19 Grenfell Street | Adelaide | SA      | 5000   | Australia | 19 Grenfell Street | Adelaide | SA      | 5000   | Australia | Mr.   | Automation_Tester_075 | Vinoth_Murali_075 | vinoth.murali3@auspost.com.au | As Onboarding New Customer APPC Delegate Pricing TIER 9 Org Level_30_08_2023 | Australia Post Parcel Contract | Adhoc            | 0                  | $1,000,000         | 1500              | VIN-TESTING-0123456789 | Z3             | TIER 9 ($1,000,000 - $2,499,999.99) | 1250000             | 1250000         | TIER 9 ($1,000,000 - $2,499,999.99) | 00            | Adhoc              | Automation_Tester_075 Vinoth_Murali_075 | Charge Account | B Mining | 19            | Open Ended         |               | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @OnboardingUser @BALevel @WithProposal
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to out of delegation pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event    |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Delegated Pricing |
    And From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and creates sub account ['<sub_account_name>', '<sub_account_name2>'] for '<org_name>' with below details
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And Creates "Delegated" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "with" psr
    And Can submit "Create Billing Account/Rating Plan" request to SAP

    @PTEST
    Examples:
      | org_name                | abn         | acn       | b_street          | b_city   | b_state | b_code | b_country | p_street          | p_city   | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                     | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                                  | psr_evaluated_spend | evaluated_spend | tier                                      | transit_cover | transit_cover_type | sender                                  | account_type                 | industry | street_number | sub_account_name | sub_account_name2 | contract_condition | contract_term | relationship                         | level                                                | lodgement_point         |
      | Santos (Popodi) Pty Ltd | 18104796410 | 104796410 | 55 Halifax Street | Adelaide | SA      | 5000   | Australia | 55 Halifax Street | Adelaide | SA      | 5000   | Australia | Mr.   | Automation_Tester_076 | Vinoth_Murali_076 | vinoth.murali3@auspost.com.au | As Onboarding New Customer APPC Delegate Pricing TIER 13 BA Level_30_08_2023 | Australia Post Parcel Contract | Adhoc            | 0                  | $20,000,000.00     | 15000             | VIN-TESTING-0123456789 | Z9             | TIER 13 ($20,000,000.00 - $39,999,999.99) | 25000000            | 25000000        | TIER 13 ($20,000,000.00 - $39,999,999.99) | 00            | Adhoc              | Automation_Tester_076 Vinoth_Murali_076 | Charge Account + Sub Account | B Mining | 55            | SA BA Level Name | SA BA Level Name2 | Open Ended         |               | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |

  @SalesUser @OrgLevel @WithProposal
  Scenario Outline: As a Sales user, on-board new APPC customer['<org_name>'] belonging to out of delegation pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event    |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Delegated Pricing |
    And From '<opp_name>' "Sales" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>'
    And Creates "Delegated" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 1 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "with" psr
    And Can submit "Create Billing Account/Rating Plan" request to SAP

    @PTEST
    Examples:
      | org_name                            | abn         | acn       | b_street        | b_city | b_state | b_code | b_country | p_street        | p_city | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                 | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                                | psr_evaluated_spend | evaluated_spend | tier                                    | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | contract_condition | contract_term | relationship              | level                                             | lodgement_point         |
      | NM ROTHSCHILD AUST HOLDINGS PTY LTD | 26008458339 | 008458339 | 207 Kent Street | Sydney | NSW     | 2000   | Australia | 207 Kent Street | Sydney | NSW     | 2000   | Australia | Mr.   | Automation_Tester_077 | Vinoth_Murali_077 | vinoth.murali3@auspost.com.au | As Sales New Customer APPC Delegate Pricing TIER 10 Org Level_30_08_2023 | Australia Post Parcel Contract | Adhoc            | 0                  | $2,500,000.00      | 25000             | VIN-TESTING-0123456789 | Z3             | TIER 10 ($2,500,000.00 - $4,999,999.99) | 3500000             | 3500000         | TIER 10 ($2,500,000.00 - $4,999,999.99) | 00            | Adhoc              | Automation_Tester_077 Vinoth_Murali_077 | Charge Account | B Mining | 207           | Open Ended         |               | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @SalesUser @BALevel @WithProposal
  Scenario Outline: As a Sales user, on-board new APPC customer['<org_name>'] belonging to out of delegation pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', '<b_country>'], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', '<p_country>'] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event    |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Delegated Pricing |
    And From '<opp_name>' "Sales" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and creates sub account ['<sub_account_name>', '<sub_account_name2>'] for '<org_name>' with below details
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And Creates "Delegated" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
      | entity                        | type         | product         |
      | Australian Postal Corporation | New Contract | Parcel Contract |
    And Onboarding user, manually sign the contract under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 1 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "with" psr
    And Can submit "Create Billing Account/Rating Plan" request to SAP

    @PTEST
    Examples:
      | org_name             | abn         | acn       | b_street            | b_city   | b_state | b_code | b_country | p_street            | p_city   | p_state | p_code | p_country | title | first_name            | last_name         | email                         | opp_name                                                                | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                                  | psr_evaluated_spend | evaluated_spend | tier                                      | transit_cover | transit_cover_type | sender                                  | account_type                 | industry | street_number | sub_account_name | sub_account_name2 | contract_condition | contract_term | relationship                         | level                                                | lodgement_point         |
      | Santos (NGA) Pty Ltd | 25091824798 | 091824798 | 185 Morphett Street | Adelaide | SA      | 5000   | Australia | 185 Morphett Street | Adelaide | SA      | 5000   | Australia | Mr.   | Automation_Tester_078 | Vinoth_Murali_078 | vinoth.murali3@auspost.com.au | As Sales New Customer APPC Delegate Pricing TIER 12 BA Level_30_08_2023 | Australia Post Parcel Contract | Adhoc            | 0                  | $10,000,000.00     | 100000            | VIN-TESTING-0123456789 | Z9             | TIER 12 ($10,000,000.00 - $19,999,999.99) | 15000000            | 15000000        | TIER 12 ($10,000,000.00 - $19,999,999.99) | 00            | Adhoc              | Automation_Tester_078 Vinoth_Murali_078 | Charge Account + Sub Account | B Mining | 185           | SA BA Level Name | SA BA Level Name2 | Open Ended         |               | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |