package pojos;

import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
public class Contract {
    private String sign_step;
    private String sign_status;
    private String approval_status;
    private String active_step;
    private String active_status;
}