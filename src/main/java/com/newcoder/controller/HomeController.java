package com.newcoder.controller;

import com.newcoder.model.Question;
import com.newcoder.model.ViewObject;
import com.newcoder.service.QuestionService;
import com.newcoder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hsc
 * @date 2019/7/31 - 17:53
 */
@Controller
public class HomeController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    private  List<ViewObject> getQuestion(int userId, int offset, int limit){
        List<Question> questionList=questionService.getLatestQuestions(userId,offset,limit);
        List<ViewObject> vos=new ArrayList<>();
        for (Question question:questionList){
            ViewObject vo=new ViewObject();
            vo.set("question",question);
            vo.set("user",userService.getUser(userId));
            vos.add(vo);
        }
        return vos;
    }
    @RequestMapping(path = {"/","/index"},method = {RequestMethod.GET,RequestMethod.POST})
    public String index(Model model,
                        @RequestParam(value = "pop", defaultValue = "0") int pop){
        model.addAttribute("vos",getQuestion(0,0,10));
        System.out.println("============================"+getQuestion(0,0,10));
        return "/templates/index";
    }
}
