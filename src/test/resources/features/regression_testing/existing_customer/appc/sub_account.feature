@Progression
Feature: APPC to be applicable to Sub-Accounts - Contract Relationship - Organisation (or) Billing Account

  @Sub-Account_Requests
  Rule: Sales User should be able to indicate whether a new sub-account request is for an APPC product, so that the new sub-account can be included in an existing APPC Contract.
  User should be able to select APPC along with other products too if add contract rates is Yes. Sub-account creation request sent to
  camunda along with product array and user notified with bell notification once the sub-account is created successfully in SAP. If Parcel send login is "No",
  system should NOT create the Onboarding DSR if only APPC is selected and should create Onboarding DSR if non APPC products are selected along with APPC.
  If Parcel send login is "Yes" Onboarding DSR must be created for users to create parcel send login

  @APPC
  Scenario Outline: Submit sub-account request for existing APPC customer ['<org_name>', '<abn>'] with '<contract_relationship>' level contract populating "APPC" product, contract rates '<add_contract_rates>', parcel send '<parcel_send_login>', business address and lodgement point
    Given Got the number of DSRs from an existing customer ['<org_name>', '<abn>']
    And From an existing customer ['<org_name>', '<abn>'], active billing account is identified
    When User submits sub-account request for an existing '<contract_relationship>' level APPC contract by populating name['<sub_account_name>', '<sub_account_name2>'], contract rates "Yes", parcel send ['<parcel_send_login>', "<contact>"], "APPC" product , "No" eLMS, address['<address_line_1>', '<address_line_2>', '<sub_urb>', '<state>', '<post_code>'] and lodgement point['<lodgement_point>']
    Then Verify whether sub-account request '<sub_account_name>' has been created for customer ['<org_name>', '<abn>'] with contract rates "Yes", parcel send login '<parcel_send_login>', product "APPC" and status is "Success"
    And Verify whether <no_of_dsr> "Customer Onboarding" DSR with status as "Submitted" is generated for "APPC" product under customer ['<org_name>', '<abn>']

    @PTEST
    Examples:
      | org_name                    | abn         | contract_relationship | sub_account_name     | sub_account_name2    | parcel_send_login | contact                                 | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         | no_of_dsr |
      | CAPITAL SUPERANNUATION FUND | 12046157793 | Organisation          | SA Org Level Name 21 | SA Org Level Name 22 | No                |                                         | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 0         |
      | PAUL SUPERANNUATION FUND    | 20681694970 | Billing Account       | SA BA Level Name 23  | SA BA Level Name 24  | No                |                                         | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 0         |
      | CAPITAL SUPERANNUATION FUND | 12046157793 | Organisation          | SA Org Level Name 25 | SA Org Level Name 26 | Yes               | Automation_Tester_001 Vinoth_Murali_001 | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 1         |
      | PAUL SUPERANNUATION FUND    | 20681694970 | Billing Account       | SA BA Level Name 27  | SA BA Level Name 28  | Yes               | Vinoth_003 Tester_003                   | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 1         |

  @APPC_And_EParcel
  Scenario Outline: Submit sub-account request for existing APPC customer ['<org_name>', '<abn>'] with '<contract_relationship>' level contract populating "APPC" and "eParcel" products, contract rates '<add_contract_rates>', parcel send '<parcel_send_login>', business address and lodgement point
    Given Got the number of DSRs from an existing customer ['<org_name>', '<abn>']
    And From an existing customer ['<org_name>', '<abn>'], active billing account is identified
    When User submits sub-account request for an existing '<contract_relationship>' level APPC contract by populating name['<sub_account_name>', '<sub_account_name2>'], contract rates "Yes" , parcel send ['<parcel_send_login>', "<contact>"], products["APPC" and "eParcel"], "No" eLMS, address['<address_line_1>', '<address_line_2>', '<sub_urb>', '<state>', '<post_code>'] and lodgement point['<lodgement_point>']
    Then Verify whether sub-account request '<sub_account_name>' has been created for customer ['<org_name>', '<abn>'] with contract rates "Yes", parcel send login '<parcel_send_login>', products["APPC" and "eParcel"] and status is "Success"
    And Verify whether <no_of_dsr> "Customer Onboarding" DSR with status as "Submitted" is generated for '<dsr_product>' product under customer ['<org_name>', '<abn>']

    @PTEST
    Examples:
      | org_name                    | abn         | contract_relationship | sub_account_name     | sub_account_name2    | parcel_send_login | contact                                 | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         | no_of_dsr | dsr_product  |
      | CAPITAL SUPERANNUATION FUND | 12046157793 | Organisation          | SA Org Level Name 21 | SA Org Level Name 22 | No                |                                         | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 0         | eParcel      |
      | PAUL SUPERANNUATION FUND    | 20681694970 | Billing Account       | SA BA Level Name 23  | SA BA Level Name 24  | No                |                                         | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 0         | eParcel      |
      | CAPITAL SUPERANNUATION FUND | 12046157793 | Organisation          | SA Org Level Name 25 | SA Org Level Name 26 | Yes               | Automation_Tester_001 Vinoth_Murali_001 | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 1         | APPC;eParcel |
      | PAUL SUPERANNUATION FUND    | 20681694970 | Billing Account       | SA BA Level Name 27  | SA BA Level Name 28  | Yes               | Vinoth_003 Tester_003                   | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 1         | APPC;eParcel |

  @Multiple_SAR_APPC
  Scenario Outline: Submit multiple sub-account requests for existing APPC customer ['<org_name>', '<abn>'] with '<contract_relationship>' level contract populating "APPC" product, contract rates '<add_contract_rates>', parcel send "Yes", business address and lodgement point
    Given Got the number of DSRs from an existing customer ['<org_name>', '<abn>']
    And From an existing customer ['<org_name>', '<abn>'], active billing account is identified
    When User submits sub-account request for an existing '<contract_relationship>' level APPC contract by populating name['<sub_account_name1>', '<sub_account_name2>'], contract rates "Yes", parcel send '<parcel_send_login>' with '<contact>', "APPC" product , "No" eLMS, address['<address_line_1>', '<address_line_2>', '<sub_urb>', '<state>', '<post_code>'] and lodgement point['<lodgement_point>']
    And User submits sub-account request for an existing '<contract_relationship>' level APPC contract by populating name['<sub_account_name3>', '<sub_account_name4>'], contract rates "Yes", parcel send '<parcel_send_login>' with '<contact>', "APPC" product , "No" eLMS, address['<address_line_1>', '<address_line_2>', '<sub_urb>', '<state>', '<post_code>'] and lodgement point['<lodgement_point>']
    Then Verify whether sub-account request '<sub_account_name1>' has been created for customer ['<org_name>', '<abn>'] with contract rates "Yes", parcel send login '<parcel_send_login>', product "APPC" and status is "Success"
    And Verify whether sub-account request '<sub_account_name3>' has been created for customer ['<org_name>', '<abn>'] with contract rates "Yes", parcel send login '<parcel_send_login>', product "APPC" and status is "Success"
    And Verify whether <no_of_dsr> "Customer Onboarding" DSR with status as "Submitted" is generated for '<dsr_product>' product under customer ['<org_name>', '<abn>']

    @PTEST
    Examples:
      | org_name                       | abn         | contract_relationship | sub_account_name1    | sub_account_name2    | sub_account_name3    | sub_account_name4    | parcel_send_login | contact               | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         | no_of_dsr | dsr_product |
      | THE CARTER SUPERANNUATION FUND | 14506358601 | Organisation          | SA Org Level Name 31 | SA Org Level Name 32 | SA Org Level Name 33 | SA Org Level Name 34 | Yes               | Vinoth_106 Murali_106 | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 2         | APPC        |
      | PAUL SUPERANNUATION FUND       | 20681694970 | Billing Account       | SA Org Level Name 35 | SA Org Level Name 36 | SA Org Level Name 37 | SA Org Level Name 38 | Yes               | Vinoth_003 Tester_003 | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 2         | APPC        |

  @Multiple_SAR_APPC_And_EParcel
  Scenario Outline: Submit multiple sub-account requests for existing APPC customer ['<org_name>', '<abn>'] with '<contract_relationship>' level contract populating "APPC" product, contract rates '<add_contract_rates>', parcel send "Yes", business address and lodgement point
    Given Got the number of DSRs from an existing customer ['<org_name>', '<abn>']
    And From an existing customer ['<org_name>', '<abn>'], active billing account is identified
    When User submits sub-account request for an existing '<contract_relationship>' level APPC contract by populating name['<sub_account_name1>', '<sub_account_name2>'], contract rates "Yes", parcel send '<parcel_send_login>' with '<contact>', "APPC" product , "No" eLMS, address['<address_line_1>', '<address_line_2>', '<sub_urb>', '<state>', '<post_code>'] and lodgement point['<lodgement_point>']
    And User submits sub-account request for an existing '<contract_relationship>' level APPC contract by populating name['<sub_account_name3>', '<sub_account_name4>'], contract rates "Yes", parcel send '<parcel_send_login>' with '<contact>', "APPC" product , "No" eLMS, address['<address_line_1>', '<address_line_2>', '<sub_urb>', '<state>', '<post_code>'] and lodgement point['<lodgement_point>']
    Then Verify whether sub-account request '<sub_account_name1>' has been created for customer ['<org_name>', '<abn>'] with contract rates "Yes", parcel send login '<parcel_send_login>', product "APPC" and status is "Success"
    And Verify whether sub-account request '<sub_account_name3>' has been created for customer ['<org_name>', '<abn>'] with contract rates "Yes", parcel send login '<parcel_send_login>', product "APPC" and status is "Success"
    And Verify whether <no_of_dsr> "Customer Onboarding" DSR with status as "Submitted" is generated for '<dsr_product>' product under customer ['<org_name>', '<abn>']

    @PTEST
    Examples:
      | org_name                       | abn         | contract_relationship | sub_account_name1    | sub_account_name2    | sub_account_name3    | sub_account_name4    | parcel_send_login | contact               | address_line_1 | address_line_2 | sub_urb   | state | post_code | lodgement_point         | no_of_dsr | dsr_product  |
      | THE CARTER SUPERANNUATION FUND | 14506358601 | Organisation          | SA Org Level Name 31 | SA Org Level Name 32 | SA Org Level Name 33 | SA Org Level Name 34 | Yes               | Vinoth_106 Murali_106 | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 2         | APPC;eParcel |
      | PAUL SUPERANNUATION FUND       | 20681694970 | Billing Account       | SA Org Level Name 35 | SA Org Level Name 36 | SA Org Level Name 37 | SA Org Level Name 38 | Yes               | Vinoth_003 Tester_003 | 111            | BOURKE Street  | MELBOURNE | VIC   | 3000      | ST KILDA ROAD POST SHOP | 2         | APPC;eParcel |