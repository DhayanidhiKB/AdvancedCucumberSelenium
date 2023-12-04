@Regression @StarTrack @ExistingCustomers
Feature: "Create Rating Plan" for StarTrack Existing Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @CreateProposal
  Rule: Sales user creates an opportunity with customer type as "Existing Customer". If required
  a new deal support request of type "Customer Onboarding" with "New Proposal" work type is submitted.
  Proposal is created once pricing points are added for "StarTrack" product and checked out successfully.
  "New Contract" is created by managing relationships at required levels once the proposal is accepted.
  Finally "Create Rating Plan" request is "Submitted" to SAP once the contract is activated.

  @STE
  Scenario Outline: As a Onboarding user, on-board existing StarTrack customer['<org_name>', '<abn>'] using "CheckOut Only" belonging to tier['<tier>'] with service type['<service_type>'] and monthly spend['<monthly_spend>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code '<post_code>', monthly spend '<monthly_spend>' and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | Yes             | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "StarTrack" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And From '<opp_name>' '<user_type>' user '<check_out_option>' '<product>' after validating its attributes '<service_type>', '<primary_suburb>', '<suburb>' and '<cart_tier>'
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' '<user_type>' user presents "<org_name>__StarTrack Proposal" document[1] initially in '<proposal_status>' status to '<sender>' and later "Accepted"
    And '<user_type>' user changes '<opp_name>' stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And Sales user creates csq related to '<opp_name>' with freight offering of type['<freight_type>'] and pickup location
      | customer_brief         | address_line1 | address_line2 | suburb    | state | postcode |
      | For StarTrack Customer | 111           | BOURKE ST     | MELBOURNE | VIC   | 3000     |
    And '<user_type>' confirms details like key contact['<first_name>' '<last_name>'] and creates contract documents from "Accepted" proposal
      | entity                    | type         | product   |
      | StarTrack Express Pty Ltd | New Contract | StarTrack |
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Rating Plan" request is "Submitted" for '<product>' to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | user_type  | org_name                            | first_name             | last_name          | opp_name                                                                                     | post_code      | monthly_spend | product            | service_type | primary_suburb | suburb         | cart_tier | check_out_option           | proposal_status | sender                                    | freight_type |
      | Onboarding | ELAND SUPERANNUATION FUND           | Automation_Tester_1014 | Vinoth_Murali_1014 | As Onboarding checkout only StarTrack Premium existing customer_31_10_2023                   | 3000-MELBOURNE | 11000         | Premium - STE      | PRM          | 3000-MELBOURNE | 3000-MELBOURNE | Tier 4    | Checkout Only              | Draft           | Automation_Tester_1014 Vinoth_Murali_1014 | Premium      |
      | Onboarding | FONDA SUPERANNUATION FUND           | Automation_Tester_1015 | Vinoth_Murali_1015 | As Onboarding generate proposal document StarTrack Road Express existing customer_31_10_2023 | 3000-MELBOURNE | 11000         | Road Express - STE | EXP          | 3000-MELBOURNE | 3000-MELBOURNE | Tier 4    | Generate Proposal Document | Generated       | Automation_Tester_1015 Vinoth_Murali_1015 | Premium      |
      | Sales      | SANTOS (KORINCI-BARU NO. 2) PTY LTD | Automation_Tester_1016 | Vinoth_Murali_1016 | As Sales checkout only StarTrack Premium existing customer_31_10_2023                        | 3000-MELBOURNE | 11000         | Premium - STE      | PRM          | 3000-MELBOURNE | 3000-MELBOURNE | Tier 4    | Checkout Only              | Draft           | Automation_Tester_1016 Vinoth_Murali_1016 | Premium      |
      | Sales      | NATIONAL AUSTRALIA LIMITED          | Automation_Tester_1017 | Vinoth_Murali_1017 | As Sales generate proposal document StarTrack Road Express existing customer_31_10_2023      | 3000-MELBOURNE | 11000         | Road Express - STE | EXP          | 3000-MELBOURNE | 3000-MELBOURNE | Tier 4    | Generate Proposal Document | Generated       | Automation_Tester_1017 Vinoth_Murali_1017 | Premium      |

  @OfflineRates
  Scenario Outline: As a Onboarding user, on-board existing StarTrack customer['<org_name>', '<abn>'] using "CheckOut Only" belonging to tier['<tier>'] with service type['<service_type>'] and monthly spend['<monthly_spend>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code '<post_code>', monthly spend '<monthly_spend>' and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | Yes             | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    When "Submitted" a "Customer Onboarding" DSR for "StarTrack" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    Then From '<opp_name>' '<user_type>' user "Use Offline Rates and Checkout" '<product>' due to ["Technical Issue", "Testing"] after validating '<service_type>', '<primary_suburb>', '<suburb>' and '<cart_tier>'

    @PTEST
    Examples:
      | user_type  | org_name                  | first_name             | last_name          | opp_name                                                                       | post_code      | monthly_spend | product       | service_type | primary_suburb | suburb         | cart_tier |
      | Onboarding | ELAND SUPERANNUATION FUND | Automation_Tester_1018 | Vinoth_Murali_1018 | As Onboarding Offline Rates for StarTrack Premium existing customer_31_10_2023 | 3000-MELBOURNE | 11000         | Premium - STE | PRM          | 3000-MELBOURNE | 3000-MELBOURNE | Tier 4    |