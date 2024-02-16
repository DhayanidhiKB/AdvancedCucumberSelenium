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
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
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

    @PTEST
    Examples:
      | user_type  | org_name                      | first_name             | last_name          | opp_name                                                                      | category              | price_structure | lodgement_code | transit_cover | lodgement_point | no_of_dsr |
      | Onboarding | BLUE SPRING CTR               | Dhaya                  | Automation Tester  | eParcel_Express_CAT5                                                          | CAT5 - 20000 to 49999 | BANDED {Z6}     | 3000           | 60            | 3000            | 2         |
      | Onboarding | BLUE SPRING CTR               | Dhaya                  | Automation Tester  | eParcel_Express_CAT6                                                          | CAT6 - Above 50000    | BANDED {Z6}     | 3000           | 60            | 3000            | 2         |
      | Sales      | THE CORAL SUPERANNUATION FUND | Automation_Tester_1012 | Vinoth_Murali_1012 | As Sales generate agreement document for EParcel existing customer_31_10_2023 | CAT2 - 2000 to 4999   | BANDED {Z6}     | 3000           | 10            | 3000            | 2         |


  @InternationalDigitalPCMSBundle
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
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
      | user_type  | org_name        | first_name | last_name         | opp_name                          | industry              | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point |
      | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlBundle_DC_No_3Kto5K_12_12     | Construction Services | No                | 3,000 - 4,999         | Direct Integration    | 3000            |
      | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlBundle_DC_Yes_5Kto20K_12_12   | Construction Services | Yes               | 5,000 - 19,999        | Direct Integration    | 3000            |
      | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlBundle_DC_No_20Kto35K_12_12   | Construction Services | No                | 20,000 - 34,999       | Direct Integration    | 3000            |
      | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlBundle_DC_No_LessThan3K_12_12 | Construction Services | Yes               | Less than 3,000       | Direct Integration    | 3000            |


  @InternationalDigitalPCMSBundleInclAirmailLetters
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle incl Airmail Letters belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type              | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | Existing Customer | Determine Go/No Go decision | Testing     |
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
      | user_type | org_name | first_name | last_name | opp_name | industry | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point |
      #| Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlLetters_DC_Yes_LessThan3K_12_12 | Construction Services | Yes               | Less than 3,000       | Direct Integration    | 3000            |
     # | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlLetters_DC_No_3Kto5K_12_12   | Construction Services | No                | 3,000 - 4,999         | Direct Integration    | 3000            |
     # | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlLetters_DC_Yes_5Kto20K_12_12 | Construction Services | Yes               | 5,000 - 19,999        | Direct Integration    | 3000            |
      | Onboarding | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlLetters_DC_No_20Kto35K_12_12 | Construction Services | No                | 20,000 - 34,999       | Direct Integration    | 3000            |


  @InternationalPCMSBundle @RKCode
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle incl Airmail Letters belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "International Digital PCMS Bundle" after validating '<industry>', '<Domestic Customer>', '<Revenue Commitment($)>', '<Integration Platforms>' and adding lodgement point '<lodgement_point>' '<rk_code>' by '<user_type>' user
    When '<user_type>' user "Generate Agreement Document" for "International Digital PCMS Bundle" redirected to contract page
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Creates the contract [0] under '<opp_name>'

    @PTEST
    Examples:
      | user_type | org_name        | first_name | last_name         | opp_name                    | industry              | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point | rk_code  |
      | Sales     | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlPCMSBundleRKCode_Dec7th | Construction Services | No                | 5,000 - 19,999        | eParcel               | 3000            | 20002363 |

  @InternationalAirmailLetters @RKCode
  Scenario Outline: As OnBoarding user, on-board existing customer['<org_name>'] using "Generate Agreement Document" with International Digital PCMS Bundle incl Airmail Letters belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    And "Submitted" a "Customer Onboarding" DSR for "International" to create "New Proposal"
      | description             | support_work_type  |
      | Customer Onboarding DSR | Express Onboarding |
    And '<opp_name>' cart saved with "International Digital PCMS Bundle incl Airmail Letters" after validating '<industry>', '<Domestic Customer>', '<Revenue Commitment($)>', '<Integration Platforms>' and adding lodgement point '<lodgement_point>' '<rk_code>' by '<user_type>' user
    When '<user_type>' user "Generate Agreement Document" for "International Digital PCMS Bundle incl Airmail Letters" redirected to contract page
      | entity                        | type         | product       |
      | Australian Postal Corporation | New Contract | International |
    And Creates the contract [0] under '<opp_name>'

    @PTEST
    Examples:
      | user_type | org_name        | first_name | last_name         | opp_name                                  | industry              | Domestic Customer | Revenue Commitment($) | Integration Platforms | lodgement_point | rk_code  |
      | Sales     | BLUE SPRING CTR | Dhaya      | Automation Tester | IntlPCMSBundleAirmailLettersRKCode_Dec7th | Construction Services | No                | 5,000 - 19,999        | eParcel               | 3000            | 20002362 |



