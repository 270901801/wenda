package com.newcoder.service;

import com.newcoder.dao.UserDAO;
import com.newcoder.model.User;
import org.apache.solr.common.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;


/**
 * @author hsc
 * @date 2019/7/31 - 18:09
 */
@Service
public class UserService {
    private static final Logger logger=LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO userDAO;

    public User selectByName(String name){
        return userDAO.selectByName(name);
    }
    public Map<String,Object> register(String username,String password){
        Map<String,Object> map=new HashMap<String,Object>();
        if(StringUtils.isEmpty(username)){
            map.put("msg","用户名不能为空");
            return  map;
        }
        if(StringUtils.isEmpty(password)){
            map.put("msg","密码不能为空");
            return map;
        }
        User user=userDAO.selectByName(username);
        if(user!=null){
            map.put("msg","用户名已经被注册");
            return map;
        }

        user=new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0,5));
        String head=String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000));
        user.setHeadUrl(head);
//        user.setPassword(WendaUtil.MD5(password));
        userDAO.addUser(user);

        //登录
        String ticket=null;//addLoginTicket(user);
        map.put("ticket",ticket);
        return  map;
    }
    public User getUser(int id){
        return userDAO.selectById(id);
    }
    public void logout(String ticket){
//        loginTicketDAO.updateStatus(ticket,1);
    }
}
