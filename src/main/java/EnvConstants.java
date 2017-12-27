import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnvConstants {
    DEV("dev"),
    QA("qa"),
    PROD("prod");

    private final String environmentName;
}
