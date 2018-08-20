package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Answer;
import com.demo.domain.Question;
import com.demo.domain.Room;
import com.demo.domain.User;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer>{
  	List<Answer>findByUser(User user);
  	
  	
  	
  	List<Answer>findByUserAndQuestionNotIn(User user, List<Question> questions); 
  	
    List<Answer> findByUserAndQuestion(User user, Question question);
    
    List<Answer> findByUserAndQuestionIn(User user, List<Question> questions);
    
    List<Answer> findByUserAndQuestionAndEndIsNull(User user, Question question);
  	
  	List<Answer>findAllByUserAndEndIsNull(User user);
  	List<Answer>findAllByUserAndEndIsNotNull(User user);
  	
  	List<Answer>findAllByUserAndCorrectTrue(User user);
  	List<Answer>findAllByUserAndCorrectFalse(User user);
  	
  	List<Answer>findAllByUserAndSkipTrue(User user);
  	
  	
}
