package abdulghani.tariq;

import abdulghani.tariq.memoization.MemoizedRecognizer;
import abdulghani.tariq.recognizers.Recognizer;
import abdulghani.tariq.recognizers.SequenceCombinator;
import abdulghani.tariq.recognizers.TokenRecognizer;

import static abdulghani.tariq.recognizers.OrElseCombinator.of;
import static abdulghani.tariq.recognizers.SequenceCombinator.*;

public class Main {

    public static void main(String[] args) {
        String[] input = new String[]{
                "let", "function", "id", "{", "...", "}", ";"
        };

        String[] input2 = new String[]{
                "let", "x", "=", "number", ";",
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

        Recognizer funcRecognizer = startWith(new MemoizedRecognizer(let))
                .then(new MemoizedRecognizer(function))
                .then(new MemoizedRecognizer(id))
                .then(new MemoizedRecognizer(lParen))
                .then(new MemoizedRecognizer(ellipsis))
                .then(new MemoizedRecognizer(rParen))
                .then(new MemoizedRecognizer(semi));

        Recognizer variableRecognizer = startWith(new MemoizedRecognizer(let))
                .then(new MemoizedRecognizer(varX))
                .then(new MemoizedRecognizer(assign))
                .then(new MemoizedRecognizer(num))
                .then(new MemoizedRecognizer(semi));

        Recognizer memFunc = new MemoizedRecognizer(funcRecognizer);
//        memFunc.apply(input,0);
//        memFunc.apply(input,0);
//        memFunc.apply(input,0);

        Recognizer memVar = new MemoizedRecognizer(variableRecognizer);
        Recognizer funcOrVar =  new MemoizedRecognizer(of(memFunc).or(memVar));
//        funcOrVar.apply(input2, 0);
//        funcOrVar.apply(input2, 0);


        /**
         *
         * E -> E + R | P
         * P -> a | b
         * R -> c | d
         *
         * test on a + c
         */


        Recognizer plus = new MemoizedRecognizer(new TokenRecognizer("PLUS", "+"));
        Recognizer P = new MemoizedRecognizer(
                of(new MemoizedRecognizer(new TokenRecognizer("a","a")))
                .or(new MemoizedRecognizer(new TokenRecognizer("b", "b")))
                );

        Recognizer R = new MemoizedRecognizer(
                of(new MemoizedRecognizer(new TokenRecognizer("c","c")))
                        .or(new MemoizedRecognizer(new TokenRecognizer("d", "d")))
        );
        Recognizer S = self("S");
        Recognizer E = new MemoizedRecognizer(memorisedSelf("E").then(plus).then(R).or(P));
        String[] input3 = new String[]{"a", "+", "d"};
//        System.out.println(E);
        E.apply(input3, 0);

//        P.apply(input3, 0);
//        System.out.println("---------");
//        R.apply(input3, 2);
    }


}
