package com.newcoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author hsc
 * @date 2019/7/30 - 15:46
 */
//@Controller
public class IndexController {
    //

    @RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
    @ResponseBody
    public String index(HttpSession httpSession) {
        return "Hello NowCoder"+httpSession.getAttribute("msg");
    }


    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam(value = "type", defaultValue = "1", required = true) int type,
                          @RequestParam(value = "key", defaultValue = "hsc", required = false) String key) {
        return String.format("Profile page of %s  /  %d,t:%d  k:%s", groupId, userId, type, key);
    }

    @RequestMapping("/ty")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.didispace.com");
        System.out.println(11);
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "/templates/ty";
    }

    @RequestMapping(path = {"/request"},method = RequestMethod.GET)
    @ResponseBody
    public String request(Model model, HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession httpSession,
                          @CookieValue("JSESSIONID") String sessionId){
        StringBuilder sb=new StringBuilder();
        sb.append("COOKVALUE:"+sessionId);
        Enumeration<String> headerNames=request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String name=headerNames.nextElement();
            sb.append(name+":"+request.getHeader(name)+"<br>");
        }
        if(request.getCookies()!=null){
            for (Cookie cookie:request.getCookies()){
                sb.append("cookie:"+cookie.getName()+"value:"+cookie.getValue());
            }
        }
        return sb.toString();
    }

    @RequestMapping(path = {"/redirect/{code}"},method = RequestMethod.GET)
    public String request(@PathVariable("code") String code,
                          HttpSession httpSession){
        httpSession.setAttribute("msg","jump from redirect");
        return "redirect:/";
    }

    @RequestMapping(path = {"/admin"},method = RequestMethod.GET)
    @ResponseBody
    public String admin(@RequestParam("key") String key){
        if ("admin".equals(key)){
            return "hello admin";
        }
        throw new IllegalArgumentException("参数有误");
    }

    @ExceptionHandler()
    @ResponseBody
    public  String error(Exception e){
        return e.getMessage();
    }
}

