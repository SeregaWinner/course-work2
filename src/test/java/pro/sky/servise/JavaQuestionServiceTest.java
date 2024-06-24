package pro.sky.servise;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.exception.LimitExceededQuestionException;
import pro.sky.exception.QuestionAlreadyAddedException;
import pro.sky.exception.QuestionNotFoundException;
import pro.sky.model.Question;
import pro.sky.service.QuestionService;
import pro.sky.service.impl.JavaQuestionService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

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
        questions.forEach(question -> questionService
                .add(question.getQuestion(), question.getAnswer()));
    }

    @AfterEach
    public void afterEach() {
        new ArrayList<>(questionService.getAll()).forEach(questionService::remove);

    }

    @Test
    void add1Test() {
        int was = questionService.getAll().size();
        Question expected = new Question("Вопрос 7", "Ответ 7");
        Question actual = questionService.add(new Question("Вопрос 7", "Ответ 7"));

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was + 1);
        assertThat(questionService.getAll()).contains(expected);
    }

    @Test
    void add2Test() {
        int was = questionService.getAll().size();
        Question expected = new Question("Вопрос 7", "Ответ 7");
        Question actual = questionService.add("Вопрос 7", "Ответ 7");

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was + 1);
        assertThat(questionService.getAll()).contains(expected);
    }

    @Test
    void when_questionService_already_added_then_questions_QuestionAlreadyAddedException_is_thrown_one_test() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add(new Question("Вопрос 1", "Ответ 1")));

    }

    @Test
    void when_questionService_already_added_then_questions_QuestionAlreadyAddedException_is_thrown_two_test() {
        assertThatExceptionOfType(QuestionAlreadyAddedException.class)
                .isThrownBy(() -> questionService.add("Вопрос 1", "Ответ 1"));

    }

    @Test
    void removeTest() {
        int was = questionService.getAll().size();
        Question expected = new Question("Вопрос 6", "Ответ 6");
        assertThat(questionService.getAll().contains(expected));
        Question actual = questionService.remove(new Question("Вопрос 6", "Ответ 6"));

        assertThat(actual).isEqualTo(expected);
        assertThat(questionService.getAll()).hasSize(was - 1);
        assertThat(questionService.getAll()).doesNotContain(expected);
    }

    @Test
    void when_questionService_not_found_then_questions_QuestionNotFoundException_is_thrown() {
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(new Question("Вопрос 7", "Ответ 7")));

    }

    @Test
    void getAllTest() {
        assertThat(questionService.getAll())
                .containsExactlyInAnyOrderElementsOf(questions);
    }

    @Test
    void getRandomQuestionsTest() {
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }

    @Test
    void when_questionService_limit_exceeded_then_questions_LimitExceededQuestionException_is_thrown() {
        afterEach();
        assertThatExceptionOfType(LimitExceededQuestionException.class)
                .isThrownBy(questionService::getRandomQuestion);
    }


}