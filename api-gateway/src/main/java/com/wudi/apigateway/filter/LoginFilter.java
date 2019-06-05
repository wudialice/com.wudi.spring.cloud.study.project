package com.wudi.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 登录过滤器
 */
@Component
public class LoginFilter extends ZuulFilter {

    /**
     * 过滤器类型 FilterConstants
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 值最小，最先执行
     * 它的值不能随便定义，有些基础的过滤器要在它之前执行
     * @return
     */
    @Override
    public int filterOrder() {
        return 4;
    }

    /**
     * 过滤器是否生效 true生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request =  requestContext.getRequest();
        String url = request.getRequestURI();
        System.out.println(url);
        System.out.println(request.getRequestURI());

        if ("/apigateway/order/api/v1/order/saveF".equalsIgnoreCase(url)){
           //会被 run方法拦截
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("被拦截了哦");
        //JWT、 权限控制 ACL
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request =  requestContext.getRequest();

        String token =  request.getHeader("token");

        if (StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }


        if (StringUtils.isBlank(token)){
            /**
             * 值为false则结束不进行下去了
             */
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        return null;
    }
}
