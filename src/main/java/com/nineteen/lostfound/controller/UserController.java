package com.nineteen.lostfound.controller;

import com.alibaba.fastjson.JSONObject;
import com.nineteen.lostfound.entity.User;
import com.nineteen.lostfound.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by mengxu on 2017/9/26.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/login",method= RequestMethod.POST)
    @ResponseBody
    public JSONObject checkLogin(HttpServletRequest request) {
        JSONObject result=new JSONObject();
        String username=request.getParameter("user");
        String password=request.getParameter("password");
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()){
                //使用shiro来验证
                token.setRememberMe(true);
                currentUser.login(token);//验证角色和权限
            }
        }catch(Exception ex){

        }
        result.put("success", true);
        return result;
    }
    @RequestMapping(value="/test",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> test(HttpServletRequest request, HttpServletResponse response){
       String userid=request.getParameter("userid");
       String password=request.getParameter("password");
        User user=new User();
        user.setUserid(userid);
        user.setPassword(password);
        int flag= userService.login(user);
       /* JSONObject jsonObject=new JSONObject();
        jsonObject.put("flag",flag);*/
        Map<String,Object> jsonObject=new HashMap<String, Object>();
        jsonObject.put("flag",flag);
        return jsonObject;
    }
}
