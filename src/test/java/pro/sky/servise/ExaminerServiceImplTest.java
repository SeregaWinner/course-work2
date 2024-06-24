package pro.sky.servise;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.exception.IncorrectAmountException;
import pro.sky.model.Question;

import pro.sky.service.QuestionService;
import pro.sky.service.impl.ExaminerServiceImpl;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    private final List<Question> questions = List.of(
            new Question("Вопрос 1", "Ответ 1"),
            new Question("Вопрос 2", "Ответ 2"),
            new Question("Вопрос 3", "Ответ 3"),
            new Question("Вопрос 4", "Ответ 4"),
            new Question("Вопрос 5", "Ответ 5"),
            new Question("Вопрос 6", "Ответ 6")
    );

    @BeforeEach
    public void beforeEach() {
        when(questionService.getAll()).thenReturn(questions);
    }

    @Test
    void getRandomQuestionsTest(){
        when(questionService.getRandomQuestion()).thenReturn(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 5", "Ответ 5"),
                new Question("Вопрос 5", "Ответ 5"),
                new Question("Вопрос 2", "Ответ 2")
        );
        assertThat(examinerService.getRandomQuestions(4)).containsExactlyInAnyOrder(
                new Question("Вопрос 1", "Ответ 1"),
                new Question("Вопрос 2", "Ответ 2"),
                new Question("Вопрос 3", "Ответ 3"),
                new Question("Вопрос 5", "Ответ 5")
        );

    }

    @Test
    void when_examinerService_incorrect_amount_IncorrectAmountException_is_throw(){
        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(()->examinerService.getRandomQuestions(7));

        assertThatExceptionOfType(IncorrectAmountException.class)
                .isThrownBy(()->examinerService.getRandomQuestions(-1));

    }





}
