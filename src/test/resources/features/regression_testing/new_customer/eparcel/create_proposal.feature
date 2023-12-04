@Regression @EParcel @NewCustomers
Feature: Create Billing Account/Rating Plan ID for EParcel New Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @CreateProposal
  Rule: Onboarding creates a new organisation with key contact and sales creates an opportunity
  with customer type as "New Customer". If required a new deal support request of type
  "Customer Onboarding" with "New Proposal" work type is submitted. Proposal is created once
  pricing points are added for "EParcel" product and checked out successfully. "New Contract" is
  created by managing relationships at required levels after performing credit assessment once
  the proposal is accepted. Finally "Create Billing Account/Rating Plan" request is "Submitted"
  to SAP once the contract is activated.

  @International
  Scenario Outline: As OnBoarding user, on-board new customer['<org_name>'] using "CheckOut Only" with EParcel International belonging to revenue_commitment['<revenue_commitment>'] and integration_platforms['<integration_platforms>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user '<check_out_option>' '<product>' after validating its attributes '<product_industry>', '<domestic_customer>', '<revenue_commitment>', '<integration_platforms>' and adding lodgement point '<lodgement_point>'
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "<org_name>__Proposal" document[0] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    And '<user_type>' user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for "Charge Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Creates contract from "Accepted" proposal by confirming details like key contact['<first_name>' '<last_name>'] and managing account numbers
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | user_type  | org_name                                           | abn         | acn       | b_street            | b_city       | b_state | b_code | p_street            | p_city       | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                      | product                                                | product_industry      | domestic_customer | revenue_commitment | integration_platforms | lodgement_point | check_out_option           | proposal_status | sender                                    | industry | street_number | no_of_dsr |
      | Onboarding | THE NATION FAMILY SUPERANNUATION FUND              | 22587953901 |           | 11 Victory Road     | Airport West | VIC     | 3042   | 11 Victory Road     | Airport West | VIC     | 3042   | Mr.   | Automation_Tester_1001 | Vinoth_Murali_1001 | vinoth.murali3@auspost.com.au | As Onboarding checkout only EParcel International new customer_03_11_2023     | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Checkout Only              | Draft           | Automation_Tester_1001 Vinoth_Murali_1001 | B Mining | 11            | 2         |
      | Onboarding | NATIONAL AUSTRALIA LIMITED                         | 19004799106 |           | 2 Lonsdale Street   | Melbourne    | VIC     | 3000   | 2 Lonsdale Street   | Melbourne    | VIC     | 3000   | Mr.   | Automation_Tester_1002 | Vinoth_Murali_1002 | vinoth.murali3@auspost.com.au | As Onboarding generate proposal EParcel International new customer_06_11_2023 | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1002 Vinoth_Murali_1002 | B Mining | 2             | 2         |
      | Sales      | Santos (NGA) Pty Ltd                               | 25091824798 | 091824798 | 185 Morphett Street | Adelaide     | SA      | 5000   | 185 Morphett Street | Adelaide     | SA      | 5000   | Mr.   | Automation_Tester_1003 | Vinoth_Murali_1003 | vinoth.murali3@auspost.com.au | As Sales checkout only EParcel International new customer_03_11_2023          | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Checkout Only              | Draft           | Automation_Tester_1003 Vinoth_Murali_1003 | B Mining | 185           | 2         |
      | Sales      | ROTHSCHILD AUSTRALIA AIRCRAFT LEASING PTY. LIMITED | 97006346961 |           | 65 Martin Place     | SYDNEY       | NSW     | 2000   | 65 Martin Place     | SYDNEY       | NSW     | 2000   | Mr.   | Automation_Tester_1004 | Vinoth_Murali_1004 | vinoth.murali3@auspost.com.au | As Sales generate proposal EParcel International new customer_03_11_2023      | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1004 Vinoth_Murali_1004 | B Mining | 65            | 2         |

  @EParcelBundle
  Scenario Outline: As OnBoarding user, on-board new customer['<org_name>'] using "CheckOut Only" with EParcel Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "eParcel" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "eParcel" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    And From '<opp_name>' '<user_type>' user '<check_out_option>' "eParcel Express" after validating its attributes '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>'
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "<org_name>__Proposal" document[0] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    And '<user_type>' user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for "Charge Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Creates contract from "Accepted" proposal by confirming details like key contact['<first_name>' '<last_name>'] and managing account numbers
      | entity                        | type         | product           |
      | Australian Postal Corporation | New Contract | eParcels Domestic |
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Billing Account/Rating Plan" request is "Submitted" for "eParcel" to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @STEST
    Examples:
      | user_type  | org_name                            | abn         | acn       | b_street           | b_city   | b_state | b_code | p_street           | p_city   | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                               | category            | price_structure | lodgement_code | transit_cover | lodgement_point | check_out_option           | proposal_status | sender                                    | industry | street_number | no_of_dsr |
      | Onboarding | ROTHSCHILD NOMINEES PTY LTD         | 98003066822 | 003066822 | 1 Bligh Street     | Sydney   | NSW     | 2000   | 1 Bligh Street     | Sydney   | NSW     | 2000   | Mr.   | Automation_Tester_1005 | Vinoth_Murali_1005 | vinoth.murali1@auspost.com.au | As Onboarding checkout only EParcel Bundle new customer_03_11_2023     | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Checkout Only              | Draft           | Automation_Tester_1005 Vinoth_Murali_1005 | B Mining | 1             | 2         |
      | Onboarding | FUNDI PTY LTD                       | 45054357890 | 054357890 | 19 Aberdeen Street | Perth    | WA      | 6001   | 19 Aberdeen Street | Perth    | WA      | 6001   | Mr.   | Automation_Tester_1006 | Vinoth_Murali_1006 | vinoth.murali1@auspost.com.au | As Onboarding generate proposal EParcel Bundle new customer_03_11_2023 | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1006 Vinoth_Murali_1006 | B Mining | 19            | 2         |
      | Sales      | NM ROTHSCHILD AUST HOLDINGS PTY LTD | 26008458339 | 008458339 | 207 Kent Street    | Sydney   | NSW     | 2000   | 207 Kent Street    | Sydney   | NSW     | 2000   | Mr.   | Automation_Tester_1007 | Vinoth_Murali_1007 | vinoth.murali1@auspost.com.au | As Sales checkout only EParcel Bundle new customer_03_11_2023          | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Checkout Only              | Draft           | Automation_Tester_1007 Vinoth_Murali_1007 | B Mining | 207           | 2         |
      | Sales      | KING SUPERANNUATION FUND            | 30028163730 |           | 83 KING Street     | RANDWICK | NSW     | 2031   | 83 KING Street     | RANDWICK | NSW     | 2031   | Mr.   | Automation_Tester_1008 | Vinoth_Murali_1008 | vinoth.murali1@auspost.com.au | As Sales generate proposal EParcel Bundle new customer_03_11_2023      | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Generate Proposal Document | Generated       | Automation_Tester_1008 Vinoth_Murali_1008 | B Mining | 83            | 2         |

  @OfflineRates
  Scenario Outline: As OnBoarding user, on-board new customer['<org_name>'] using "CheckOut Only" with EParcel International belonging to revenue_commitment['<revenue_commitment>'] and integration_platforms['<integration_platforms>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user "Use Offline Rates and Checkout" '<product>' due to ["Technical Issue", "Testing"] after validating its attributes '<product_industry>', '<domestic_customer>', '<revenue_commitment>', '<integration_platforms>' and adding lodgement point '<lodgement_point>'

    @STEST
    Examples:
      | user_type  | org_name         | abn         | acn       | b_street     | b_city    | b_state | b_code | p_street     | p_city    | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                     | product                                                | product_industry      | domestic_customer | revenue_commitment | integration_platforms | lodgement_point |
      | Onboarding | NATIONAL PTY LTD | 27104830006 | 104830006 | 9 Bay Street | Southport | QLD     | 4215   | 9 Bay Street | Southport | QLD     | 4215   | Mr.   | Automation_Tester_1010 | Vinoth_Murali_1010 | vinoth.murali3@auspost.com.au | As Onboarding offline checkout EParcel International new customer_03_11_2023 | International Digital PCMS Bundle incl Airmail Letters | Construction Services | Yes               | Less than 3,000    | eParcel               | 3000            |