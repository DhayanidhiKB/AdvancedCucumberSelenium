package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SubAccountRequest {
    private String contract_rates;
    private String parcel_send;
    private String eLMS;
    private String address_line_1;
    private String address_line_2;
    private String sub_urb;
    private String state;
    private String post_code;
    private String lodgement_point;
}