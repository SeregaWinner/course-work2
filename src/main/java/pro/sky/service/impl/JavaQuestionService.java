package pro.sky.service.impl;

import jakarta.annotation.PostConstruct;
import pro.sky.exception.LimitExceededQuestionException;
import pro.sky.exception.QuestionAlreadyAddedException;
import pro.sky.exception.QuestionNotFoundException;
import pro.sky.model.Question;
import org.springframework.stereotype.Service;
import pro.sky.service.QuestionService;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @PostConstruct
    private void init() {
        add("Что означает инициализация?",
                "Инициализация — присваивание какого-то значения переменной.");

        add("Что такое «цикл»?",
                "Цикл — конструкция кода, которая повторяет одно и то же действие несколько " +
                        "(столько, сколько нам потребуется) раз.");

        add("Что вы знаете о массивах?",
                "Массив — это структура данных, которая позволяет хранить несколько значений одного типа.");

        add("Что такое исключения?",
                "Исключения - ситуации, когда в работе программы что-то пошло не так.");

        add("Что такое полиморфизм в ООП?",
                "Полиморфизм (polymorphism) — это понятие из объектно-ориентированного программирования, " +
                        "которое позволяет разным сущностям выполнять одни и те же действия. " +
                        "При этом неважно, как эти сущности устроены внутри и чем они различаются.");
    }


    @Override
    public Question add(String question, String answer) {
        return add(new Question(question,answer));
    }

    @Override
    public Question add(Question question) {
        if(!questions.add(question)){
            throw new QuestionAlreadyAddedException();
        }
        return question;
    }



    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(questions);
    }


    @Override
    public Question remove(Question question) {
        if (!questions.remove(question)){
            throw new QuestionNotFoundException();
        }
        return question;
    }

    @Override
    public Question getRandomQuestion(){
        if (questions.isEmpty()){
            throw new LimitExceededQuestionException();
        }
        int randomQuestion = random.nextInt(questions.size());
        return questions.stream()
                .skip(randomQuestion)
                .findFirst()
                .orElseThrow(QuestionNotFoundException::new);
    }

}
