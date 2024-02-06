package com.my.salesforce.sandbox;

public interface Constants {
    String ROOT_DIRECTORY = System.getProperty("user.dir");
    String PROPERTIES_FILE_PATH = ROOT_DIRECTORY + "/resources/properties/";
    String INITIAL_PROPOSAL_STAGE = "Draft";
    String PROPOSAL_STAGE = "Generated";
    String CONTRACT_CONDITION = "Fixed Term";
    String CARTONS_B2B = "30";
    String CARTONS_B2C = "70";
    String CARTONS_ITEMS = "10";
    String CARTONS_CONNOTES = "10";
    String LOCATION_TYPE = "Offices";
    String SCHEDULING = "Permanent";
    String DAY = "Monday";
    String INTERSTATE_METRO = "25";
    String INTERSTATE_REGION = "25";
    String INTRASTATE_METRO = "25";
    String INTRASTATE_REGION = "25";
    String CURRENT_ARRIVAL_TIME = "10:00 am";
    String REQUIRED_ARRIVAL_TIME = "10:00 am";
    String CLOSING_TIME = "11:00 am";
    String READY_TIME = "10:30 am";
    String LOADING_TIME = "30";
}