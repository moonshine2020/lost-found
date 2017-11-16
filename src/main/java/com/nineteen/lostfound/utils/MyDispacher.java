package com.nineteen.lostfound.utils;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhang on 2017/3/26.
 */
public class MyDispacher implements HandlerInterceptor {
    private static final Logger logger = Logger.getLogger(MyDispacher.class);
    private List<String> blackList;


    //Action之前执行
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.debug("请求-------"+httpServletRequest.getRequestURI());
        logger.debug("----------校验登录权限----------");

        HttpSession session=httpServletRequest.getSession();
        Object userName= session.getAttribute("username");
        Object privelege=session.getAttribute("privelege");
        String url=httpServletRequest.getRequestURL().toString();


        if(url.endsWith("adminIndex.html")){
            if(privelege!=null){
                return true;
            }
            else{
                String str=httpServletRequest.getContextPath()+"/html/login.html";
                httpServletResponse.sendRedirect(str);
                return false;
            }
        }

        if(userName!=null){
            System.out.println(userName + "--->已登录");
            return true;
        }

        for (int i = 0; i <blackList.size() ; i++) {
            if (url.endsWith(blackList.get(i))){
                if (logger.isDebugEnabled()){
                    logger.debug("----------在黑名单----------");
                    String str=httpServletRequest.getContextPath()+"/html/login.html";
                    httpServletResponse.sendRedirect(str);
                    return false;
                }
            }
        }
       return true;
    }
    //生成视图之前执行
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    //最后执行可用于释放资源
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

  /*  public void setWhiteList(List<String> whiteList) {
        this.blackList = whiteList;
    }

    public List<String> getWhiteList() {
        return blackList;
    }*/

    public void setBlackList(List<String> blackList) {
        this.blackList = blackList;
    }

    public List getBlackList() {
        return blackList;
    }
}
