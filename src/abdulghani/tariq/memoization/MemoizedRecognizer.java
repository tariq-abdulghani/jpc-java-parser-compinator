package abdulghani.tariq.memoization;

import abdulghani.tariq.recognizers.Recognizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Recognizer Adapter used to apply memoization on other recognizers
 */
public class MemoizedRecognizer implements Recognizer {
    private ResultsTable resultsTable = ResultsTable.getInstance();
    private RecursionCountTable recursionCountTable = RecursionCountTable.getInstance();

    private  Recognizer inner;

    public MemoizedRecognizer(Recognizer r){
        inner = r;
    }

    @Override
    public List<Integer> apply(String[] input, int i) {
        Result storedResult = resultsTable.get(i, this);
        if(storedResult != null){
            System.out.println("memoized: " + inner +", " + storedResult);
            return  storedResult.indices;
        }
        System.out.println("memoize: (" + inner +", " + i + ")");
        System.out.println("checking left rec count "+ inner);
        if(recursionCountTable.get(i, inner) > input.length - i){
            System.out.println("exceeded max rec count ");
            List<Integer> indices = new ArrayList<>();
            resultsTable.add(i, this, indices);
            return  indices;
        }
        recursionCountTable.add(i, inner, recursionCountTable.get(i, inner)+1);
        List<Integer> indices = inner.apply(input, i);
        resultsTable.add(i, this, indices);
        return indices;
    }

    @Override
    public String toString() {
        return inner.toString();
    }
}
