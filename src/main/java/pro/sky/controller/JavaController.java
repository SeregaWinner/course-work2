package pro.sky.controller;


import pro.sky.model.Question;
import pro.sky.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaController {

    private final QuestionService questionService;

    public JavaController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam(value = "question") String question,
                                @RequestParam(value = "answer") String answer) {
        return questionService.add(question, answer);

    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam(value = "question") String question,
                           @RequestParam(value = "answer") String answer) {
        return questionService.remove(new Question(question, answer));
    }

    @GetMapping()
    public Collection<Question> getQuestions() {
        return questionService.getAll();
    }


}
