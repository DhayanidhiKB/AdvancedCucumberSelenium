package com.my.salesforce.sandbox.properties;

import com.my.salesforce.sandbox.enums.*;
import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/resources/properties/user.properties",
        "file:${user.dir}/resources/properties/remote.properties",
        "file:${user.dir}/resources/properties/sales-force.properties"})
public interface Properties extends Config {
    Browsers browser();

    Environments environment();

    int timeOut();

    String testData();

    String uploads();

    String snaps();

    String reportConfig();

    String reportName();

    String reports();

    String hubProtocol();

    String hubHost();

    String hubPort();

    @Key("${environment}.appUrl")
    String appUrl();

    @Key("${environment}.sales-username")
    String salesUsername();

    @Key("${environment}.sales-password")
    String salesPassword();

    @Key("${environment}.pricing-username")
    String pricingUsername();

    @Key("${environment}.pricing-password")
    String pricingPassword();

    @Key("${environment}.on-boarding-username")
    String onBoardingUsername();

    @Key("${environment}.on-boarding-password")
    String onBoardingPassword();
}