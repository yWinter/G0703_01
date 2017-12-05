package com.lanou.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    @RequestMapping(value = "/home")
    public String homePage(){
        return "home";
    }

    // 定位到登录页
    @RequestMapping(value = "/login")
    public String loginPage(){
        if (SecurityUtils.getSubject().isAuthenticated()){
            return "home";
        }
        return "login";
    }

    // 登录表单验证
    @RequestMapping(value = "/loginsubmit")
    public String loginsubmit(HttpServletRequest request) throws Exception {

        // 如果在shirospring的配置文件中, 配置了表单认证过滤器
        // 那么在这个方法中只需要处理异常信息即可

        String exClassName =
                (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exClassName)){
            throw new UnknownAccountException("账号不存在");
        } else if (IncorrectCredentialsException.class.getName().equals(exClassName)){
            throw new IncorrectCredentialsException("密码错误");
        } else {
            throw new Exception(exClassName);
        }
    }

    @RequiresRoles("student")
    @RequestMapping(value = "/test")
    public String testm(){


        return "test";
    }
}
