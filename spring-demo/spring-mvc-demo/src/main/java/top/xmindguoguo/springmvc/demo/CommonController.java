package top.xmindguoguo.springmvc.demo;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: 于国帅
 * @Description:
 * @Date: 2021/1/19 0:35
 * @Version: v1.0
 */
@Controller
@RequestMapping("/common")
public class CommonController {
    /**
     * 获取springmvc 所有的@RequestMapping 对应的url
     *
     * @param request
     * @return Object
     * @Title getUrlMapping
     * @author 于国帅
     * @date 2018年9月7日 上午8:25:03
     */
    @ResponseBody
    @RequestMapping("getUrlMapping")
    public Object getUrlMapping(HttpServletRequest request) {
        List<String> uList = new ArrayList<String>();// 存储所有url集合
        // WebApplicationContext springvc 的应用上下文 能够获取 getServletContext
        WebApplicationContext wac = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);// 获取上下文对象
        // 获取 HandlerMapping 的map
        Map<String, HandlerMapping> requestMappings = BeanFactoryUtils.beansOfTypeIncludingAncestors(wac, HandlerMapping.class, true,
                false);
        for (HandlerMapping handlerMapping : requestMappings.values()) {
            if (handlerMapping instanceof RequestMappingHandlerMapping) {
                // 内部很深的水啊
                RequestMappingHandlerMapping rmhm = (RequestMappingHandlerMapping) handlerMapping;
                Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
                for (RequestMappingInfo rmi : handlerMethods.keySet()) {
                    PatternsRequestCondition prc = rmi.getPatternsCondition();
                    // 获取到了所有的url 因为不知道接下来干什么，所以暂时忽略掉
                    Set<String> patterns = prc.getPatterns();
                    for (String uStr : patterns)
                        uList.add(uStr + "<br/>");
                }
            }
        }
        return uList;
    }
}
