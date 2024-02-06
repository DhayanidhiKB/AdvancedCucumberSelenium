package pojos;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Proposal {
    private String price_structure;
    private String lodgement_zone;
    private String lodgement_zone1;
    private String lodgement_zone2;
    private String cubic_status;
    private String cubic_factor;
}