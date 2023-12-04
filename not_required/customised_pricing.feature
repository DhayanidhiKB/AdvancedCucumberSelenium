@Regression @NewCustomers
Feature: Create Billing Account/Rating Plan ID for APPC New Customers with Custom Pricing Points [Tier 1 to Tier 3 (or) Tier 9 to Tier 14]

  @APPC @CustomPricing
  Rule: On-boarding user creates an organisation and as a sales user create an opportunity with
  customer onboarding dsr (if required). As On-boarding/Sales user create proposal, check-out
  appc products from shopping cart, accept the proposal after credit assessment, create contract
  by adding required relationship. Finally on-boarding user signs the contract and submit
  new billing account/rating plan id request to SAP

    #SAP ERP and COMPASS feature rate card NOT synched and only minmum tiers
    #are synced manually by pricing team

  @OnboardingUser @OrgLevel @WithProposal
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to custom pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event     |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Customised Pricing |
    And From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>'
    And Creates "Customised" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
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
      | org_name                | abn         | first_name            | last_name         | email                         | opp_name                                                                        | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                                | psr_evaluated_spend | evaluated_spend | tier                                    | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | contract_condition | contract_term | relationship              | level                                             | lodgement_point         |
      | NATIONAL WEST PTY. LTD. | 54106602466 | Automation_Tester_081 | Vinoth_Murali_081 | vinoth.murali3@auspost.com.au | As Onboarding New Customer APPC Customised Pricing TIER 9 Org Level_21_08_2023  | Australia Post Parcel Contract | Adhoc            | 00                 | $1,000,000         | 1500              | PDA-COMPASS-0000002492 | Z3             | TIER 9 ($1,000,000 - $2,499,999.99)     | 1000000             | 1000000         | TIER 9 ($1,000,000 - $2,499,999.99)     | 00            | Adhoc              | Automation_Tester_081 Vinoth_Murali_081 | Charge Account | B Mining | 66            | Open Ended         |               | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |
      | SANTOS UNIT TRUST       | 13290675317 | Automation_Tester_083 | Vinoth_Murali_083 | vinoth.murali3@auspost.com.au | As Onboarding New Customer APPC Customised Pricing TIER 11 Org Level_21_08_2023 | Australia Post Parcel Contract | Always On        | 70                 | $5,000,000.00      | 5000              | PDA-COMPASS-0000002493 | Z9             | TIER 11 ($5,000,000.00 - $9,999,999.99) | 7500000             | 7500000         | TIER 11 ($5,000,000.00 - $9,999,999.99) | 60            | Always On          | Automation_Tester_083 Vinoth_Murali_083 | Charge Account | B Mining | 24            | Fixed Term         | 12            | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @OnboardingUser @BALevel @WithProposal
  Scenario Outline: As a Onboarding user, on-board new APPC customer['<org_name>'] belonging to custom pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event     |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Customised Pricing |
    And From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and creates sub account ['<sub_account_name>', '<sub_account_name2>'] for '<org_name>' with below details
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And Creates "Customised" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
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
      | org_name                                  | abn         | first_name            | last_name         | email                         | opp_name                                                                       | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                                  | psr_evaluated_spend | evaluated_spend | tier                                      | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | sub_account_name | sub_account_name2 | contract_condition | contract_term | relationship                         | level                                                | lodgement_point         |
      | The Trustee for Najoe Superannuation Fund | 76345332369 | Automation_Tester_082 | Vinoth_Murali_082 | vinoth.murali3@auspost.com.au | As Onboarding New Customer APPC Customised Pricing TIER 13 BA Level_21_08_2023 | Australia Post Parcel Contract | Always On        | 60                 | $20,000,000.00     | 15000             | PDA-COMPASS-0000001722 | Z6             | TIER 13 ($20,000,000.00 - $39,999,999.99) | 25000000            | 25000000        | TIER 13 ($20,000,000.00 - $39,999,999.99) | 20            | Adhoc              | Automation_Tester_082 Vinoth_Murali_082 | Charge Account | B Mining | 12            | SA BA Level Name | SA BA Level Name2 | Fixed Term         | 12            | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |

  @SalesUser @OrgLevel @WithProposal
  Scenario Outline: As a Sales user, on-board new APPC customer['<org_name>'] belonging to custom pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event     |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Customised Pricing |
    And From '<opp_name>' "Sales" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>'
    And Creates "Customised" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
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
      | org_name                       | abn         | first_name            | last_name         | email                         | opp_name                                                                  | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                                  | psr_evaluated_spend | evaluated_spend | tier                                      | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | contract_condition | contract_term | relationship              | level                                             | lodgement_point         |
      | THE BH & P SUPERANNUATION FUND | 21569439143 | Automation_Tester_085 | Vinoth_Murali_085 | vinoth.murali3@auspost.com.au | As Sales New Customer APPC Customised Pricing TIER 12 BA Level_21_08_2023 | Australia Post Parcel Contract | Always On        | 50                 | $10,000,000.00     | 10000             | PDA-COMPASS-0000001725 | Z6             | TIER 12 ($10,000,000.00 - $19,999,999.99) | 15000000            | 15000000        | TIER 12 ($10,000,000.00 - $19,999,999.99) | 30            | Adhoc              | Automation_Tester_085 Vinoth_Murali_085 | Charge Account | B Mining | 650           | Fixed Term         | 12            | Add Contract Relationship | Apply rates to All Accounts for this Organisation | ST KILDA ROAD POST SHOP |

  @SalesUser @BALevel @WithProposal
  Scenario Outline: As a Sales user, on-board new APPC customer['<org_name>'] belonging to custom pricing tier['<tier>'], price structure Z6, with default lodgement zones[Capital, Country, Metro] and contract relationship applied at['<relationship>', '<level>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "" and monthly spend ""
      | sub_type | stage   | close_date | is_it_startrack | type         |
      | New      | Propose | 31/12/2023 | No              | New Customer |
    And As Sales user submitted "Pricing Support Request - Australia Post Parcel Contract" from '<opp_name>' with transit cover['<psr_transit_type>', '<psr_transit_amount>'], revenue['<psr_annual_revenue>'], volume['<psr_annual_volume>'] which is then "Completed" by pricing user entering '<quote_id>', '<psr_price_zone>', '<psr_tier>' and '<psr_evaluated_spend>'
      | agreement_duration | agreement_type | catalyst             | sales_justification  | owner                            | recommendation    | end_date   | approved_event     |
      | 3 Years            | Variable APR   | Acquisition/Campaign | PSR for pricing user | Pricing Support Request Domestic | Approving the psr | 31/12/2023 | Customised Pricing |
    And From '<opp_name>' "Sales" user "Checkout Only" '<product>' "with" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Proposal document "R2T APPC Proposal" in "Draft" status is presented to '<sender>' and later "Accepted"
    When User performs credit assessment of the '<opp_name>' using abn['<abn>'] for account type['<account_type>'] by entering '<industry>', '<email>', '<street_number>', '<sender>' and creates sub account ['<sub_account_name>', '<sub_account_name2>'] for '<org_name>' with below details
      | contract_rates | parcel_send | eLMS | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         |
      | Yes            | Yes         | Yes  | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP |
    And Creates "Customised" contract from "Accepted" proposal by confirming details like condition['<contract_condition>'], term["<contract_term>"], key contact['<first_name>' '<last_name>'] and '<relationship>' applied at '<level>' level using '<lodgement_point>'
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
      | org_name                         | abn         | first_name            | last_name         | email                         | opp_name                                                                   | product                        | psr_transit_type | psr_transit_amount | psr_annual_revenue | psr_annual_volume | quote_id               | psr_price_zone | psr_tier                                | psr_evaluated_spend | evaluated_spend | tier                                    | transit_cover | transit_cover_type | sender                                  | account_type   | industry | street_number | sub_account_name | sub_account_name2 | contract_condition | contract_term | relationship                         | level                                                | lodgement_point         |
      | THE CORAL SUPERANNUATION FUND    | 77803567129 | Automation_Tester_084 | Vinoth_Murali_084 | vinoth.murali3@auspost.com.au | As Sales New Customer APPC Customised Pricing TIER 10 Org Level_21_08_2023 | Australia Post Parcel Contract | Adhoc            | 0                  | $2,500,000.00      | 2500              | PDA-COMPASS-0000001724 | Z3             | TIER 10 ($2,500,000.00 - $4,999,999.99) | 3500000             | 3500000         | TIER 10 ($2,500,000.00 - $4,999,999.99) | 00            | Adhoc              | Automation_Tester_084 Vinoth_Murali_084 | Charge Account | B Mining | 18            | SA BA Level Name | SA BA Level Name2 | Open Ended         |               | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |
      | BHP BILLITON SUPERANNUATION FUND | 30187082512 | Automation_Tester_086 | Vinoth_Murali_086 | vinoth.murali3@auspost.com.au | As Sales New Customer APPC Customised Pricing TIER 11 Org Level_21_08_2023 | Australia Post Parcel Contract | Always On        | 80                 | $5,000,000.00      | 5000              | PDA-COMPASS-0000001726 | Z9             | TIER 11 ($5,000,000.00 - $9,999,999.99) | 7500000             | 7500000         | TIER 11 ($5,000,000.00 - $9,999,999.99) | 90            | Always On          | Automation_Tester_086 Vinoth_Murali_086 | Charge Account | B Mining | 111           | SA BA Level Name | SA BA Level Name2 | Fixed Term         | 12            | Add Product specific Billing Account | Apply rates only to new Charge Accounts/Sub Accounts | ST KILDA ROAD POST SHOP |