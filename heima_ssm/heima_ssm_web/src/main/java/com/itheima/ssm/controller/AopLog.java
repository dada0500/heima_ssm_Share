package com.itheima.ssm.controller;

import com.itheima.ssm.domain.SysLog;
import com.itheima.ssm.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

@Component
@Aspect
public class AopLog {

    @Autowired
    private SysLogService sysLogService;

//    @Pointcut("execution(* com.itheima.ssm.controller.*.*(..))")
    @Pointcut("execution(* com.itheima.ssm.controller.*Controller.*(..))")
    private void pt1(){

    }

    @Around("pt1()")
    public Object log(ProceedingJoinPoint jp){
        Object proceed = null;
        try {
            // 获取开始时间
            Date visitTime = new Date ();
            long startTime = visitTime.getTime ();
            //
            Class clazz = jp.getTarget ().getClass ();
            // 获取方法名
            String methodName = jp.getSignature ().getName ();
            // 获取request 对象
            HttpServletRequest request = ((ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取response对象
            HttpServletResponse response = ((ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes()).getResponse();
            // 获取uri
            String contextPath = request.getContextPath ();
            System.out.println ("contextPath:" + contextPath);
            String url = request.getRequestURI ();
            url = url.replaceFirst (contextPath,"");
            // 获取ip
            String ip = request.getRemoteAddr ();
            // 获取username
            SecurityContext context = SecurityContextHolder.getContext ();
            User user = (User) context.getAuthentication ().getPrincipal ();
            String username = user.getUsername ();

            // 执行切入点方法
            proceed = jp.proceed (jp.getArgs ());
            // 获取结束时间,计算执行时间
            long endTime = System.currentTimeMillis ();
            Long executionTime = endTime - startTime;

            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime (executionTime);
            sysLog.setIp (ip);
            sysLog.setMethod ("[类名] " + clazz.getName () + "[方法名] " + methodName);
            sysLog.setUrl (url);
            sysLog.setUsername (username);
            sysLog.setVisitTime (new Timestamp (visitTime.getTime ()));
            // 调用Sevice完成操作
            sysLogService.save(sysLog);
        } catch (Throwable e) {
            e.printStackTrace ();
        }
        return proceed;
    }
/*org.mybatis.spring.MyBatisSystemException: nested exception is org.apache.ibatis.type.TypeException: Could not set parameters for mapping: ParameterMapping{property='ip', mode=IN, javaType=class java.lang.String, jdbcType=null, numericScale=null, resultMapId='null', jdbcTypeName='null', expression='null'}. Cause: org.apache.ibatis.type.TypeException: Error setting null for parameter #3 with JdbcType OTHER . Try setting a different JdbcType for this parameter or a different jdbcTypeForNull configuration property. Cause: java.sql.SQLException: ��Ч��������*/
}
