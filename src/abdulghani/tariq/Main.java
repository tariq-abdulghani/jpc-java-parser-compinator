package abdulghani.tariq;

import abdulghani.tariq.recognizers.Recognizer;
import abdulghani.tariq.recognizers.TokenRecognizer;

import static abdulghani.tariq.recognizers.OrElseCombinator.of;
import static abdulghani.tariq.recognizers.SequenceCombinator.startWith;

public class Main {

    public static void main(String[] args) {
        String[] input = new String[]{
                "let",
                "function",
                "id",
                "{",
                "...",
                "}",
                ";"
        };

        String[] input2 = new String[]{
                "let",
                "x",
                "=",
                "number",
                ";",
        };

        TokenRecognizer let = new TokenRecognizer("LET", "let");
        TokenRecognizer function = new TokenRecognizer("FUNC", "function");
        TokenRecognizer id = new TokenRecognizer("ID", "id");
        TokenRecognizer lParen = new TokenRecognizer("L_PAREN", "{");
        TokenRecognizer ellipsis = new TokenRecognizer("ellipsis", "...");
        TokenRecognizer rParen = new TokenRecognizer("R_PAREN", "}");
        TokenRecognizer semi = new TokenRecognizer("SEMI", ";");

        TokenRecognizer varX = new TokenRecognizer("X", "x");
        TokenRecognizer assign = new TokenRecognizer("ASSIGN", "=");
        TokenRecognizer num = new TokenRecognizer("NUM", "number");

//        System.out.println(let.apply(input, 0));
        Recognizer functionRecognizer = startWith(let)
                .then(function)
                .then(id)
                .then(lParen)
                .then(ellipsis)
                .then(rParen)
                .then(semi);

        Recognizer variableRecognizer = startWith(let)
                .then(varX)
                .then(assign)
                .then(num)
                .then(semi);

        System.out.println(
                startWith(let)
                        .then(function)
                        .then(id)
                        .then(lParen)
                        .then(ellipsis)
                        .then(rParen)
                        .then(semi)
                        .apply(input, 0));

//        System.out.println(functionRecognizer);
//        System.out.println(variableRecognizer);
//        System.out.println(of(functionRecognizer).or(variableRecognizer));
        System.out.println(
                of(functionRecognizer).or(variableRecognizer).apply(input2, 0)
        );
    }


}
