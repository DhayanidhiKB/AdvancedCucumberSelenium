@Regression @StarTrack @NewCustomers
Feature: Create Billing Account/Rating Plan ID for EParcel New Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @GenerateAgreement
  Rule: Onboarding creates a new organisation with key contact and sales creates an opportunity
  with customer type as "New Customer". If required a new deal support request of type
  "Customer Onboarding" with "New Proposal" work type is submitted. Proposal generation is
  skipped by adding pricing points for "StarTrack" product and check out using "Generate Agreement Document"
  option. "New Contract" is created by managing relationships at required levels after performing
  credit assessment. Finally "Create Billing Account/Rating Plan" request is "Submitted" to SAP
  once the customer signs the contract.

  @STE
  Scenario Outline: As a Onboarding user, on-board new StarTrack customer['<org_name>', '<abn>'] using "Generate Agreement Document" belonging to tier['<tier>'] with service type['<service_type>'] and monthly spend['<monthly_spend>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "10000", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code '<post_code>', monthly spend '<monthly_spend>' and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | Yes             | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "StarTrack" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with '<product>' after validating '<service_type>', '<primary_suburb>', '<suburb>' and '<cart_tier>' by '<user_type>' user
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When '<user_type>' user performs credit assessment of the '<opp_name>' using abn['<abn>'] for "Charge Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and "Generate Agreement Document" for '<product>' redirected to contract page
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | New Contract | StarTrack |
    And '<opp_name>' contains "Accepted without proposal document" with contact '<sender>' and creates a contract documents
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
      | user_type  | org_name                       | abn         | acn | b_street         | b_city           | b_state | b_code | p_street           | p_city           | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                                     | post_code      | monthly_spend | sender                                    | product            | service_type | primary_suburb | suburb         | cart_tier | industry | street_number | no_of_dsr |
      | Onboarding | MCKENZIE SUPERANNUATION FUND   | 37158288490 |     | 3 Kenwyn Close   | St Ives          | NSW     | 2075   | 3 Kenwyn Close     | St Ives          | NSW     | 2075   | Mr.   | Automation_Tester_1019 | Vinoth_Murali_1019 | vinoth.murali3@auspost.com.au | As Onboarding generate agreement document for StarTrack Road Express new customer_31_10_2023 | 3000-MELBOURNE | 11000         | Automation_Tester_1019 Vinoth_Murali_1019 | Premium - STE      | PRM          | 3000-MELBOURNE | 3000-MELBOURNE | Tier 4    | B Mining | 3             | 2         |
      | Sales      | THE BH & P SUPERANNUATION FUND | 21569439143 |     | Penshurst Street | North Willoughby | NSW     | 2068   | 3 Penshurst Street | North Willoughby | NSW     | 2068   | Mr.   | Automation_Tester_1020 | Vinoth_Murali_1020 | vinoth.murali3@auspost.com.au | As Sales generate agreement document for StarTrack Premium new customer_31_10_2023           | 3000-MELBOURNE | 11000         | Automation_Tester_1020 Vinoth_Murali_1020 | Road Express - STE | EXP          | 3000-MELBOURNE | 3000-MELBOURNE | Tier 4    | B Mining | 3             | 2         |