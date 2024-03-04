package ac.daejeon.app.aspectAndInterceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class AppAspect {

    /**
     * Before: 대상 “메서드”가 실행되기 전에 Advice를 실행합니다.
     *
     * @param joinPoint
     */
    @Before("execution(* ac.daejeon.app.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {

        //System.out.println("접속한 라우팅 " + joinPoint.getSignature().getName());
        //log.info("Before: " + joinPoint.getSignature().getName());
    }


    //@Around("@annotation(simpleEvent)")
    @Around("execution(* ac.daejeon.app.controller.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {


        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();

        //System.out.println("메서드 네임 " + methodName);

        String session_studentId = "";
        String session_studentEmail = "";
        int session_studentIdx = 0;

        for (Object arg : args) {



            if (arg instanceof HttpServletRequest) {

                Object studentId = ((HttpServletRequest) arg).getSession().getAttribute("STUDENT_ID");
                Object studentIdx = ((HttpServletRequest) arg).getSession().getAttribute("STUDENT_IDX");
                Object studentEmail = ((HttpServletRequest) arg).getSession().getAttribute("STUDENT_EMAIL");

                if (studentId != null && studentIdx != null && studentEmail != null) {
                    session_studentId = ((HttpServletRequest) arg).getSession().getAttribute("STUDENT_ID").toString();
                    session_studentIdx = (int) ((HttpServletRequest) arg).getSession().getAttribute("STUDENT_IDX");
                    session_studentEmail = ((HttpServletRequest) arg).getSession().getAttribute("STUDENT_EMAIL").toString();
                }

            }

            if (arg instanceof Model) {

                if (!session_studentId.equals("") && !session_studentEmail.equals("") && session_studentIdx != 0) {
                    ((Model) arg).addAttribute("studentIdx", session_studentIdx);
                    ((Model) arg).addAttribute("studentId", session_studentId);
                    ((Model) arg).addAttribute("studentEmail", session_studentEmail);
                } else {
                    ((Model) arg).addAttribute("studentIdx", null);
                    ((Model) arg).addAttribute("studentId", null);
                    ((Model) arg).addAttribute("studentEmail", null);
                }
            }

        }

        return joinPoint.proceed();
        //return proceed;
    }


}
