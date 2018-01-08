package football.broadcasted_configs;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.regex.Pattern;

@Component
@Getter
public class UserConfig implements Serializable {

    private final int maxPlayHours = 120;

    private final int minMinutesAfterLastTime = 0;

    private final int maxFirstTimeHours = 45;

    private final int maxSecondTimeHours = 90;

    private final String emptyDescription = "Empty description of operation";

    private Pattern timePattern = Pattern.compile("([0-9]+):([0-9]+)");

}
