package com.wudi.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class OrderRateLimiterFilter extends ZuulFilter {
    /**
     * 通过 谷歌 guava框架——>进行网关限流使用
     * 限流 每秒1千个令牌
     */
    private static  final RateLimiter RATE_LIMITER = RateLimiter.create(1000);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 限流在所有的过滤器中最先执行
     * @return
     */
    @Override
    public int filterOrder() {
        return -4;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext =  RequestContext.getCurrentContext();
        HttpServletRequest request =  requestContext.getRequest();
        String url = request.getRequestURI();

        if ("/apigateway/order/api/v1/order/saveF".equalsIgnoreCase(url)){
            //会被 run方法拦截
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext requestContext =  RequestContext.getCurrentContext();

        /**
         * 如果没有拿到令牌,返回错误码
         */
        if (!RATE_LIMITER.tryAcquire()){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }

        return null;
    }
}
