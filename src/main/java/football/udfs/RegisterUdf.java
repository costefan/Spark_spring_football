package football.udfs;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static java.lang.annotation.RetentionPolicy.*;

@Retention(RUNTIME)
@Component
public @interface RegisterUdf {
}
