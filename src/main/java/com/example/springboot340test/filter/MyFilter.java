package com.example.springboot340test.filter;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
@Order(2)
public class MyFilter implements Filter {

    public  MyFilter(){
        log.info("create MyFilter1");
    }

    @PostConstruct
    public  void myinit(){
        log.info("myinit MyFilter1");
    }

    /**
     * 在filter被加载到service中的时候被container调用，Servlet container实例化完filter以后立即调用Filter的init方法
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("myFilter1 init........");
    }

    /**
     * 在每次request/response时候会被container调用，能够传递到doFilter中的request或者response可以传递到Filter环中的下一个环节。 此时的Filter在设计模式中被称作责任链模式结构
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("Filter1执行任务前.................");
        chain.doFilter(request, response);
        log.info("Filter1执行任务后...........");
    }

    /**
     * 当filter从service中移除的时候，container调用destroy方法，通过调用这个方法，释放Filter所占有的系统资源。
     */
    @Override
    public void destroy() {
        System.out.println("Myfilter2 destory........");
    }

}