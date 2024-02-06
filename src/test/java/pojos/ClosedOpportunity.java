package pojos;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class ClosedOpportunity {
    private String stage_name;
    private String next_steps;
    private String reason;
    private String comment;
}