package steps;

import com.my.salesforce.sandbox.application.pages.Salesforce;
import com.my.salesforce.sandbox.browsers.BrowserSupplier;
import com.my.salesforce.sandbox.properties.UserConfig;
import io.cucumber.java.*;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pojos.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Hooks {
    private final Base lBase;

    public Hooks(@NotNull Base base) {
        this.lBase = base;
    }

    @Before
    public void set_up() {
        this.lBase.lBrowser = BrowserSupplier.launch(UserConfig.getProperties().browser());
        this.lBase.salesforce = new Salesforce(this.lBase.lBrowser);
    }

    @After
    public void shut_down() {
        this.lBase.salesforce.shut_down();
    }

    @AfterStep
    public void addSnapShot(@NotNull Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File source = ((TakesScreenshot) this.lBase.lBrowser).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(source);
            scenario.attach(fileContent, "image/png", "image");
        }
    }

    @DataTableType
    public Opportunity opportunity(@NotNull Map<String, String> details) {
        return new Opportunity(details.get("sub_type"), details.get("stage"),
                details.get("identify_steps"), details.get("close_date"), details.get("is_it_startrack"),
                details.get("total_value"), details.get("type"), details.get("qualify_steps"),
                details.get("description"));
    }

    @DataTableType
    public ClosedOpportunity close_opportunity(@NotNull Map<String, String> details) {
        return new ClosedOpportunity(details.get("stage_name"), details.get("next_steps"),
                details.get("reason"), details.get("comment"));
    }

    @DataTableType
    public DSR dsr(@NotNull Map<String, String> details) {
        return new DSR(details.get("description"), details.get("support_work_type"),
                details.get("dsr_stage"), details.get("integration_status"),
                details.get("integration_status_description"), details.get("rating_integration_status"),
                details.get("rating_integration_status_description"));
    }

    @DataTableType
    public PSR psr(@NotNull Map<String, String> details) {
        return new PSR(details.get("agreement_duration"), details.get("agreement_type"), details.get("catalyst"),
                details.get("sales_justification"), details.get("owner"), details.get("recommendation"),
                details.get("end_date"), details.get("approved_event"));
    }

    @DataTableType
    public Competitor competitor(@NotNull Map<String, String> details) {
        return new Competitor(details.get("type"), details.get("product"), details.get("name"),
                details.get("status"), details.get("advantage"));
    }

    @DataTableType
    public Proposal proposal(@NotNull Map<String, String> details) {
        return new Proposal(details.get("price_structure"), details.get("lodgement_zone"), details.get("lodgement_zone1"),
                details.get("lodgement_zone2"), details.get("cubic_status"), details.get("cubic_factor"));
    }

    @DataTableType
    public ContractConfirmationDetails contract_details(@NotNull Map<String, String> details) {
        return new ContractConfirmationDetails(details.get("entity"), details.get("type"), details.get("product"));
    }

    @DataTableType
    public Contract contract(@NotNull Map<String, String> details) {
        return new Contract(details.get("sign_step"), details.get("sign_status"), details.get("approval_status"),
                details.get("active_step"), details.get("active_status"));
    }

    @DataTableType
    public Zones zones(@NotNull Map<String, String> details) {
        return new Zones(details.get("zone"), details.get("zone1"), details.get("zone2"));
    }

    @DataTableType
    public PickUpLocation location(@NotNull Map<String, String> details) {
        return new PickUpLocation(details.get("customer_brief"), details.get("address_line1"), details.get("address_line2"),
                details.get("suburb"), details.get("state"), details.get("postcode"));
    }

    @DataTableType
    public SubAccountRequest sub_account_request(@NotNull Map<String, String> details) {
        return new SubAccountRequest(details.get("contract_rates"), details.get("parcel_send"), details.get("eLMS"),
                details.get("address_line_1"), details.get("address_line_2"), details.get("sub_urb"),
                details.get("state"), details.get("post_code"), details.get("lodgement_point"));
    }

    @DataTableType
    public SAPIntegrationStatus sapIntegrationStatus(@NotNull Map<String, String> details) {
        return new SAPIntegrationStatus(details.get("status"), details.get("rating_status"),
                details.get("rating_description"));
    }
}