package quiz.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Lida pelo ProcessadorPerguntas via Reflection para criar objetos Pergunta automaticamente
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PerguntaQuiz {

    String statement();

    String[] alternatives();

    int correct();

    String category() default "";

    String difficulty() default "MÉDIO";
}
