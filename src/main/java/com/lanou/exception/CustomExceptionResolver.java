package com.lanou.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lizhongren1 on 2017/12/4.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {

        // 前端控制器DispatcherServlet在进行HandlerMapping
        // 调用handlerAdapter执行Handler的过程中, 如果遇到异常会执行此方法

        // Handler实际上是最终要执行的方法, 真实身份是HandlerMethod

        request.setAttribute("msg", ex.getMessage());

        try {
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ModelAndView();
    }
}
