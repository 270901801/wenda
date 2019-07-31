package com.newcoder;

import com.newcoder.dao.QuestionDAO;
import com.newcoder.dao.UserDAO;
import com.newcoder.model.Question;
import com.newcoder.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.Random;

/**
 * @author hsc
 * @date 2019/7/31 - 15:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InitDatabaseTest {
    @Autowired
    UserDAO userDAO;
    @Autowired
    QuestionDAO questionDAO;
    @Test
    public void initDatabase(){
        Random random=new Random();
        for (int i=0;i<11;++i){
//            User user=new User();
//            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png",random.nextInt(1000)));
//            user.setName(String.format("USER%d",i));
//            user.setPassword("");
//            user.setSalt("");
//            userDAO.addUser(user);

            Question question=new Question();
            question.setCommentCount(i);
            Date date=new Date();
            date.setTime(date.getTime()+1000*3600*i);
            question.setCreatedDate(date);
            question.setUserId(i+1);
            question.setTitle(String.format("TITLE{%d}",i));
            question.setContent(String.format("xxx content %d",i));

            questionDAO.addQuestion(question);
        }
//        Assert.assertEquals("",userDAO.selectById(1).getPassword());
//        userDAO.deleteById(1);
//        Assert.assertNull(userDAO.selectById(1));

        System.out.println(questionDAO.selectLastestQuestions(0,0,10));
    }
}
