package football;

import org.apache.spark.sql.DataFrame;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static football.Constants.*;


@Profile(DEV)
@Component
@Aspect
public class ShowDfAspect {

    @Before("@annotation(football.aop.ShowDfInTheBeginning)")
    public void showDfInTheBeginning(JoinPoint joinPoint) {
        DataFrame dataFrame = (DataFrame) joinPoint.getArgs()[0];
        System.out.println("         Dataframe show in the beginning ... ");
        consolePrint(joinPoint, dataFrame);
    }

    @AfterReturning(value = "@annotation(football.aop.ShowDfInTheEnd)", returning = "dataFrame")
    public void showDfInTheEndOfMethod(JoinPoint joinPoint, DataFrame dataFrame) {
        System.out.println("         Dataframe show at the end ... ");
        consolePrint(joinPoint, dataFrame);
    }

    public void consolePrint(JoinPoint joinPoint, DataFrame dataFrame){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        System.out.println("            " + className);
        dataFrame.show();
    }
}
