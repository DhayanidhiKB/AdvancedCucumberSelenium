@SE010 @SP100
Feature: User Stories related to credit assessment

  @CreditAssessment @OutstandingActions
  Rule: Credit Assessment tab is moved to opportunity details page. Validate Credit assessment messages
  with no primary proposal and closed opportunities. Initiate credit assessment from opportunity
  when its stage is Qualify/Negotiate. Initiate or Move credit assessment when there is already an existing
  one. Redirection to opportunity page when credit assessment is cancelled/customer disagreed.

  @Non-Existing
  Scenario Outline: New credit assessment can be started when opportunity['<opp_name>'] is in "Qualify" (or) "Negotiate" stage. Users redirected to opportunity page when credit assessment is cancelled
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    When From '<opp_name>' "Sales" user "Checkout Only" '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    Then "Cancel" credit assessment for "Charge Account" returns to opportunity['<opp_name>'] page in ["Propose","Develop proposal"] stage with below competitor
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    And "Customer Disagreed" credit assessment for "Charge Account + Sub Account" returns to opportunity['<opp_name>'] page in ["Negotiate", "Prepare contract"] stage with task["Proposal:", '<first_name>' '<last_name>']
    And "Sales" user verifies message under credit assessment when opportunity['<opp_name>'] is closed
      | stage_name  | next_steps        | reason     | comment |
      | Closed Lost | No further action | Capability | Testing |

    @PTEST
    Examples:
      | org_name                     | abn         | acn | b_street       | b_city  | b_state | b_code | p_street       | p_city  | p_state | p_code | title | first_name            | last_name         | email                         | opp_name                                                  | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type |
      | NATIONAL 1 AUSTRALIA LIMITED | 78083415525 |     | 53 Belmont Ave | Belmont | WA      | 6104   | 53 Belmont Ave | Belmont | WA      | 6104   | Mr.   | Automation_Tester_181 | Vinoth_Murali_181 | vinoth.murali3@auspost.com.au | SE010 User Story Credit Assessment not present_06_09_2023 | Australia Post Parcel Contract | 30000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              |

  @Existing
  Scenario Outline: User is able to "Create New Credit Assessment" or "Move Credit Assessment" from '<opp_name>' with an existing credit assessment
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for '<product>' to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    When From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When From '<opp_name>' "Onboarding" user presents "R2T APPC Proposal" document[1] initially in "Draft" status to '<sender>' and later "Accepted"
    And "Onboarding" user tries to create contract from an "Accepted" Proposal lands in '<opp_name>' page for to complete credit assessment using abn['<abn>'] for "Charge Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and change stage from "Propose" to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>']
    And From '<opp_name>' "Onboarding" user "Checkout Only" '<product>' "without" psr after validating '<evaluated_spend>', '<tier>', '<transit_cover>', '<transit_cover_type>' and below details
      | price_structure | lodgement_zone | lodgement_zone1 | lodgement_zone2 | cubic_status | cubic_factor |
      | Z6              | Capital        | Metro           | Country         | Y            | 1.00         |
    Then User is able to "Create New Credit Assessment" or "Move Credit Assessment" from '<opp_name>'

    @PTEST
    Examples:
      | org_name                | abn         | acn       | b_street         | b_city    | b_state | b_code | p_street         | p_city    | p_state | p_code | title | first_name            | last_name         | email                         | opp_name                                                       | product                        | evaluated_spend | tier                             | transit_cover | transit_cover_type | sender                                  | industry | street_number |
      | NATIONAL WEST PTY. LTD. | 54106602466 | 106602466 | 664 Anzac Parade | Kingsford | NSW     | 2032   | 664 Anzac Parade | Kingsford | NSW     | 2032   | Mr.   | Automation_Tester_182 | Vinoth_Murali_182 | vinoth.murali3@auspost.com.au | SE010 User Story Credit Assessment already existing_06_09_2023 | Australia Post Parcel Contract | 30000           | TIER 4 ($20,000.00 - $49,999.99) | 00            | Adhoc              | Automation_Tester_182 Vinoth_Murali_182 | B Mining | 43            |