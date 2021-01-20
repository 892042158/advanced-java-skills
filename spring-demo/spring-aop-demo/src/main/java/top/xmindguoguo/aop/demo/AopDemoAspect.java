package top.xmindguoguo.aop.demo;//package com.fanfan.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 基础的aop的使用
 * 切入语法@see https://blog.csdn.net/zhengchao1991/article/details/53391244
 */
@Slf4j
@Component
@Aspect
public class AopDemoAspect {
    /**
     * 前置通知：目标方法执行之前执行以下方法体的内容
     *
     * @param jp
     */
    @Before("@annotation(top.xmindguoguo.aop.demo.AopDemo)")
    public void beforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
       log.info("@Before【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
    }

    /**
     * 返回通知：目标方法正常执行完毕时执行以下代码
     *
     * @param jp
     * @param result
     */
    @AfterReturning(value = "@annotation(top.xmindguoguo.aop.demo.AopDemo)", returning = "result")
    public void afterReturningMethod(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
       log.info("@AfterReturning【返回通知】the method 【" + methodName + "】 ends with 【" + result + "】");
    }

    /**
     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。
     *
     * @param jp
     */
    @After("@annotation(top.xmindguoguo.aop.demo.AopDemo)")
    public void afterMethod(JoinPoint jp) {
       log.info("@After【后置通知】this is a afterMethod advice...");
    }

    /**
     * 异常通知：目标方法发生异常的时候执行以下代码
     */
    @AfterThrowing(value = "@annotation(top.xmindguoguo.aop.demo.AopDemo)", throwing = "e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e) {
        String methodName = jp.getSignature().getName();
       log.info("@AfterThrowing【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
    }

    /**
     * 环绕通知：目标方法执行前后分别执行一些代码，发生异常的时候执行另外一些代码
     *
     * @return 从以上发现，返回通知和异常通知不会同时出现；不管是否发生异常，后置通知都会正常打印。
     */
    @Around(value = "@annotation(top.xmindguoguo.aop.demo.AopDemo)")
    public Object aroundMethod(ProceedingJoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object result = null;
        try {
           log.info("@Before【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
            // 执行目标方法
            result = jp.proceed();
           log.info("@AfterReturning【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with " + result);

        } catch (Throwable e) {
           log.info("@AfterThrowing【环绕通知中的--->异常通知】：the method 【" + methodName + "】 occurs exception " + e);
        }
       log.info("@After【环绕通知中的--->后置通知】：-----------------end.----------------------");

        return result;
    }

    public void printWebJoinPoint(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 获取RequestAttributes
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        // 如果要获取Session信息的话，可以这样写：
        // HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
//        Enumeration<String> enumeration = request.getParameterNames();
//        Map<String, String> parameterMap = new HashMap<>();
//        while (enumeration.hasMoreElements()) {
//            String parameter = enumeration.nextElement();
//            parameterMap.put(parameter, request.getParameter(parameter));
//        }
        /*        String str = JSON.toJSONString(parameterMap);
        if (obj.length > 0) {
            log.warn("请求的参数信息为：" + str);
        }*/

    }

    public void printJoinPoint(JoinPoint joinPoint) throws Throwable {
        // 获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        // AOP代理类的信息
        joinPoint.getThis();
        // 代理的目标对象
        joinPoint.getTarget();
        // 用的最多 通知的签名
        Signature signature = joinPoint.getSignature();
        // 代理的是哪一个方法
        log.info(signature.getName());
        // AOP代理类的名字
        log.info(signature.getDeclaringTypeName());
        // AOP代理类的类（class）信息
        signature.getDeclaringType();
    }

}
