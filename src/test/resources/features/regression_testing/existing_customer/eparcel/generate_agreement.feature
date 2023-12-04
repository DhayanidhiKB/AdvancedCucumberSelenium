@Regression @EParcel @ExistingCustomers
Feature: "Create contract with UCT change" request for EParcel Existing Customers with Standard Rate Card (Tier 4 to Tier 8) Pricing Points

  @GenerateAgreement
  Rule: Sales user creates an opportunity with customer type as "Existing Customer". If required
  a new deal support request of type "Customer Onboarding" with "New Proposal" work type is submitted.
  Proposal generation is skipped by "Generate Agreement Document" while adding pricing points for "EParcel" product.
  "New Contract" is created by managing relationships at required levels once the proposal is accepted.
  Finally "Create Rating Plan" request is "Submitted" to SAP once the contract is activated.

  @EParcelBundle
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with EParcel Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type       | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "eParcel" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "eParcel" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    And '<opp_name>' cart saved with "eParcel Express" after validating '<category>', '<price_structure>', '<lodgement_code>', '<transit_cover>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    When '<user_type>' user "Generate Agreement Document" for "eParcel" redirected to contract page
      | entity                        | type         | product           |
      | Australian Postal Corporation | New Contract | eParcels Domestic |
    And Creates the contract [0] under '<opp_name>'
    #And "Propose" '<opp_name>' that contains["Accepted without proposal document", '<sender>'] is changed to ["Negotiate", "Prepare contract"] by adding task["Proposal:", '<sender>'] and creates a contract by managing account numbers
    #And Onboarding user, manually sign the contract[0] under '<opp_name>' and confirming its status as "SFDC DSR Created"
    #  | sign_step     | sign_status  | approval_status | active_step | active_status |
     # | In Signatures                 | Fully Signed | Approved          | In Effect | Activated |
    #ONboarding DSR: automatically generated for create SAP account/request
    #Then Make sure that there are <no_of_dsr> "Customer Onboarding" dsr under '<opp_name>' in "Submitted" status "without" psr
    #And Validate the response when "Create Rating Plan" request is "Submitted" for "eParcel" to SAP and finally '<opp_name>' is "Closed Won" with details["Commence implementation", "Capability", "Its a win"]
     # | status  | rating_status | rating_description                               |
     # | Success                       | Completed    | Rating plan(s) have been activated successfully. |

    @PTEST
    Examples:
      | user_type  | org_name        | first_name | last_name         | opp_name             | category           | price_structure | lodgement_code | transit_cover | lodgement_point | sender                          | no_of_dsr |
      #| Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | eParcel_Express_CAT5 | CAT5 - 20000 to 49999 | BANDED {Z6}     | 3000           | 60            | 3000            | Dhayanidhi KB Automation Tester | 2         |
      | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | eParcel_Express_CAT6 | CAT6 - Above 50000 | BANDED {Z6}     | 3000           | 60            | 3000            | Dhayanidhi KB Automation Tester | 2         |
     # | Sales      | THE CORAL SUPERANNUATION FUND    | Automation_Tester_1012 | Vinoth_Murali_1012 | As Sales generate agreement document for EParcel existing customer_31_10_2023      | CAT2 - 2000 to 4999 | BANDED {Z6}     | 3000           | 10            | 3000            | Automation_Tester_1012 Vinoth_Murali_1012 | 2    |


  @InternationalDigitalPCMSBundle
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "International Digital PCMS Bundle" after validating '<industry>', '<Domestic Customer>', '<Revenue Commitment($)>', '<Integration Platforms>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    When '<user_type>' user "Generate Agreement Document" for "International Digital PCMS Bundle" redirected to contract page
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Creates the contract [0] under '<opp_name>'


    @PTEST
    Examples:
      | user_type  | org_name                     | first_name | last_name  | opp_name                             | industry              | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point |
      | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | InternationalBundle_RCThirdScenario  | Construction Services | Yes               | 5,000 - 19,999        | Direct Integration    | 3000            |
      | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | InternationalBundle_RCFourthScenario | Construction Services | Yes               | 20,000 - 34,999       | Direct Integration    | 3000            |
      | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | InternationalBundle_RCFifthScenario  | Construction Services | Yes               | 35,000 - 50,000       | Direct Integration    | 3000            |
      | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | InternationalBundle_RCSixthScenario  | Construction Services | Yes               | Above 50,000          | Direct Integration    | 3000            |


  @InternationalDigitalPCMSBundleInclAirmailLetters
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle incl Airmail Letters belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "International Digital PCMS Bundle incl Airmail Letters" after validating '<industry>', '<Domestic Customer>', '<Revenue Commitment($)>', '<Integration Platforms>' and adding lodgement point '<lodgement_point>' by '<user_type>' user
    When '<user_type>' user "Generate Agreement Document" for "International Digital PCMS Bundle incl Airmail Letters" redirected to contract page
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Creates the contract [0] under '<opp_name>'

    @PTEST
    Examples:
      | user_type  | org_name                     | first_name | last_name  | opp_name                    | industry              | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point |
    #  | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | IntlLetters_RCFirstScenario  | Construction Services | Yes               | Less than 3,000       | Direct Integration    | 3000            |
     # | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | IntlLetters_RCSecondScenario | Construction Services | Yes               | 3,000 - 4,999         | Direct Integration    | 3000            |
      | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | IntlLetters_RCThirdScenario | Construction Services | Yes               | 5,000 - 19,999        | Direct Integration    | 3000            |
     # | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | IntlLetters_RCFourthScenario | Construction Services | Yes               | 20,000 - 34,999       | Direct Integration    | 3000            |
    #  | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | IntlLetters_RCFifthScenario | Construction Services | Yes               | 35,000 - 50,000       | Direct Integration    | 3000            |
     # | Onboarding | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | IntlLetters_RCSixthScenario | Construction Services | Yes               | Above 50,000          | Direct Integration    | 3000            |

  @InternationalDigitalBundleInclAirmailLettersRKCode
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle incl Airmail Letters belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Generate Agreement Document" with below details after adding "International Digital PCMS Bundle incl Airmail Letters" with rk code['<rk_code>'] to the opportunity["<opp_name>"] by validating its attributes "Construction Services", "Yes", "Less than 3,000", "eParcel" and lodgement point "3000"
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Creates the contract [0] under '<opp_name>'

      @PTEST
      Examples:
        | user_type | org_name                     | first_name | last_name  | opp_name                   | industry              | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point |
        | Sales     | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | InternationalAirmail_28_11 | Construction Services | No                | 5,000 - 19,999        | eParcel               | 3000            |

  @InternationalPCMSBundle @RKCode
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle incl Airmail Letters belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Generate Agreement Document" with below details after adding "International Digital PCMS Bundle" with rk code['<rk_code>'] to the opportunity["<opp_name>"] by validating its attributes '<industry>', '<Domestic Customer>', '<Revenue Commitment($)>', '<Integration Platforms>' and lodgement point "3000"
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Creates the contract [0] under '<opp_name>'

    @PTEST
    Examples:
      | user_type | org_name                     | first_name | last_name  | opp_name                   | industry              | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point |
      | Sales     | PROS ATTY_Dhaya_AfterRefresh | Dhaya      | Automation | InternationalAirmail_28_11 | Construction Services | No                | 5,000 - 19,999        | eParcel               | 3000            |



