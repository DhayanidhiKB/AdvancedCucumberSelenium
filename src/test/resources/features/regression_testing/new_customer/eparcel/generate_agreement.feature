@Regression @EParcel @NewCustomers
Feature: Create Billing Account/Rating Plan ID for EParcel New Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @GenerateAgreement
  Rule: Onboarding creates a new organisation with key contact and sales creates an opportunity
  with customer type as "New Customer". If required a new deal support request of type
  "Customer Onboarding" with "New Proposal" work type is submitted. Proposal generation is
  skipped by "Generate Agreement Document" while adding pricing points for "EParcel" product. "New Contract" is
  created by managing relationships at required levels after performing credit assessment.
  Finally "Create Billing Account/Rating Plan" request is "Submitted" to SAP once the contract is activated.

  @EParcelBundle
  Scenario Outline: As OnBoarding user, on-board new customer['<org_name>'] using "Generate Agreement Document" with EParcel Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Onboarding user, created an organisation['<org_name>', '<abn>', "<acn>"] with credit limit "0", billing address['<b_street>', '<b_city>', '<b_state>', '<b_code>', "Australia"], physical address ['<p_street>', '<p_city>', '<p_state>', '<p_code>', "Australia"] and contact['<title>', '<first_name>', '<last_name>', '<email>']
    And Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "eParcel" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "eParcel" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    And '<opp_name>' cart saved with "eParcel Express" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When '<user_type>' user performs credit assessment of the '<opp_name>' using abn['<abn>'] for "Charge Account" by entering '<industry>', '<email>', '<street_number>', '<sender>' and "Generate Agreement Document" for "eParcel" redirected to contract page
      | entity                        | type         | product           |
      | Australian Postal Corporation | New Contract | eParcels Domestic |
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and creates a contract by managing account numbers
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
      | user_type  | org_name                         | abn         | acn | b_street             | b_city     | b_state | b_code | p_street             | p_city     | p_state | p_code | title | first_name             | last_name          | email                         | opp_name                                                                      | category            | price_structure | lodgement_code | transit_cover | lodgement_point | sender                                    | industry | street_number | no_of_dsr |
      | Onboarding | BHP BILLITON SUPERANNUATION FUND | 30187082512 |     | 189 Queen Street     | Melbourne  | VIC     | 3000   | 189 Queen Street     | Melbourne  | VIC     | 3000   | Mr.   | Automation_Tester_1011 | Vinoth_Murali_1011 | vinoth.murali3@auspost.com.au | As Onboarding generate agreement document for EParcel new customer_03_11_2023 | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Automation_Tester_1011 Vinoth_Murali_1011 | B Mining | 189           | 2         |
      | Sales      | THE CORAL SUPERANNUATION FUND    | 77803567129 |     | 20 Queensland Avenue | Broadbeach | QLD     | 4218   | 20 Queensland Avenue | Broadbeach | QLD     | 4218   | Mr.   | Automation_Tester_1012 | Vinoth_Murali_1012 | vinoth.murali3@auspost.com.au | As Sales generate agreement document for EParcel new customer_03_11_2023      | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Automation_Tester_1012 Vinoth_Murali_1012 | B Mining | 20            | 2         |