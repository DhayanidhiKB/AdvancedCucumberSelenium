Feature: Negative Scenario

  @EParcelBundle
  Scenario Outline: As OnBoarding user, on-board new customer['<org_name>'] using "Generate Agreement Document" with EParcel Bundle belonging to category['<category>'] and price structure['<price_structure>']
    Given Sales user, created an opportunity['<opp_name>'] for the above organisation['<org_name>'] with key contact['<first_name>' '<last_name>'], post code "", monthly spend "" and changed its stage to "Qualify"
      | sub_type | stage    | identify_steps             | close_date | is_it_startrack | total_value | type         | qualify_steps               | description |
      | New      | Identify | Update opportunity details | 31/12/2023 | No              | 500         | New Customer | Determine Go/No Go decision | Testing     |
    When 'Sales' user saves the cart with "eParcel" after validating "CAT2 - 2000 to 4999", "BANDED {Z6}", "3000", "10" and adding lodgement point "3000"
    Then Modify quantity "1", revenue start date "31/01/2024" and revenue end date "30/01/2025"

    @PTEST
    Examples:
      | org_name                              | first_name            | last_name         | opp_name                                                                      |
      | THE NATION FAMILY SUPERANNUATION FUND | Automation_Tester_513 | Vinoth_Murali_513 | As Onboarding checkout only for EParcel International new customer_14_10_2023 |