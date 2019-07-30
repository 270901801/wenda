package com.newcoder.wenda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hsc
 * @date 2019/7/30 - 15:46
 */
@Controller
public class IndexController {

    @RequestMapping(path = {"/","/index"},method = RequestMethod.POST)
    @ResponseBody
    public String index(){
        return "Hello NowCoder";
    }

    @RequestMapping(path = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @PathVariable("groupId") String groupId,
                          @RequestParam(value = "type",defaultValue = "1",required = true) int type,
                          @RequestParam(value = "key",defaultValue = "hsc",required = false) String key){
        return String.format("Profile page of %s  /  %d,t:%d  k:%s",groupId,userId,type,key);
    }
}
