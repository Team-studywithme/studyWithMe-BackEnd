package team.studywithme.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
public class LogAspect {

    @Pointcut("execution(* team.studywithme.api.controller..*.*(..))")
    private void allController() {}

    @Around("allController()")
    public Object controllerTimer(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        long totalTimeMillis = stopWatch.getTotalTimeMillis();

        log.info("실행 API : /{}, 실행 시간 : {}ms", joinPoint.getSignature().getName(), totalTimeMillis);

        return result;
    }
}
