@Regression @EParcel @ExistingCustomers
Feature: "Create Rating Plan" request is "Submitted" to SAP for EParcel Existing Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @GenerateAgreementManually
  Rule: Sales user creates an opportunity with customer type as "Existing Customer". If required
  a new deal support request of type "Customer Onboarding" with "New Proposal" work type is submitted.
  Proposal generation is skipped by "Generate Manual Agreement Document" while adding pricing points
  for "EParcel" product. "New Contract" is created by managing relationships at required levels once
  the proposal is accepted.Finally "Create Rating Plan" request is "Submitted" to SAP once the
  contract is activated.

  @EParcelBundle
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Manual Agreement Document" with EParcel Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "eParcel" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "eParcel" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    And '<opp_name>' cart saved with "eParcel Express" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    And Sales user "Propose" the '<opp_name>' for "Develop proposal" after adding competitors and status
      | type       | product | name            | status                    | advantage |
      | Competitor | Courier | Couriers Please | Even with the Competition | Testing   |
    When '<user_type>' user "Generate Manual Agreement Document" for "eParcel" redirected to contract page
      | entity                        | type         | product           |
      | Australian Postal Corporation | New Contract | eParcels Domestic |
    And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and merge '<file_name>' with contract after managing account numbers
    And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
      | sign_step     | sign_status  | approval_status | active_step | active_status |
      | In Signatures | Fully Signed | Approved        | In Effect   | Activated     |
    #ONboarding DSR: automatically generated for create SAP account/request
    Then Make sure that there are 2 "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    And Validate the response when "Create Rating Plan" request is "Submitted" for "eParcel" to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
      | status  | rating_status | rating_description                               |
      | Success | Completed     | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | user_type  | org_name                     |  | first_name             | last_name          | opp_name                                                                                  | category            | price_structure | lodgement_code | transit_cover | lodgement_point | sender                                    | file_name                  |
      | Onboarding | NATION AUSTRALIA PTY LIMITED |  | Automation_Tester_1013 | Vinoth_Murali_1013 | As Onboarding generate manual agreement document for EParcel existing customer_31_10_2023 | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Automation_Tester_1013 Vinoth_Murali_1013 | PDA-COMPASS-0000000084.pdf |