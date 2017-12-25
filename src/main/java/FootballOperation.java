import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FootballOperation {
    private int code;
    private String from;
    private String to;
    private String stadium;
    private String startTime;
}
