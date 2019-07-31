package com.newcoder.service;

import com.newcoder.dao.QuestionDAO;
import com.newcoder.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hsc
 * @date 2019/7/31 - 17:56
 */
@Service
public class QuestionService {
    private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    QuestionDAO questionDAO;

    public List<Question> getLatestQuestions(int userId, int offset, int limit) {
        return questionDAO.selectLastestQuestions(userId, offset, limit);
    }
}
