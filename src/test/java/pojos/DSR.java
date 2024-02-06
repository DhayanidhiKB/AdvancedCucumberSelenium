package pojos;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class DSR {
    private String description;
    private String support_work_type;
    private String dsr_stage;
    private String integration_status;
    private String integration_status_description;
    private String rating_integration_status;
    private String rating_integration_status_description;
}