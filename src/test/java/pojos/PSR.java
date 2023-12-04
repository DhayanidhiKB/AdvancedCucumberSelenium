package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PSR {
    private String agreement_duration;
    private String agreement_type;
    private String catalyst;
    private String sales_justification;
    private String owner;
    private String recommendation;
    private String end_date;
    private String approved_event;
}