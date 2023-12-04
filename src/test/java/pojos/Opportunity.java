package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Opportunity {
    private String sub_type;
    private String stage;
    private String identify_steps;
    private String close_date;
    private String is_it_startrack;
    private String total_value;
    private String type;
    private String qualify_steps;
    private String description;
}