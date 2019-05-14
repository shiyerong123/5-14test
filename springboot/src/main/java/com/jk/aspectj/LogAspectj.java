package com.jk.aspectj;


import com.jk.model.LogBean;
import com.jk.utils.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Aspect
@Component
public class LogAspectj {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Pointcut("execution(* com.jk.controller.*.*(..))")
    public void logPointCut(){}
    @AfterReturning(value="logPointCut()",argNames= "jp,rtv",returning="rtv")
    public void saveLog(JoinPoint jp, Object rtv){
        LogBean logBean = new LogBean();
        String className = jp.getTarget().getClass().getName();
        logBean.setClassName(className);
        String methodName = jp.getSignature().getName();
        logBean.setMethodName(methodName);
        Object[] args = jp.getArgs();
        StringBuffer stringBuffer = new  StringBuffer();
        for (int i = 0; i < args.length; i++) {
            stringBuffer.append("第"+i+"个参数值=").append(args[i].toString());
        }
        logBean.setParams(stringBuffer.toString());
        logBean.setResponseBody(rtv==null?"":rtv.toString());
        logBean.setCreateTime(new Date());
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttributes;
        if(sra!=null){
            HttpServletRequest request = sra.getRequest();
            logBean.setIp(IpUtil.getIpAddr(request));
            HttpSession session = request.getSession();
            /*GoodBean user2 = (GoodBean)session.getAttribute("user");
            if (user2 != null) {
                logBean.setUserId(user2.getUserId());
            }*/
        }
        mongoTemplate.insert(logBean);
        // List<LogBean> findAll = mongoTemplate.findAll(LogBean.class);


    }
}
