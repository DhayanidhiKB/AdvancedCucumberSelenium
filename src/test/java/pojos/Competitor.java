package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Competitor {
    private String type;
    private String product;
    private String name;
    private String status;
    private String advantage;
}