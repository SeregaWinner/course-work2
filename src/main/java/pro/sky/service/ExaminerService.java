package pro.sky.service;

import pro.sky.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getRandomQuestions(int amount);
}