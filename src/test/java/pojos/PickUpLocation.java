package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PickUpLocation {
    private String customer_brief;
    private String address_line1;
    private String address_line2;
    private String suburb;
    private String state;
    private String postcode;
}