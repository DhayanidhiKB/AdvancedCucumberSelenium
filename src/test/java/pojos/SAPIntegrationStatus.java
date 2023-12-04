package pojos;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class SAPIntegrationStatus {
    private String status;
    private String rating_status;
    private String rating_description;
}