package abdulghani.tariq.memoization;

import java.util.List;

public class Result {
    String recognizer;
    List<Integer> indices;

    public Result(String recognizer, List<Integer> indices){
        this.recognizer = recognizer;
        this.indices = indices;
    }
}
