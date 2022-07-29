package abdulghani.tariq.memoization;

import abdulghani.tariq.recognizers.Recognizer;

import java.util.List;

/**
 * Recognizer Adapter used to apply memoization on other recognizers
 */
public class MemoizedRecognizer implements Recognizer {
    private ResultsTable resultsTable = ResultsTable.getInstance();
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
        List<Integer> indices = inner.apply(input, i);
        resultsTable.add(i, this, indices);
        return indices;
    }

    @Override
    public String toString() {
        return inner.toString();
    }
}
