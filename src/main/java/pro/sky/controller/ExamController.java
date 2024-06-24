package pro.sky.controller;

import pro.sky.service.ExaminerService;
import pro.sky.service.impl.JavaQuestionService;
import pro.sky.model.Question;
import pro.sky.service.impl.ExaminerServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get/{amount}")
    public Collection<Question> outQuestionByAmount (@PathVariable("amount") int amount){
        return examinerService.getRandomQuestions(amount);
    }
}
